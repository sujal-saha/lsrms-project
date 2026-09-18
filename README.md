# Library & Student Resource Management System (LSRMS)

A modular, command-line Java application built for the **Programming in Java** course evaluation.

## Features
- **Book Management:** View and add catalog entries.
- **Student Profile Tracking:** Maintain security deposits and transaction history.
- **Multithreaded Search:** Performs asynchronously using `java.lang.Runnable`.
- **Robust Exception Handling:** Prevents crashes using custom exception classes (`BookNotFoundException`, `InsufficientBalanceException`).
- **File-Based Persistence:** Saves data dynamically into `data/*.txt` files.

## Applied Java Concepts
- Object-Oriented Programming (Abstraction, Encapsulation, Polymorphism)
- Java Collections Framework (`ArrayList`, `List`)
- File I/O (`BufferedReader`, `BufferedWriter`, `File`)
- Multithreading & Concurrency (`Thread`, `Runnable`)
- Custom Exception Handling (`throws`, `try-catch`)

## How to Build and Run (Terminal/CLI)
```bash
# Clone or navigate to the repository directory
cd lsrms-project

# Compile the source code into 'bin' directory
javac -d bin src/com/vit/lsrms/*.java src/com/vit/lsrms/*/*.java

# Execute the application
java -cp bin com.vit.lsrms.Main
