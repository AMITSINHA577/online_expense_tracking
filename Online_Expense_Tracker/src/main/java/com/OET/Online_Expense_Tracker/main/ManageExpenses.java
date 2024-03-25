package com.OET.Online_Expense_Tracker.main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.OET.Online_Expense_Tracker.Dao.ExpenseDao;
import com.OET.Online_Expense_Tracker.Dao.ExpenseDaoImpl;
import com.OET.Online_Expense_Tracker.Entity.Category;
import com.OET.Online_Expense_Tracker.Entity.Expense;
import com.OET.Online_Expense_Tracker.Entity.User;
import com.OET.Online_Expense_Tracker.Service.ExpenseService;
import com.OET.Online_Expense_Tracker.Service.ExpenseServiceImpl;
import com.OET.Online_Expense_Tracker.Util.HibernateUtil;

public class ManageExpenses {
	Scanner scanner = new Scanner(System.in);
	private ExpenseDao expenseDao = new ExpenseDaoImpl(HibernateUtil.getSessionFactory());
	private ExpenseService expenseService = new ExpenseServiceImpl(expenseDao);
	
	public void manageExpenses(User user) {
      while (true) {
    	  try {
    		  Util.clearScreen();
	          System.out.println("What would you like to do?");
	          System.out.println("1. Add expense");
	          System.out.println("2. Update expense");
	          System.out.println("3. Delete expense");
	          System.out.println("4. View expenses");
	          System.out.println("5. Back to previour menu");
	
	          int choice = Integer.parseInt(scanner.nextLine());
	
	          switch (choice) {
	              case 1:
	            	  Util.clearScreen();
	                  addExpense(user);
	                  break;
	              case 2:
	            	  Util.clearScreen();
	                  updateExpense(user);
	                  break;
	              case 3:
	            	  Util.clearScreen();
	            	  	deleteExpense(user);
	                  break;
	              case 4:
	            	  Util.clearScreen();
	                  viewExpenses(user);
	                  break;
	              case 5:
	                  return;
	              default:
	                  Util.inValidChoice();
	          }
    	  } catch(Exception ex) {
    		  Util.inValidChoice();
    	  }
      }
  }
	
  private void addExpense(User user) {
	  Category cat = null;
	  while(true) {
		  ManageCategory manageCategory = new ManageCategory();
		  manageCategory.viewCategory(user, true, false);
		  System.out.print("Select category for new expenses : ");
		  int catId = Integer.parseInt(scanner.nextLine());
		  cat = manageCategory.isValidCategory(catId);
		  if(cat != null){
			  break;
		  } else {
			  Util.inValidChoice();
		  }
	  }
	  
	  System.out.print("Enter expense description : ");
	  String description = scanner.nextLine();
	
	  System.out.print("Enter expense amount : ");
	  double amount = Double.parseDouble(scanner.nextLine());

	  Expense expense = new Expense();
	  expense.setDescription(description);
	  expense.setAmount(amount);
	  expense.setDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
	  expense.setUser(user.getEmail());
	  expense.setCat(cat.getCategory());
	  expenseService.addExpense(expense);
	
	  System.out.println("Expense added successfully.");
	  Util.continueMessage();
  }
  
	private void viewExpenses(User user) {
		List<Expense> expenses = expenseService.getUserExpenses(user.getEmail());
		displayExpences(expenses, false);
		Util.continueMessage();
	}
	
	private void displayExpences(List<Expense> expenses, boolean showId) {
		if (expenses.isEmpty()) {
		    System.out.println("No expenses found.");
		    Util.continueMessage();
		    return;
		}
		
		System.out.println("Your expenses:");
		for (Expense expense : expenses) {
			if(showId) {
				System.out.println(expense.getId() + " - " + expense.getCat() + "("+ expense.getDescription() + ") : $" + expense.getAmount()+" Date - "+expense.getDate());
			} else {
				System.out.println(" - " + expense.getCat() + "(" + expense.getDescription() + ") : $" + expense.getAmount()+" Date - "+expense.getDate());
			}
		}
	}
	
	 private void deleteExpense(User user) {
		 boolean conti = true;
		while(conti) {
			List<Expense> list = expenseService.getUserExpenses(user.getEmail());
			displayExpences(list, true);
			long expenseId = 0L;
			if(list.size() > 0) {
				 System.out.print("Enter Expense id to delete - ");
				 boolean deleted = false;
				 try {
					 expenseId = Long.parseLong(scanner.nextLine());
					 for(Expense e : list) {
						 if(e.getId() == expenseId) {
							 expenseService.deleteExpense(e);
							 deleted = true;
							 break;
						 }
					 }
					 if(deleted) {
						 if(list.size() > 1) {
							 System.out.println("Expense deleted successfully. Do you want to continue to delete more - (y|n)");
							 String input = scanner.nextLine();
							 if(input.equals("y") || input.equals("Y")) {
								 Util.clearScreen();
								 deleteExpense(user);
								 return;
							 } else {
								 conti = false;
							 }
						 } else {
							 System.out.println("Expense deleted successfully.");
							 Util.continueMessage();
							 conti = false;
						 }
					 } else {
						 System.out.println("Invalid Expense Id.");
						 Util.continueMessage();
					 }
				 } catch (Exception e) {
					System.out.println("Invalid Expense Id.");
					Util.continueMessage();
				}
			 } else {
				 conti = false;
			 }
		 }
   }
	 
	 
	 private void updateExpense(User user) {
		 boolean conti = true;
		while(conti) {
			 List<Expense> list = expenseService.getUserExpenses(user.getEmail());
			 displayExpences(list, true);
			 long expenseId = 0L;
			 if(list.size() > 0) {
				 System.out.print("Enter Expense id to update - ");
				 boolean updated = false;
				 try {
					 expenseId = Long.parseLong(scanner.nextLine());
					 for(Expense e : list) {
						 if(e.getId() == expenseId) {
							  System.out.print("Enter new description : ");
							  String description = scanner.nextLine();
							
							  System.out.print("Enter new amount :");
							  double amount = Double.parseDouble(scanner.nextLine());
							  e.setAmount(amount);
							  e.setDescription(description);
							 expenseService.updateExpense(e);
							 updated = true;
							 break;
						 }
					 }
					 if(updated) {
						 System.out.print("Expense updated successfully. Do you want to continue to update more - (y|n)");
						 String input = scanner.nextLine();
						 if(input.equals("y") || input.equals("Y")) {
							 Util.clearScreen();
							 updateExpense(user);
							 return;
						 } else {
							 conti = false;
						 }
					 } else {
						 Util.inValidChoice();
					 }
				 } catch (Exception e) {
					Util.inValidChoice();
				}
			 } else {
				 conti = false;
			 }
		 }
   }
	 
	 public void compareExpenses(User user) {
		 while(true) {
			 try {
				 Util.clearScreen();
				 System.out.print("Enter start date in dd-MM-yyyy formate : ");
				 String startDate = scanner.nextLine();
				 System.out.print("Enter end date in dd-MM-yyyy formate : ");
				 String endDate = scanner.nextLine();
				 List<Expense> list = expenseService.compareExpenses(user.getEmail(), startDate, endDate);
				 displayExpences(list, false);
			 } catch(Exception e) {
				 System.out.println(e.getMessage());
//				 Util.inValidChoice();
			 }
		 }
	 }
}
