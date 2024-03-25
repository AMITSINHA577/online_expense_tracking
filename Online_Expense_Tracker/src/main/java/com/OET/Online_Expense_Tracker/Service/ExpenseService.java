package com.OET.Online_Expense_Tracker.Service;

import java.util.List;

import com.OET.Online_Expense_Tracker.Entity.Expense;

public interface ExpenseService {
    void addExpense(Expense expense);
    void updateExpense(Expense expense);
    void deleteExpense(Expense expense);
    List<Expense> getUserExpenses(String userId);
    List<Expense> compareExpenses(String userId, String startDate, String endDate);
}
