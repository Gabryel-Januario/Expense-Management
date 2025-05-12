package com.ExpenseManagement.Expense.Management.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<ExpenseResponseDTO> create(@RequestBody @Valid ExpenseRequestDTO data, Authentication authentication){
        ExpenseResponseDTO expenseResponseDTO = this.expenseService.create(data, authentication);

        return ResponseEntity.status(HttpStatus.CREATED).body(expenseResponseDTO);
    }

    @GetMapping("/expenses")
    public ResponseEntity<?> getAll(Authentication authentication) {
        List<ExpenseResponseDTO> response =  this.expenseService.getAll(authentication);

        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @GetMapping("/expense/{id}")
    public ResponseEntity<ExpenseResponseDTO> getById(@PathVariable UUID id) {

        ExpenseResponseDTO response =  this.expenseService.getById(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody ExpenseRequestDTO data, @PathVariable UUID id) {
        this.expenseService.update(data, id);

        return ResponseEntity.status(HttpStatus.OK).body("Expense updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        this.expenseService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body("Expense id: " + id + " deleted successfully");
    }
    
}
