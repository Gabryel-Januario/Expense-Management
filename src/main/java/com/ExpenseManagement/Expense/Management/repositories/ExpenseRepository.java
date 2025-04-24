package com.ExpenseManagement.Expense.Management.repositories;

import org.springframework.stereotype.Repository;
import com.ExpenseManagement.Expense.Management.models.Expense;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, UUID>{
    
}
