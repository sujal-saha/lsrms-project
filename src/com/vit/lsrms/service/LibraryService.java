package com.vit.lsrms.service;

import com.vit.lsrms.exception.BookNotFoundException;
import com.vit.lsrms.exception.InsufficientBalanceException;
import com.vit.lsrms.model.Book;
import com.vit.lsrms.model.Student;
import com.vit.lsrms.util.FileHandler;

import java.util.List;

public class LibraryService {
    private List<Book> books;
    private List<Student> students;

    public LibraryService() {
        this.books = FileHandler.loadBooks();
        this.students = FileHandler.loadStudents();
    }

    public List<Book> getAllBooks() { return books; }
    public List<Student> getAllStudents() { return students; }

    public void addBook(String id, String title, String author) {
        books.add(new Book(id, title, author, false));
        FileHandler.saveBooks(books);
        System.out.println("Book added successfully!");
    }

    public void issueBook(String regNo, String bookId) throws BookNotFoundException, InsufficientBalanceException {
        Student student = findStudent(regNo);
        Book book = findBook(bookId);

        if (student == null) {
            System.out.println("Error: Student registration number not found.");
            return;
        }

        if (book.isIssued()) {
            System.out.println("Error: Book is already issued to another student.");
            return;
        }

        if (student.getWalletBalance() < 50.0) {
            throw new InsufficientBalanceException("Student balance is too low (Min Rs. 50 required for security deposit).");
        }

        book.setIssued(true);
        student.getBorrowedBookIds().add(bookId);

        FileHandler.saveBooks(books);
        FileHandler.saveStudents(students);
        System.out.println("Book '" + book.getTitle() + "' successfully issued to " + student.getName());
    }

    public void returnBook(String regNo, String bookId) throws BookNotFoundException {
        Student student = findStudent(regNo);
        Book book = findBook(bookId);

        if (student == null || !student.getBorrowedBookIds().contains(bookId)) {
            System.out.println("Error: Transaction record invalid or book not issued to this student.");
            return;
        }

        book.setIssued(false);
        student.getBorrowedBookIds().remove(bookId);

        FileHandler.saveBooks(books);
        FileHandler.saveStudents(students);
        System.out.println("Book successfully returned!");
    }

    public Book findBook(String bookId) throws BookNotFoundException {
        for (Book b : books) {
            if (b.getBookId().equalsIgnoreCase(bookId)) return b;
        }
        throw new BookNotFoundException("Book with ID '" + bookId + "' was not found in catalog.");
    }

    public Student findStudent(String regNo) {
        for (Student s : students) {
            if (s.getRegNo().equalsIgnoreCase(regNo)) return s;
        }
        return null;
    }
}
