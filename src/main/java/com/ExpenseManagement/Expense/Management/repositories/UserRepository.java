package com.ExpenseManagement.Expense.Management.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.ExpenseManagement.Expense.Management.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByLogin(String login);
}   
