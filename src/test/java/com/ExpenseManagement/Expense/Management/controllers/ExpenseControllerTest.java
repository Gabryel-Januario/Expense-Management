package com.ExpenseManagement.Expense.Management.controllers;

import com.ExpenseManagement.Expense.Management.dto.ExpenseRequestDTO;
import com.ExpenseManagement.Expense.Management.dto.ExpenseResponseDTO;
import com.ExpenseManagement.Expense.Management.services.ExpenseService;
import com.ExpenseManagement.Expense.Management.Enum.Category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ExpenseControllerTest {

    @Mock
    private ExpenseService expenseService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private ExpenseController expenseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateExpense() {
        ExpenseRequestDTO requestDTO = createExpenseRequest();
        ExpenseResponseDTO responseDTO = createExpenseResponse();

        when(expenseService.create(requestDTO, authentication)).thenReturn(responseDTO);

        ResponseEntity<ExpenseResponseDTO> response = expenseController.create(requestDTO, authentication);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        verify(expenseService, times(1)).create(requestDTO, authentication);
    }

    @Test
    void testGetAllExpenses() {
        List<ExpenseResponseDTO> expenseList = Arrays.asList(createExpenseResponse());

        when(expenseService.getAll(authentication)).thenReturn(expenseList);

        ResponseEntity<?> response = expenseController.getAll(authentication);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(expenseList, response.getBody());
        verify(expenseService, times(1)).getAll(authentication);
    }

    @Test
    void testGetExpenseById() {
        UUID id = UUID.randomUUID();
        ExpenseResponseDTO responseDTO = createExpenseResponse();

        when(expenseService.getById(id)).thenReturn(responseDTO);

        ResponseEntity<ExpenseResponseDTO> response = expenseController.getById(id);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        verify(expenseService, times(1)).getById(id);
    }

    @Test
    void testUpdateExpense() {
        UUID id = UUID.randomUUID();
        ExpenseRequestDTO requestDTO = createExpenseRequest();

        doNothing().when(expenseService).update(requestDTO, id);

        ResponseEntity<String> response = expenseController.update(requestDTO, id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Expense updated successfully", response.getBody());
        verify(expenseService, times(1)).update(requestDTO, id);
    }

    @Test
    void testDeleteExpense() {
        UUID id = UUID.randomUUID();

        doNothing().when(expenseService).delete(id);

        ResponseEntity<String> response = expenseController.delete(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Expense id: " + id + " deleted successfully", response.getBody());
        verify(expenseService, times(1)).delete(id);
    }

   
    private ExpenseRequestDTO createExpenseRequest() {
        ExpenseRequestDTO dto = new ExpenseRequestDTO();
        
        return dto;
    }

    private ExpenseResponseDTO createExpenseResponse() {
        return new ExpenseResponseDTO(
            "Test Description",
            BigDecimal.valueOf(100.00),
            LocalDate.now(),
            Category.FOOD,  
            "user123"
        );
    }
}
