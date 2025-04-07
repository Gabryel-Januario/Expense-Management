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
import com.ExpenseManagement.Expense.Management.dto.RegisterRequestDTO;
import com.ExpenseManagement.Expense.Management.models.User;
import com.ExpenseManagement.Expense.Management.services.AuthenticationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity<String> UserLogin(@RequestBody @Valid LoginRequestDTO data) {
        Authentication auth = this.service.UserLogin(data);

        // TODO fix this response
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Login successfully");
        
    }

    @PostMapping("/register")
    public ResponseEntity<User> UserRegister(@RequestBody @Valid RegisterRequestDTO data) {
        User user = this.service.UserRegister(data);
        
        // TODO fix this response
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
