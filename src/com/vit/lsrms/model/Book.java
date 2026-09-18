package com.vit.lsrms.model;

import java.io.Serializable;

public class Book implements Serializable {
    private String bookId;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(String bookId, String title, String author, boolean isIssued) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isIssued = isIssued;
    }

    // Getters and Setters
    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return isIssued; }
    public void setIssued(boolean issued) { isIssued = issued; }

    // Helper method to convert object to plain text for storage
    public String toFileFormat() {
        return bookId + "," + title + "," + author + "," + isIssued;
    }

    public static Book fromFileFormat(String line) {
        String[] parts = line.split(",");
        if (parts.length == 4) {
            return new Book(parts[0], parts[1], parts[2], Boolean.parseBoolean(parts[3]));
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("ID: %-6s | Title: %-25s | Author: %-15s | Status: %s", 
                bookId, title, author, (isIssued ? "Issued" : "Available"));
    }
}
