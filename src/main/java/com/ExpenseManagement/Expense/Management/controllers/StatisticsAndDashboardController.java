package com.ExpenseManagement.Expense.Management.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ExpenseManagement.Expense.Management.services.StatisticsAndDashboardService;

@RestController
@RequestMapping("/statistics")
public class StatisticsAndDashboardController {

    @Autowired
    private StatisticsAndDashboardService statisticsAndDashboardService;

    @GetMapping("/current-month")
    public ResponseEntity<?> totalExpenseForThisMonth(Authentication authentication){

        BigDecimal totalExpenseForThisMonth = this.statisticsAndDashboardService.totalExpenseCurrentMonth(authentication);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Total expense for this month is: " + totalExpenseForThisMonth.toString());
    }

    @GetMapping("/expenses/pdf")
    public ResponseEntity<byte[]> expensesPdf(Authentication authentication) {
        byte[] pdf = this.statisticsAndDashboardService.dashboard(authentication);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=expense-management.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
