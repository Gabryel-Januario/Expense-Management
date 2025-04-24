package com.ExpenseManagement.Expense.Management.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.ExpenseManagement.Expense.Management.dto.ExpenseRequestDTO;
import com.ExpenseManagement.Expense.Management.dto.ExpenseResponseDTO;
import com.ExpenseManagement.Expense.Management.exceptions.UserNotFoundException;
import com.ExpenseManagement.Expense.Management.models.Expense;
import com.ExpenseManagement.Expense.Management.models.User;
import com.ExpenseManagement.Expense.Management.repositories.ExpenseRepository;
import com.ExpenseManagement.Expense.Management.repositories.UserRepository;

@Service
public class ExpenseService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    public ExpenseResponseDTO create(ExpenseRequestDTO data, Authentication authentication) {
        String userName = authentication.getName();
        User user = this.userRepository.findByLogin(userName)
        .orElseThrow(() -> new UserNotFoundException("User not found"));

        Expense expense = new Expense(data.getDescription(), data.getAmount(), data.getDate(), data.getCategory(), user);

        this.expenseRepository.save(expense);

        ExpenseResponseDTO expenseResponseDTO = new ExpenseResponseDTO(expense.getDescription(), expense.getAmount(), expense.getDate(), expense.getCategory(), expense.getUser().getId());

        return expenseResponseDTO;
    }
    
}
