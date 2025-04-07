package com.ExpenseManagement.Expense.Management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.ExpenseManagement.Expense.Management.dto.LoginRequestDTO;
import com.ExpenseManagement.Expense.Management.dto.LoginResponseDTO;
import com.ExpenseManagement.Expense.Management.dto.RegisterRequestDTO;
import com.ExpenseManagement.Expense.Management.models.User;
import com.ExpenseManagement.Expense.Management.services.AuthenticationService;
import com.ExpenseManagement.Expense.Management.services.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> UserLogin(@RequestBody @Valid LoginRequestDTO data) {
        Authentication auth = this.authenticationService.UserLogin(data);

        String token = this.tokenService.generateToken(data);

        LoginResponseDTO response = new LoginResponseDTO(token);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        
    }

    @PostMapping("/register")
    public ResponseEntity<User> UserRegister(@RequestBody @Valid RegisterRequestDTO data) {
        User user = this.authenticationService.UserRegister(data);
        
        // TODO fix this response
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
