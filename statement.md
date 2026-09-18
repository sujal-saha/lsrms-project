# Problem Statement & System Scope

## Problem Statement
In educational institutions, managing resource allocation and tracking library operations often involves fragmented record-keeping. Existing command-line tools either lack proper data persistence or crash unexpectedly due to poor exception handling.

## Scope of the Project
The Library & Student Resource Management System (LSRMS) is a CLI-based Core Java application tailored for academic departments. The project focuses on handling core transaction logic (borrowing, returning, catalog updates) with multi-threaded async search capabilities and text-file binary/plain persistence.

## Target Users
- **Department Librarians / Administrators:** Manage books and check student records.
- **Students:** Issue/Return books and search resource catalogs.

## High-Level Features
1. Book Inventory CRUD Operations.
2. Multithreaded Search Engine via Java Threads.
3. Custom Exception Validation (e.g., balance limits, missing book entries).
4. Flat-file persistent data storage across sessions.
