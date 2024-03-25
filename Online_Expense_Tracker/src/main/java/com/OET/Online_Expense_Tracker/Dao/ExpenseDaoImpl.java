package com.OET.Online_Expense_Tracker.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.OET.Online_Expense_Tracker.Entity.Expense;

public class ExpenseDaoImpl implements ExpenseDao {
	private final SessionFactory factory;
	
	public ExpenseDaoImpl(SessionFactory factory){
		this.factory = factory;
	}
	
	public void addExpense(Expense expense) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(expense);
		session.getTransaction().commit();
	}

	public void updateExpense(Expense expense) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Expense old = session.get(Expense.class, expense.getId());
		old.setDescription(expense.getDescription());
		old.setDate(expense.getDate());
		old.setAmount(expense.getAmount());
		session.save(old);
		session.getTransaction().commit();
	}

	public void deleteExpense(Expense expense) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();

		session.delete(expense);  
		session.getTransaction().commit();
	}

	public List<Expense> getExpensesByUser(String userId) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();

		Query query = session.createQuery("from Expense where userId = :userId").setParameter("userId", userId);
		List<Expense> list = query.list();  
		session.getTransaction().commit();
		return list; 
	}
	
	public List<Expense> compareExpenses(String userId, String startDate, String endDate){
		Session session = factory.getCurrentSession();
		session.beginTransaction();

		Query query = session.createQuery("from Expense where userId = :userId and date >= :startDate and date <= :endDate")
				.setParameter("userId", userId).setParameter("startDate", startDate)
				.setParameter("endDate", endDate);
		List<Expense> list = query.list();  
		session.getTransaction().commit();
		return list; 
	}

}
