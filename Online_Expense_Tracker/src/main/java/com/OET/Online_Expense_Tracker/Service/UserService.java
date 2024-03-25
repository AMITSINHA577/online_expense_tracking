package com.OET.Online_Expense_Tracker.Service;

import com.OET.Online_Expense_Tracker.Entity.User;

public interface UserService {
    void registerUser(String name, String email, String password);
    User loginUser(String email, String password);
    boolean isValidEmail(String email);
}
