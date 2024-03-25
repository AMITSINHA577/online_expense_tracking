package com.OET.Online_Expense_Tracker.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.OET.Online_Expense_Tracker.Entity.Category;
import com.OET.Online_Expense_Tracker.Entity.User;

public class CategoryDaoImpl implements CategoryDao{
	
	private final SessionFactory factory;
	
	public CategoryDaoImpl(SessionFactory factory){
		this.factory = factory;
	}
	
	public void addCategory(Category category) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(category);
		session.getTransaction().commit();
	}

	public void updateCategory(Category category) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Category old = session.get(Category.class, category.getId());
		old.setCategory(category.getCategory());
		session.save(old);
		session.getTransaction().commit();
	}

	public void deleteCategory(Category category) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.delete(category);  
		session.getTransaction().commit();
	}

	public List<Category> getCategoryByUser(String userId) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();

		Query query = session.createQuery("from Category where userId = :userId").setParameter("userId", userId);
		List<Category> list = query.list();  
		session.getTransaction().commit();
		return list; 
	}
	
	public Category isValidCategory(int catId) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Category category = session.createQuery("FROM Category WHERE id = :id", Category.class)
				.setParameter("id", catId).uniqueResult();
		session.getTransaction().commit();
		return category;
	}
}
