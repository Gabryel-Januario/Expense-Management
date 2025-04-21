package com.ExpenseManagement.Expense.Management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.ExpenseManagement.Expense.Management.dto.LoginRequestDTO;
import com.ExpenseManagement.Expense.Management.dto.LoginResponseDTO;
import com.ExpenseManagement.Expense.Management.dto.RegisterRequestDTO;
import com.ExpenseManagement.Expense.Management.services.AuthenticationService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> UserLogin(@RequestBody @Valid LoginRequestDTO data) {
        String token =  this.authenticationService.UserLogin(data);

        LoginResponseDTO response = new LoginResponseDTO(token);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        
    }

    @PostMapping("/register")
    public ResponseEntity<String> UserRegister(@RequestBody @Valid RegisterRequestDTO data) {
        this.authenticationService.UserRegister(data);
        
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully!");
    }
}
