package com.ExpenseManagement.Expense.Management.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ExpenseManagement.Expense.Management.Enum.Category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
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
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Expense() {}

    public Expense(String description, BigDecimal amount, LocalDate date, Category category, User user) {
       this.description = description;
       this.amount = amount;
       this.date = date;
       this.category = category;
       this.user = user;
    }


}
