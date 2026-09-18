package com.vit.lsrms.util;

import com.vit.lsrms.model.Book;
import com.vit.lsrms.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String BOOK_FILE = "data/books.txt";
    private static final String STUDENT_FILE = "data/students.txt";

    public static void saveBooks(List<Book> books) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOK_FILE))) {
            for (Book b : books) {
                writer.write(b.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving book data: " + e.getMessage());
        }
    }

    public static List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        File file = new File(BOOK_FILE);
        if (!file.exists()) return books;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Book b = Book.fromFileFormat(line);
                if (b != null) books.add(b);
            }
        } catch (IOException e) {
            System.err.println("Error loading book data: " + e.getMessage());
        }
        return books;
    }

    public static void saveStudents(List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENT_FILE))) {
            for (Student s : students) {
                writer.write(s.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving student data: " + e.getMessage());
        }
    }

    public static List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        File file = new File(STUDENT_FILE);
        if (!file.exists()) return students;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Student s = Student.fromFileFormat(line);
                if (s != null) students.add(s);
            }
        } catch (IOException e) {
            System.err.println("Error loading student data: " + e.getMessage());
        }
        return students;
    }
}
