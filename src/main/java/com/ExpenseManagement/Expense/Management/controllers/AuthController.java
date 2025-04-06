package com.ExpenseManagement.Expense.Management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.ExpenseManagement.Expense.Management.dto.RegisterDTO;
import com.ExpenseManagement.Expense.Management.models.User;
import com.ExpenseManagement.Expense.Management.services.AuthenticationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity<String> UserLogin() {
        return ResponseEntity.ok().body("successfully login");
    }

    @PostMapping("/register")
    public ResponseEntity<User> UserRegister(@RequestBody @Valid RegisterDTO data) {
        User user = this.service.UserRegister(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
