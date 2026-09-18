package com.vit.lsrms;

import com.vit.lsrms.exception.BookNotFoundException;
import com.vit.lsrms.exception.InsufficientBalanceException;
import com.vit.lsrms.model.Book;
import com.vit.lsrms.model.Student;
import com.vit.lsrms.service.BookSearchWorker;
import com.vit.lsrms.service.LibraryService;
import com.vit.lsrms.util.DataInitializer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize file setup and initial seed data if not existing
        DataInitializer.checkAndInitializeData();
        
        LibraryService libraryService = new LibraryService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================================");
        System.out.println("  LIBRARY & STUDENT RESOURCE MANAGEMENT SYSTEM  ");
        System.out.println("          (VIT CSE Departmental Project)         ");
        System.out.println("=================================================");

        while (true) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. View All Books");
            System.out.println("2. Add New Book");
            System.out.println("3. Async Search Book (Multithreaded)");
            System.out.println("4. View All Students");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            String inputChoice = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(inputChoice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("\n--- Catalog ---");
                    for (Book b : libraryService.getAllBooks()) {
                        System.out.println(b);
                    }
                    break;

                case 2:
                    System.out.print("Enter Book ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    libraryService.addBook(id, title, author);
                    break;

                case 3:
                    System.out.print("Enter search keyword (Title/Author): ");
                    String query = scanner.nextLine();
                    // Spawning a background thread to fulfill syllabus concurrency criteria
                    Thread searchThread = new Thread(new BookSearchWorker(libraryService.getAllBooks(), query));
                    searchThread.start();
                    try {
                        searchThread.join(); // Wait for thread to finish printing output
                    } catch (InterruptedException e) {
                        System.out.println("Thread interrupted.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- Registered Students ---");
                    for (Student s : libraryService.getAllStudents()) {
                        System.out.println(s);
                    }
                    break;

                case 5:
                    System.out.print("Enter Student Reg No: ");
                    String sReg = scanner.nextLine();
                    System.out.print("Enter Book ID: ");
                    String bId = scanner.nextLine();
                    try {
                        libraryService.issueBook(sReg, bId);
                    } catch (BookNotFoundException | InsufficientBalanceException e) {
                        System.out.println("Transaction Failed: " + e.getMessage());
                    }
                    break;

                case 6:
                    System.out.print("Enter Student Reg No: ");
                    String rReg = scanner.nextLine();
                    System.out.print("Enter Book ID: ");
                    String rId = scanner.nextLine();
                    try {
                        libraryService.returnBook(rReg, rId);
                    } catch (BookNotFoundException e) {
                        System.out.println("Transaction Failed: " + e.getMessage());
                    }
                    break;

                case 7:
                    System.out.println("Saving data and exiting... Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please select from options 1-7.");
            }
        }
    }
}
