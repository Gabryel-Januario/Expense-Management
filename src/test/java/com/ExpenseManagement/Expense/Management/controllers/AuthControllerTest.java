package com.ExpenseManagement.Expense.Management.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.ExpenseManagement.Expense.Management.dto.LoginRequestDTO;
import com.ExpenseManagement.Expense.Management.dto.LoginResponseDTO;
import com.ExpenseManagement.Expense.Management.dto.RegisterRequestDTO;
import com.ExpenseManagement.Expense.Management.repositories.UserRepository;
import com.ExpenseManagement.Expense.Management.services.AuthenticationService;

public class AuthControllerTest {

    @Mock 
    private AuthenticationService authenticationService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUserLogin() {
        LoginRequestDTO request = new LoginRequestDTO("login@gmail.com", "password");
        String expectedResponse = "token";

        when(authenticationService.UserLogin(request)).thenReturn(expectedResponse);
        
        ResponseEntity<?> response = authController.UserLogin(request);

        LoginResponseDTO responseBody = (LoginResponseDTO) response.getBody();

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertNotNull(responseBody);
        assertEquals(expectedResponse, responseBody.getToken());
        

    }

    @Test
    void testUserRegister() {

        RegisterRequestDTO request = new RegisterRequestDTO("name", "login@gmail.com", "password");

        
    
    doNothing().when(authenticationService).UserRegister(request);

    ResponseEntity<?> response = authController.UserRegister(request);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals("User created successfully!", response.getBody());

    
    verify(authenticationService, times(1)).UserRegister(request);

    }
}
