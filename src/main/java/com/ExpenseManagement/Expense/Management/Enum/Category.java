package com.ExpenseManagement.Expense.Management.Enum;

public enum Category {
    
    FOOD("Food"),
    TRANSPORT("Transport"),
    HEALTH("Health"),
    HOUSING("Housing"),
    EDUCATION("Education"),
    ENTERTAINMENT("Entertainment"),
    OTHERS("Others"),
    GROCERIES("Groceries");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName; 
    }
}
