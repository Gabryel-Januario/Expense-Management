package com.ExpenseManagement.Expense.Management.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ExpenseManagement.Expense.Management.Enum.Category;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ExpenseRequestDTO {

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotBlank(message = "Amount cannot be blank")
    private BigDecimal amount;

    @NotBlank(message = "Date cannot be blank")
    private LocalDate date;

    @NotBlank(message = "Category cannot be blank")
    private Category category;
    

}
