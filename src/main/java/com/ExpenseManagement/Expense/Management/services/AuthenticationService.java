package com.ExpenseManagement.Expense.Management.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ExpenseManagement.Expense.Management.dto.RegisterDTO;
import com.ExpenseManagement.Expense.Management.exceptions.UserAlreadyExistsException;
import com.ExpenseManagement.Expense.Management.models.User;
import com.ExpenseManagement.Expense.Management.repositories.UserRepository;



@Service
public class AuthenticationService implements UserDetailsService{

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        return this.repository.findByLogin(username);
    }
    
    public User UserRegister(RegisterDTO data) {

        if(this.repository.findByLogin(data.getLogin()) != null) {
            throw new UserAlreadyExistsException("User already exists with this email!");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());

        User newUser = new User(data.getName(), data.getLogin(), encryptedPassword);

        this.repository.save(newUser);

        return newUser;
    }


    
}
