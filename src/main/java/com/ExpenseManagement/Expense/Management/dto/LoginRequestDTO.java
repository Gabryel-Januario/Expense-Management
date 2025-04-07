package com.ExpenseManagement.Expense.Management.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {

    @NotBlank(message = "login cannot be blank")
    private String login;
    
    @NotBlank(message = "password cannot be blank")
    private String password;


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
