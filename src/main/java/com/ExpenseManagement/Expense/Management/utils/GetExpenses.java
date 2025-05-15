package com.ExpenseManagement.Expense.Management.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.ExpenseManagement.Expense.Management.exceptions.UserNotFoundException;
import com.ExpenseManagement.Expense.Management.models.Expense;
import com.ExpenseManagement.Expense.Management.models.User;
import com.ExpenseManagement.Expense.Management.repositories.UserRepository;

@Component
public class GetExpenses {
    
    @Autowired
    private UserRepository userRepository;

    public List<Expense> getCurrentMonthExpense(Authentication authentication) {
        String username = authentication.getName();
        User user = this.userRepository.findByLogin(username)
        .orElseThrow(() -> new UserNotFoundException("User not found"));

        List<Expense> expenses = user.getExpenses();

        List<Expense> expensesForThisMonth = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (Expense expense : expenses) {
            if ( expense.getDate().getMonthValue() == today.getMonthValue() &&
            expense.getDate().getYear() == today.getYear()) {
                expensesForThisMonth.add(expense);
            }
        }
        return expensesForThisMonth;
    }
}
