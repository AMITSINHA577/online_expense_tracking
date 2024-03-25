package com.OET.Online_Expense_Tracker.Dao;

import java.util.List;

import com.OET.Online_Expense_Tracker.Entity.Expense;

public interface ExpenseDao {
	void addExpense(Expense expense);

	void updateExpense(Expense expense);

	void deleteExpense(Expense expense);

	List<Expense> getExpensesByUser(String userId);
	
	List<Expense> compareExpenses(String userId, String startDate, String endDate);
}