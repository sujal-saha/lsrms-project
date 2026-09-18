package com.vit.lsrms.service;

import com.vit.lsrms.model.Book;
import java.util.List;

// Demonstrating Runnable interface for multithreading Requirement
public class BookSearchWorker implements Runnable {
    private List<Book> books;
    private String query;

    public BookSearchWorker(List<Book> books, String query) {
        this.books = books;
        this.query = query.toLowerCase();
    }

    @Override
    public void run() {
        System.out.println("\n[Background Thread: Searching catalog for '" + query + "'...]");
        boolean found = false;
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(query) || b.getAuthor().toLowerCase().contains(query)) {
                System.out.println("  -> MATCH: " + b);
                found = true;
            }
        }
        if (!found) {
            System.out.println("  -> No matching books found.");
        }
    }
}
