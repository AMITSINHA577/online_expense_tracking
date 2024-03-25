package com.OET.Online_Expense_Tracker.Service;

import java.util.List;

import com.OET.Online_Expense_Tracker.Entity.Category;

public interface CategoryService {
	void addCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(Category category);
    Category isValidCategory(int catID);
    List<Category> getUserCategory(String userId);
}
