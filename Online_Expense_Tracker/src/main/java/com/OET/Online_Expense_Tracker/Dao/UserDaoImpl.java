package com.OET.Online_Expense_Tracker.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.OET.Online_Expense_Tracker.Entity.User;

public class UserDaoImpl implements UserDao {

	private final SessionFactory factory;

	public UserDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}

	public void saveUser(User user) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
	}

	public User getUserByEmailAndPassword(String email, String password) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();

		User user = session.createQuery("FROM User WHERE email = :email AND password = :password", User.class)
				.setParameter("email", email).setParameter("password", password).uniqueResult();

		session.getTransaction().commit();
		return user;
	}
	
	public User getUserByEmail(String email) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();

		User user = session.createQuery("FROM User WHERE email = :email", User.class)
				.setParameter("email", email).uniqueResult();

		session.getTransaction().commit();
		return user;
	}
}