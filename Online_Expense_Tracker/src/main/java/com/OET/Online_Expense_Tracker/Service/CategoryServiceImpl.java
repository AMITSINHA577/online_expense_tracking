package com.OET.Online_Expense_Tracker.Service;

import java.util.List;

import com.OET.Online_Expense_Tracker.Dao.CategoryDao;
import com.OET.Online_Expense_Tracker.Entity.Category;

public class CategoryServiceImpl implements CategoryService {
	
    private final CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

	public void addCategory(Category category) {
		categoryDao.addCategory(category);
	}

	public void updateCategory(Category category) {
		categoryDao.updateCategory(category);
	}

	public void deleteCategory(Category category) {
		categoryDao.deleteCategory(category);
	}

	public List<Category> getUserCategory(String userId) {
		return categoryDao.getCategoryByUser(userId);
	}
	
	public Category isValidCategory(int	 catId) {
		return categoryDao.isValidCategory(catId);
	}

}
