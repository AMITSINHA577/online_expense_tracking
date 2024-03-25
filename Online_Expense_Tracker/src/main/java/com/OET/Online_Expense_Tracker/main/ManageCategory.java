package com.OET.Online_Expense_Tracker.main;

import java.util.List;
import java.util.Scanner;

import com.OET.Online_Expense_Tracker.Dao.CategoryDao;
import com.OET.Online_Expense_Tracker.Dao.CategoryDaoImpl;
import com.OET.Online_Expense_Tracker.Entity.Category;
import com.OET.Online_Expense_Tracker.Entity.User;
import com.OET.Online_Expense_Tracker.Service.CategoryService;
import com.OET.Online_Expense_Tracker.Service.CategoryServiceImpl;
import com.OET.Online_Expense_Tracker.Util.HibernateUtil;

public class ManageCategory {
	
	Scanner scanner = new Scanner(System.in);
	private CategoryDao categoryDao = new CategoryDaoImpl(HibernateUtil.getSessionFactory());
	private CategoryService categoryService = new CategoryServiceImpl(categoryDao);
	
	public void manageCategory(User user) {
		 while (true) {
	    	  try {
	    		  Util.clearScreen();
		          System.out.println("What would you like to do?");
		          System.out.println("1. Add Category");
		          System.out.println("2. Update Category");
		          System.out.println("3. Delete Category");
		          System.out.println("4. View Category");
		          System.out.println("5. Back to previour menu");
		          
		          int choice = Integer.parseInt(scanner.nextLine());
		      	
		          switch (choice) {
		              case 1:
		            	  Util.clearScreen();
		                  addCategory(user);
		                  break;
		              case 2:
		            	  Util.clearScreen();
		                  updateCategory(user);
		                  break;
		              case 3:
		            	  Util.clearScreen();
		            	  deleteCategory(user);
		                  break;
		              case 4:
		            	  Util.clearScreen();
		                  viewCategory(user, false, true);
		                  break;
		              case 5:
		                  return;
		              default:
		                  Util.inValidChoice();
		          }
	    	  } catch (Exception e) {
				Util.inValidChoice();
			}
		 }
	}
	public void viewCategory(User user, boolean showId, boolean cls) {
		List<Category> category = categoryService.getUserCategory(user.getEmail());
		displayCategory(category, showId);
		if(cls) {
			Util.continueMessage();
		}
	}
	private void displayCategory(List<Category> category, boolean showId) {
		if (category.isEmpty()) {
		    System.out.println("No category found.");
		    Util.continueMessage();
		    return;
		}
		
		System.out.println("Your Category :");
		for (Category cat : category) {
			if(showId) {
				System.out.println(cat.getId() + " - " + cat.getCategory());
			} else {
				System.out.println(" - " + cat.getCategory());
			}
		}
	}
	private void deleteCategory(User user) {
		boolean conti = true;
		while(conti) {
			List<Category> list = categoryService.getUserCategory(user.getEmail());
			displayCategory(list, true);
			long categoryId = 0L;
			 if(list.size() > 0) {
				 System.out.print("Enter category id to delete - ");
				 boolean deleted = false;
				 try {
					 categoryId = Long.parseLong(scanner.nextLine());
					 for(Category c : list) {
						 if(c.getId() == categoryId) {
							 categoryService.deleteCategory(c);
							 deleted = true;
							 break;
						 }
					 }
					 if(deleted) {
						 if(list.size() > 1) {
							 System.out.println("Category deleted successfully. Do you want to continue to delete more - (y|n)");
							 String input = scanner.nextLine();
							 if(input.equals("y") || input.equals("Y")) {
								 Util.clearScreen();
								 deleteCategory(user);
								 return;
							 } else {
								conti = false;
							 }
						 } else {
							 System.out.println("Category deleted successfully.");
							 Util.continueMessage();
							 conti = false;
						 }
					 } else {
						 System.out.println("Invalid Category Id.");
					 }
				 } catch (Exception e) {
					System.out.println("Invalid Category Id.");
				}
			 } else {
				 conti = false;
			 }
		 }
	}
	private void updateCategory(User user) {
		boolean conti = true;
		 while(conti) {
			 List<Category> list = categoryService.getUserCategory(user.getEmail());
			 displayCategory(list, true);
			 long categoryId = 0L;
			 if(list.size() > 0) {
				 System.out.print("Enter Category id to update - ");
				 boolean updated = false;
				 try {
					 categoryId = Long.parseLong(scanner.nextLine());
					 for(Category c : list) {
						 if(c.getId() == categoryId) {
							 System.out.print("Enter new category : ");
							 String cat = scanner.nextLine();
							 c.setCategory(cat);
							 categoryService.updateCategory(c);
							 updated = true;
							 break;
						 }
					 }
					 if(updated) {
						 System.out.println("Category updated successfully. Do you want to continue to update more - (y|n)");
						 String input = scanner.nextLine();
						 if(input.equals("y") || input.equals("Y")) {
							 Util.clearScreen();
							 updateCategory(user);
							 return;
						 } else {
							 conti = false;
						 }
					 } else {
						 System.out.print("Invalid Category Id.");
						 Util.continueMessage();
					 }
				 } catch (Exception e) {
					System.out.print("Invalid Category Id.");
					Util.continueMessage();
				}
			 } else {
				 conti = false;
			 }
		 }
   }
	
	private void addCategory(User user) {
		  System.out.print("Enter new category : ");
		  String cat = scanner.nextLine();
		  Category category = new Category();
		  category.setCategory(cat);
		  category.setUser(user.getEmail());		
		  categoryService.addCategory(category);
		  System.out.println("Category added successfully.");
		  Util.continueMessage();
	}
	
	public Category isValidCategory(int catID) {
		return categoryService.isValidCategory(catID);
	}

}
