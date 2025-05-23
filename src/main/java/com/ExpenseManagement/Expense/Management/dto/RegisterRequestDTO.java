package com.ExpenseManagement.Expense.Management.dto;

import jakarta.validation.constraints.NotBlank;

public class RegisterRequestDTO {
    
    @NotBlank(message = "name cannot be empty")
    private String name;

    @NotBlank(message = "login cannot be empty")
    private String login;

    @NotBlank(message = "password cannot be empty")
    private String password;

    public RegisterRequestDTO(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

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
