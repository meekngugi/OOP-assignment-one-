/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package loginsystem;
import java.util.Scanner;
import java.io.Console;

/**
 * BSD 214 Object-Oriented Programming 2 - Login System
 * This program handles user authentication with password masking
 * Users get 3 attempts to enter correct credentials
 */
public class LoginSystem {
    // Predefined valid credentials
    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "password123";
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int attempts = 3;
        boolean loginSuccessful = false;
        
        System.out.println("=== CAR RENTAL SYSTEM LOGIN ===");
        System.out.println("You have " + attempts + " attempts to login\n");
        
        // Login attempt loop
        while (attempts > 0 && !loginSuccessful) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            
            // Get password with masking
            String password = getMaskedPassword("Enter password: ");
            
            // Validate credentials
            if (validateCredentials(username, password)) {
                loginSuccessful = true;
                System.out.println("\n✅ Login successful! Welcome, " + username + "!");
            } else {
                attempts--;
                System.out.println("\n❌ Invalid username or password. Attempts remaining: " + attempts);
                
                if (attempts > 0) {
                    System.out.println("Please try again.\n");
                } else {
                    System.out.println("❌ No more attempts remaining. System locked.");
                }
            }
        }
        
        scanner.close();
        
        // If login successful, proceed to main application
        if (loginSuccessful) {
            System.out.println("\n=== ACCESS GRANTED TO CAR RENTAL SYSTEM ===");
            // Here you would typically launch the main Car Rental System
        }
    }
    
    /**
     * Method to get password input with asterisk masking
     * @param prompt The prompt to display to user
     * @return The entered password as String
     */
    private static String getMaskedPassword(String prompt) {
        Console console = System.console();
        
        if (console != null) {
            // Use Console.readPassword() for proper password masking
            char[] passwordArray = console.readPassword(prompt);
            return new String(passwordArray);
        } else {
            // Fallback for IDEs that don't support Console
            System.out.print(prompt);
            Scanner scanner = new Scanner(System.in);
            return scanner.nextLine();
        }
    }
    
    /**
     * Validates user credentials against stored values
     * @param username The username to validate
     * @param password The password to validate
     * @return true if credentials are valid, false otherwise
     */
    private static boolean validateCredentials(String username, String password) {
        // Comment: Comparing entered username and password with predefined values
        return VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password);
    }
}