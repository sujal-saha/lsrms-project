package com.vit.lsrms.util;

import com.vit.lsrms.model.Book;
import com.vit.lsrms.model.Student;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataInitializer {
    public static void checkAndInitializeData() {
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }

        File bookFile = new File("data/books.txt");
        if (!bookFile.exists()) {
            List<Book> defaultBooks = new ArrayList<>();
            defaultBooks.add(new Book("B101", "Core Java Complete Ref", "Herbert Schildt", false));
            defaultBooks.add(new Book("B102", "Data Structures in Java", "Robert Lafore", false));
            defaultBooks.add(new Book("B103", "Operating System Concepts", "Silberschatz", false));
            defaultBooks.add(new Book("B104", "Clean Code", "Robert C. Martin", false));
            FileHandler.saveBooks(defaultBooks);
        }

        File studentFile = new File("data/students.txt");
        if (!studentFile.exists()) {
            List<Student> defaultStudents = new ArrayList<>();
            defaultStudents.add(new Student("23BCEN101", "Rahul Sharma", 250.00));
            defaultStudents.add(new Student("23BCEN102", "Ananya Verma", 100.00));
            FileHandler.saveStudents(defaultStudents);
        }
    }
}
