package com.ExpenseManagement.Expense.Management.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ExpenseManagement.Expense.Management.Enum.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ExpenseRequestDTO {

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotNull(message = "Amount cannot be null")
    private BigDecimal amount;

    @NotNull(message = "Date cannot be null")
    private LocalDate date;

    @NotNull(message = "Category cannot be null")
    private Category category;
    

}
