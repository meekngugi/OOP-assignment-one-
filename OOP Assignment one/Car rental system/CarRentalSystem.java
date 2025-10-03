import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

/**
 * BSD 214 Object-Oriented Programming 2 - Car Rental System
 * Complete Car Rental System with Car, Customer, and Rental Agency classes
 */
public class CarRentalSystem {
    public static void main(String[] args) {
        RentalAgency agency = new RentalAgency();
        Scanner scanner = new Scanner(System.in);
        
        // Initialize with sample data
        initializeSampleData(agency);
        
        boolean running = true;
        
        System.out.println("=== CAR RENTAL MANAGEMENT SYSTEM ===");
        
        while (running) {
            displayMenu();
            int choice = getIntInput(scanner, "Enter your choice: ");
            
            switch (choice) {
                case 1:
                    rentCar(agency, scanner);
                    break;
                case 2:
                    returnCar(agency, scanner);
                    break;
                case 3:
                    addNewCar(agency, scanner);
                    break;
                case 4:
                    registerCustomer(agency, scanner);
                    break;
                case 5:
                    agency.displayAvailableCars();
                    break;
                case 6:
                    agency.displayAllCustomers();
                    break;
                case 7:
                    agency.displayAllRentals();
                    break;
                case 8:
                    running = false;
                    System.out.println("Thank you for using Car Rental System!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }
    
    private static void displayMenu() {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. Rent a Car");
        System.out.println("2. Return a Car");
        System.out.println("3. Add New Car");
        System.out.println("4. Register New Customer");
        System.out.println("5. View Available Cars");
        System.out.println("6. View All Customers");
        System.out.println("7. View All Rentals");
        System.out.println("8. Exit");
        System.out.println("=====================");
    }
    
    private static void rentCar(RentalAgency agency, Scanner scanner) {
        System.out.println("\n--- Rent a Car ---");
        agency.displayAvailableCars();
        
        String carId = getStringInput(scanner, "Enter Car ID to rent: ");
        int customerId = getIntInput(scanner, "Enter Customer ID: ");
        int days = getIntInput(scanner, "Enter rental days: ");
        
        boolean success = agency.rentCar(carId, customerId, days);
        if (success) {
            System.out.println("✅ Car rented successfully!");
        } else {
            System.out.println("❌ Failed to rent car. Please check availability.");
        }
    }
    
    private static void returnCar(RentalAgency agency, Scanner scanner) {
        System.out.println("\n--- Return a Car ---");
        String carId = getStringInput(scanner, "Enter Car ID to return: ");
        
        boolean success = agency.returnCar(carId);
        if (success) {
            System.out.println("✅ Car returned successfully!");
        } else {
            System.out.println("❌ Failed to return car. Car not found or not rented.");
        }
    }
    
    private static void addNewCar(RentalAgency agency, Scanner scanner) {
        System.out.println("\n--- Add New Car ---");
        String id = getStringInput(scanner, "Enter Car ID: ");
        String make = getStringInput(scanner, "Enter Car Make: ");
        String model = getStringInput(scanner, "Enter Car Model: ");
        int year = getIntInput(scanner, "Enter Year: ");
        double dailyRate = getDoubleInput(scanner, "Enter Daily Rate: ");
        
        Car car = new Car(id, make, model, year, dailyRate);
        agency.addCar(car);
        System.out.println("✅ New car added successfully!");
    }
    
    private static void registerCustomer(RentalAgency agency, Scanner scanner) {
        System.out.println("\n--- Register New Customer ---");
        int id = getIntInput(scanner, "Enter Customer ID: ");
        String name = getStringInput(scanner, "Enter Customer Name: ");
        String email = getStringInput(scanner, "Enter Email: ");
        String phone = getStringInput(scanner, "Enter Phone: ");
        
        Customer customer = new Customer(id, name, email, phone);
        agency.addCustomer(customer);
        System.out.println("✅ New customer registered successfully!");
    }
    
    private static void initializeSampleData(RentalAgency agency) {
        // Add sample cars
        agency.addCar(new Car("C001", "Toyota", "Camry", 2022, 45.00));
        agency.addCar(new Car("C002", "Honda", "Civic", 2023, 40.00));
        agency.addCar(new Car("C003", "Ford", "Mustang", 2021, 65.00));
        
        // Add sample customers
        agency.addCustomer(new Customer(101, "John Doe", "john@email.com", "123-456-7890"));
        agency.addCustomer(new Customer(102, "Jane Smith", "jane@email.com", "123-456-7891"));
    }
    
    // Utility methods for input validation
    private static int getIntInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input! Please enter a number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer
        return value;
    }
    
    private static double getDoubleInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input! Please enter a decimal number: ");
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine(); // Clear the buffer
        return value;
    }
    
    private static String getStringInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}

// Car class representing a rental car
class Car {
    private String carId;
    private String make;
    private String model;
    private int year;
    private double dailyRate;
    private boolean isAvailable;
    
    // Constructor
    public Car(String carId, String make, String model, int year, double dailyRate) {
        this.carId = carId;
        this.make = make;
        this.model = model;
        this.year = year;
        this.dailyRate = dailyRate;
        this.isAvailable = true; // Cars are available by default when added
    }
    
    // Getters and setters
    public String getCarId() { return carId; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public double getDailyRate() { return dailyRate; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    
    // Method to display car information
    public void displayInfo() {
        System.out.printf("ID: %s | %s %s %s | Rate: $%.2f/day | Status: %s%n",
                carId, year, make, model, dailyRate, 
                isAvailable ? "Available" : "Rented");
    }
}

// Customer class representing a rental customer
class Customer {
    private int customerId;
    private String name;
    private String email;
    private String phone;
    
    // Constructor
    public Customer(int customerId, String name, String email, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    
    // Getters
    public int getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    
    // Method to display customer information
    public void displayInfo() {
        System.out.printf("ID: %d | Name: %s | Email: %s | Phone: %s%n",
                customerId, name, email, phone);
    }
}

// Rental record class
class RentalRecord {
    private String carId;
    private int customerId;
    private LocalDate rentalDate;
    private LocalDate returnDate;
    private int rentalDays;
    private double totalCost;
    private boolean isActive;
    
    // Constructor for new rental
    public RentalRecord(String carId, int customerId, int rentalDays, double dailyRate) {
        this.carId = carId;
        this.customerId = customerId;
        this.rentalDate = LocalDate.now();
        this.rentalDays = rentalDays;
        this.returnDate = this.rentalDate.plusDays(rentalDays);
        this.totalCost = dailyRate * rentalDays;
        this.isActive = true;
    }
    
    // Method to complete rental
    public void completeRental() {
        this.isActive = false;
    }
    
    // Getters
    public String getCarId() { return carId; }
    public int getCustomerId() { return customerId; }
    public boolean isActive() { return isActive; }
    
    // Display rental information
    public void displayInfo() {
        System.out.printf("Car ID: %s | Customer ID: %d | Rental Date: %s | Return Date: %s | Cost: $%.2f | Status: %s%n",
                carId, customerId, rentalDate, returnDate, totalCost,
                isActive ? "Active" : "Completed");
    }
}

// Main Rental Agency class managing the entire system
class RentalAgency {
    private List<Car> cars;
    private List<Customer> customers;
    private List<RentalRecord> rentals;
    
    // Constructor
    public RentalAgency() {
        this.cars = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.rentals = new ArrayList<>();
    }
    
    // Add a new car to the fleet
    public void addCar(Car car) {
        cars.add(car);
    }
    
    // Add a new customer
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
    
    // Rent a car to a customer
    public boolean rentCar(String carId, int customerId, int days) {
        // Find the car
        Car car = findCarById(carId);
        if (car == null || !car.isAvailable()) {
            return false;
        }
        
        // Find the customer
        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            return false;
        }
        
        // Create rental record
        RentalRecord rental = new RentalRecord(carId, customerId, days, car.getDailyRate());
        rentals.add(rental);
        
        // Mark car as unavailable
        car.setAvailable(false);
        
        return true;
    }
    
    // Return a rented car
    public boolean returnCar(String carId) {
        // Find active rental for this car
        RentalRecord rental = findActiveRentalByCarId(carId);
        if (rental == null) {
            return false;
        }
        
        // Complete the rental
        rental.completeRental();
        
        // Mark car as available
        Car car = findCarById(carId);
        if (car != null) {
            car.setAvailable(true);
        }
        
        return true;
    }
    
    // Helper method to find car by ID
    private Car findCarById(String carId) {
        for (Car car : cars) {
            if (car.getCarId().equals(carId)) {
                return car;
            }
        }
        return null;
    }
    
    // Helper method to find customer by ID
    private Customer findCustomerById(int customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null;
    }
    
    // Helper method to find active rental by car ID
    private RentalRecord findActiveRentalByCarId(String carId) {
        for (RentalRecord rental : rentals) {
            if (rental.getCarId().equals(carId) && rental.isActive()) {
                return rental;
            }
        }
        return null;
    }
    
    // Display all available cars
    public void displayAvailableCars() {
        System.out.println("\n--- AVAILABLE CARS ---");
        boolean found = false;
        for (Car car : cars) {
            if (car.isAvailable()) {
                car.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No cars available at the moment.");
        }
    }
    
    // Display all customers
    public void displayAllCustomers() {
        System.out.println("\n--- REGISTERED CUSTOMERS ---");
        if (customers.isEmpty()) {
            System.out.println("No customers registered.");
        } else {
            for (Customer customer : customers) {
                customer.displayInfo();
            }
        }
    }
    
    // Display all rental records
    public void displayAllRentals() {
        System.out.println("\n--- RENTAL HISTORY ---");
        if (rentals.isEmpty()) {
            System.out.println("No rental records found.");
        } else {
            for (RentalRecord rental : rentals) {
                rental.displayInfo();
            }
        }
    }
}