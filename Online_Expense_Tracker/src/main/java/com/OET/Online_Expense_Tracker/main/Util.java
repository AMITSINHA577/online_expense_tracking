package com.OET.Online_Expense_Tracker.main;

import java.util.Scanner;

public class Util {
	private static Scanner sc = new Scanner(System.in); 
	public static void clearScreen() {
		try {
			ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
			pb.inheritIO().start().waitFor();
		} catch (Exception e) {
			
		}
	}
	
	public static void inValidChoice() {
		System.out.println("Invalid choice. Please try again.");
		continueMessage();
	}
	
	public static void continueMessage() {
		System.out.println("Press Enter to continue...");
		sc.nextLine();
		clearScreen();
	}
}
