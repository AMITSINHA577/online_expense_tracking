package com.OET.Online_Expense_Tracker.Service;

import com.OET.Online_Expense_Tracker.Dao.UserDao;
import com.OET.Online_Expense_Tracker.Entity.User;

public class UserServiceImpl implements UserService {

	private final UserDao userDao;

	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	public void registerUser(String name, String email, String password) {
		User user = new User(name, email, password);
		userDao.saveUser(user);
		System.out.println("Registration successful!");
	}

	public User loginUser(String email, String password) {
		User user = userDao.getUserByEmailAndPassword(email, password);
		if (user != null) {
			System.out.println("Login successful! Welcome back, " + user.getName() + "!");
		} else {
			System.out.println("Login failed. Invalid email or password.");
		}
		return user;
	}
	
	public boolean isValidEmail(String email) {
		User user = userDao.getUserByEmail(email);
		if (user == null) {
			return true;
		} else {
			System.out.println("Email already exist Please try with different email.");
			return false;
		}
	}
}