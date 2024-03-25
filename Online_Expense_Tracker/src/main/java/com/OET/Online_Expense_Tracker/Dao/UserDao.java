package com.OET.Online_Expense_Tracker.Dao;

import com.OET.Online_Expense_Tracker.Entity.User;

public interface UserDao {
    void saveUser(User user);
    User getUserByEmailAndPassword(String email, String password);
    User getUserByEmail(String email);
}