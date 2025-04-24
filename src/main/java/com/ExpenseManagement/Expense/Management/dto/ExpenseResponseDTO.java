package com.ExpenseManagement.Expense.Management.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ExpenseManagement.Expense.Management.Enum.Category;


public class ExpenseResponseDTO {
    private String description;
    private BigDecimal amount;
    private LocalDate date;
    private Category category;
    private String userId;
    
    public ExpenseResponseDTO(String description, BigDecimal amount, LocalDate date,  Category category, String userId) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.userId = userId;
    }


    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public Category getCategory() {
        return category;
    }

    public String getUserId() {
        return userId;
    }

    
}
