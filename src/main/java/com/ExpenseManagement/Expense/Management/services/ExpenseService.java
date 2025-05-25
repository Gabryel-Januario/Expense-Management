package com.ExpenseManagement.Expense.Management.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;

import com.ExpenseManagement.Expense.Management.Enum.Category;
import com.ExpenseManagement.Expense.Management.dto.ExpenseRequestDTO;
import com.ExpenseManagement.Expense.Management.dto.ExpenseResponseDTO;
import com.ExpenseManagement.Expense.Management.exceptions.ExpenseNotFoundException;
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

    @Value("${api.python.ml.url}")
    private String url;

    public Category predictCategory(String description, BigDecimal amount) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> request = new HashMap<>();
        request.put("description", description);
        request.put("amount", amount);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<Map<String, Object>>() {}
        );

        Map<String, Object> body = response.getBody();
        String categoryString = (String) body.get("category");

        try {
            return Category.valueOf(categoryString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid category returned by model: " + categoryString);
        }
    }

    public ExpenseResponseDTO create(ExpenseRequestDTO data, Authentication authentication) {
        String userName = authentication.getName();
        User user = this.userRepository.findByLogin(userName)
        .orElseThrow(() -> new UserNotFoundException("User not found"));
        
        Category category;

        if(data.getCategory() == null) {
            category = this.predictCategory(data.getDescription(), data.getAmount());
        }else{
            category = data.getCategory();
        }

        Expense expense = new Expense(
            data.getDescription(), 
            data.getAmount(), 
            data.getDate(), 
            category, 
            user);
        
        this.expenseRepository.save(expense);

        ExpenseResponseDTO expenseResponseDTO = new ExpenseResponseDTO(
            expense.getDescription(), 
            expense.getAmount(), 
            expense.getDate(), 
            expense.getCategory(), 
            expense.getUser().getId());

        return expenseResponseDTO;
    }
    
    public List<ExpenseResponseDTO> getAll(Authentication authentication) {
        String login = authentication.getName();
        User user = this.userRepository.findByLogin(login).orElseThrow(() -> new UserNotFoundException("User not found"));

        List<Expense> expenses = user.getExpenses();

        List<ExpenseResponseDTO> expensesDTO = expenses.stream()
        .map(expense -> new ExpenseResponseDTO(
            expense.getDescription(), 
            expense.getAmount(), 
            expense.getDate(), 
            expense.getCategory())).toList();

        return expensesDTO;
    }

    public ExpenseResponseDTO getById(UUID id) {
        Expense expense = this.expenseRepository.findById(id)
        .orElseThrow(() -> new ExpenseNotFoundException("Expense not found"));

        ExpenseResponseDTO expenseDTO = new ExpenseResponseDTO(
                                            expense.getDescription(), 
                                            expense.getAmount(), 
                                            expense.getDate(), 
                                            expense.getCategory());

        return expenseDTO;
    }

    public void update(ExpenseRequestDTO data, UUID id) {

        Expense expense = this.expenseRepository.findById(id)
        .orElseThrow(() -> new ExpenseNotFoundException("Expense not found"));

        if(data.getDescription() != null && !data.getDescription().isBlank()) {
            expense.setDescription(data.getDescription());
        }
        if(data.getAmount() != null) {
            expense.setAmount(data.getAmount());
        }
        if (data.getDate() != null) {
            expense.setDate(data.getDate());
        }
        if (data.getCategory() != null && !data.getCategory().toString().isBlank()) {
            try {
                Category categoryEnum = Category.valueOf(data.getCategory().toString().toUpperCase());
                expense.setCategory(categoryEnum);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid category: " + data.getCategory());
            }
        }

        this.expenseRepository.save(expense);
    }

    public void delete(UUID id) {
        this.expenseRepository.findById(id)
        .orElseThrow(() -> new ExpenseNotFoundException("Expense not found"));

        this.expenseRepository.deleteById(id);
    }
 
}
