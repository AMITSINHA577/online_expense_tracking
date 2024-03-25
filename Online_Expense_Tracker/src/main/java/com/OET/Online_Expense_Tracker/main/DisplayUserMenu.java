package com.OET.Online_Expense_Tracker.main;

import java.util.Scanner;

import com.OET.Online_Expense_Tracker.Entity.User;

public class DisplayUserMenu {
	private static Scanner scanner = new Scanner(System.in);
	
	public static void displayMenu(User user) {
		 while (true) {
	    	  try {
	    		  Util.clearScreen();
		          System.out.println("What would you like to do?");
		          System.out.println("1. Manage Category");
		          System.out.println("2. Manage Expenses");
		          System.out.println("3. Compare Expenses by Category");
		          System.out.println("4. Log out");
		          
		          int choice = Integer.parseInt(scanner.nextLine());
		          ManageExpenses manageExpenses = new ManageExpenses();
		        		  
		          switch (choice) {
		              case 1:
		                  ManageCategory manageCategory = new ManageCategory();
		                  manageCategory.manageCategory(user);
		                  break;
		              case 2:
		      			  manageExpenses.manageExpenses(user);
		                  break;
		              case 3:
		      			  manageExpenses.compareExpenses(user);
		                  break;
		              case 4:
		                  System.out.println("Logged out.");
		                  Util.continueMessage();
		                  return;
		              default:
		                  Util.inValidChoice();
		          }
	    	  } catch (Exception e) {
	    		  Util.inValidChoice();
			}
		 }
	}

}
