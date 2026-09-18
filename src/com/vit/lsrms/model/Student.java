package com.vit.lsrms.model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String regNo;
    private String name;
    private double walletBalance;
    private List<String> borrowedBookIds;

    public Student(String regNo, String name, double walletBalance) {
        this.regNo = regNo;
        this.name = name;
        this.walletBalance = walletBalance;
        this.borrowedBookIds = new ArrayList<>();
    }

    public String getRegNo() { return regNo; }
    public String getName() { return name; }
    public double getWalletBalance() { return walletBalance; }
    public void setWalletBalance(double walletBalance) { this.walletBalance = walletBalance; }
    public List<String> getBorrowedBookIds() { return borrowedBookIds; }

    public String toFileFormat() {
        String books = String.join(";", borrowedBookIds);
        if (books.isEmpty()) books = "NONE";
        return regNo + "," + name + "," + walletBalance + "," + books;
    }

    public static Student fromFileFormat(String line) {
        String[] parts = line.split(",");
        if (parts.length >= 3) {
            Student s = new Student(parts[0], parts[1], Double.parseDouble(parts[2]));
            if (parts.length == 4 && !parts[3].equals("NONE")) {
                String[] bookIds = parts[3].split(";");
                for (String id : bookIds) {
                    s.getBorrowedBookIds().add(id);
                }
            }
            return s;
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("RegNo: %-10s | Name: %-15s | Balance: Rs.%.2f | Books Issued: %d", 
                regNo, name, walletBalance, borrowedBookIds.size());
    }
}
