package com.ExpenseManagement.Expense.Management.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.ExpenseManagement.Expense.Management.models.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    UserDetails findByLogin(String login);
}   
