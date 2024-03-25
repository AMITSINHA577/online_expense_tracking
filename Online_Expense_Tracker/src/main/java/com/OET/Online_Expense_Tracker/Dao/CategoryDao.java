package com.OET.Online_Expense_Tracker.Dao;

import java.util.List;

import com.OET.Online_Expense_Tracker.Entity.Category;

public interface CategoryDao {
	void addCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(Category category);
    Category isValidCategory(int catId);
    List<Category> getCategoryByUser(String userId);
}
