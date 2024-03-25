package com.OET.Online_Expense_Tracker.Util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.OET.Online_Expense_Tracker.Entity.User;

public class HibernateUtil {
	private static final SessionFactory factory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).buildSessionFactory();
		} catch (Exception ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return factory;
	}
}