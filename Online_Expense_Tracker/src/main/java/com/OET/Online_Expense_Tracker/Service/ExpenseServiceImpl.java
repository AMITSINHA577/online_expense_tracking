package com.OET.Online_Expense_Tracker.Service;

import java.util.List;

import com.OET.Online_Expense_Tracker.Dao.ExpenseDao;
import com.OET.Online_Expense_Tracker.Entity.Expense;

public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseDao expenseDao;

    public ExpenseServiceImpl(ExpenseDao expenseDao) {
        this.expenseDao = expenseDao;
    }

    public void addExpense(Expense expense) {
        expenseDao.addExpense(expense);
    }

    public void updateExpense(Expense expense) {
        expenseDao.updateExpense(expense);
    }

    public List<Expense> getUserExpenses(String userId) {
        return expenseDao.getExpensesByUser(userId);
    }

	public void deleteExpense(Expense expense) {
		expenseDao.deleteExpense(expense);	
	}
	
	public List<Expense> compareExpenses(String userId, String startDate, String endDate){
		return expenseDao.compareExpenses(userId, startDate, endDate);
	}
}