package com.OET.Online_Expense_Tracker.main;

import java.util.Scanner;

import com.OET.Online_Expense_Tracker.Dao.UserDao;
import com.OET.Online_Expense_Tracker.Dao.UserDaoImpl;
import com.OET.Online_Expense_Tracker.Entity.User;
import com.OET.Online_Expense_Tracker.Service.UserService;
import com.OET.Online_Expense_Tracker.Service.UserServiceImpl;
import com.OET.Online_Expense_Tracker.Util.HibernateUtil;

public class Main {

	public static void main(String[] args) {
		UserDao userDao = new UserDaoImpl(HibernateUtil.getSessionFactory());
		UserService userService = new UserServiceImpl(userDao);

		Scanner scanner = new Scanner(System.in);
		Util.clearScreen();
		System.out.println("Welcome to Expense Tracker!");

		while (true) {
			System.out.println("Please select your choise - ");
			System.out.println("1 - New user registration");
			System.out.println("2 - Login");
			System.out.println("3 - Exit");
			int choice = -1;
			try {
				choice = Integer.parseInt(scanner.nextLine());
				switch(choice) {
				case 1:
					Util.clearScreen();
					registerUser(scanner, userService);
					break;
				case 2:
					Util.clearScreen();
					loginUser(scanner, userService);
					break;
				case 3:
					return;
				default:
					Util.inValidChoice();
				}
			} catch (Exception ex) {
				Util.inValidChoice();
			} 
		}
	}

	private static void registerUser(Scanner scanner, UserService userService) {
		String email = "";
		while(true) {
			System.out.println("Please enter your email:");
			email = scanner.nextLine();
			
			if(userService.isValidEmail(email)) {
				break;
			}
		}
		

		System.out.println("Please enter your name:");
		String name = scanner.nextLine();

		System.out.println("Please enter your password:");
		String password = scanner.nextLine();

		userService.registerUser(name, email, password);
		
		System.out.println("User resgistration is successfull. Please Login.");
		Util.continueMessage();
	}

	private static void loginUser(Scanner scanner, UserService userService) {
		System.out.println("Please enter your email:");
		String email = scanner.nextLine();

		System.out.println("Please enter your password:");
		String password = scanner.nextLine();
		
		User user = userService.loginUser(email, password);

		if(user != null) {
			Util.continueMessage();
			DisplayUserMenu.displayMenu(user);
		}
	}
}

