package com.ExpenseManagement.Expense.Management.infra;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ExpenseManagement.Expense.Management.exceptions.UserNotFoundException;
import com.ExpenseManagement.Expense.Management.models.User;
import com.ExpenseManagement.Expense.Management.repositories.UserRepository;
import com.ExpenseManagement.Expense.Management.services.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    public String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        
        return authHeader.replace("Bearer ", "");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException{
            
    String requestURI = request.getRequestURI();

    if(requestURI.equals("/auth/login" ) || requestURI.equals("/auth/register")) {
        filterChain.doFilter(request, response);
        return;
    }



    String token = this.recoverToken(request);

    if(token != null) {
        try{
            String login = this.tokenService.valideteToken(token);
            User user = this.userRepository.findByLogin(login).orElseThrow(() -> new UserNotFoundException("User not found"));

            UsernamePasswordAuthenticationToken authentication  = new UsernamePasswordAuthenticationToken(user, null ,user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (RuntimeException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); 
            response.getWriter().write("Unauthorized: Invalid or expired token.");
            return;  
        } 
    }else {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  
        response.getWriter().write("Unauthorized: Token is missing.");
        return;  
    }

    filterChain.doFilter(request, response);

    }
    
}
