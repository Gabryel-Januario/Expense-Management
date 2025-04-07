package com.ExpenseManagement.Expense.Management.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ExpenseManagement.Expense.Management.dto.LoginRequestDTO;
import com.ExpenseManagement.Expense.Management.dto.RegisterRequestDTO;
import com.ExpenseManagement.Expense.Management.exceptions.UserAlreadyExistsException;
import com.ExpenseManagement.Expense.Management.exceptions.UserNotFoundException;
import com.ExpenseManagement.Expense.Management.models.User;
import com.ExpenseManagement.Expense.Management.repositories.UserRepository;



@Service
public class AuthenticationService implements UserDetailsService{

    @Autowired
    private UserRepository repository;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(@Lazy AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        return this.repository.findByLogin(username);
    }
    
    public User UserRegister(RegisterRequestDTO data) {

        if(this.repository.findByLogin(data.getLogin()) != null) {
            throw new UserAlreadyExistsException("User already exists with this email!");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());

        User newUser = new User(data.getName(), data.getLogin(), encryptedPassword);

        this.repository.save(newUser);

        return newUser;
    }


    public Authentication UserLogin(LoginRequestDTO data) {
        try{
            UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                    data.getLogin(), data.getPassword()
                );
            Authentication auth = this.authenticationManager.authenticate(usernamePassword);

            return auth;
        }catch(BadCredentialsException e ){
            throw new UserNotFoundException("User not found or invalid credentials");
        }
    }
}
