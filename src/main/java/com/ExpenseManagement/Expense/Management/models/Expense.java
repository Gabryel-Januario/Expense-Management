package com.ExpenseManagement.Expense.Management.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ExpenseManagement.Expense.Management.Enum.Category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "expenses")
@Table(name = "expenses")
public class Expense {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDate date;
    
    @Column(nullable = false)
    private Category category;
    
    @Column(nullable = false)
    private String userId;


}
