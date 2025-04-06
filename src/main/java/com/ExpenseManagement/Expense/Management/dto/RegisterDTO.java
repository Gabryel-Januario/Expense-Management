package com.ExpenseManagement.Expense.Management.dto;

import jakarta.validation.constraints.NotBlank;

public class RegisterDTO {
    
    @NotBlank(message = "name cannot be empty")
    private String name;

    @NotBlank(message = "login cannot be empty")
    private String login;

    @NotBlank(message = "password cannot be empty")
    private String password;

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
