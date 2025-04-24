package com.ExpenseManagement.Expense.Management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.ExpenseManagement.Expense.Management.dto.ExpenseRequestDTO;
import com.ExpenseManagement.Expense.Management.dto.ExpenseResponseDTO;

import com.ExpenseManagement.Expense.Management.services.ExpenseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("expense")
public class ExpenseController {


    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/create")
    public ResponseEntity<ExpenseResponseDTO> createExpense(@RequestBody @Valid ExpenseRequestDTO data, Authentication authentication){

        ExpenseResponseDTO expenseResponseDTO = this.expenseService.create(data, authentication);

        return ResponseEntity.status(HttpStatus.CREATED).body(expenseResponseDTO);

    }
    
}
