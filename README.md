# Student Database Management System (Java + JDBC + MySQL)

A **menu-driven CLI application** built in Java to perform CRUD operations (Create, Read, Update, Delete) on a student database.  
The project demonstrates the use of **JDBC** for database connectivity and SQL queries with **MySQL**.

---

## Features
- **Add Student** – Insert a new student record with roll number, name, department, and GPA.
- **View Students** – List all student records from the database.
- **Search Student** – Look up a student by roll number.
- **Update GPA** – Modify the GPA of an existing student.
- **Delete Student** – Remove a student record by roll number.
- **Error Handling** – Handles duplicate roll numbers, missing records, and SQL exceptions gracefully.

---

## Tech Stack
- **Java 17+**
- **JDBC (Java Database Connectivity)**
- **MySQL 8.x**
- **MySQL Connector/J (8.0.33)**

---

## Project structure
├── src/
│ └── Database.java # Main CLI application
│
├── lib/
│ └── mysql-connector-j-8.0.33.jar # JDBC driver (add here manually)
│
├── studentDB.sql # Database schema setup script
│
└── README.md # Project documentation

---

## Setup Information

1. Clone the repository
```bash
git clone https://github.com/ayushHIT/StudentDB-Java.git
cd StudentDB-Java

2. Set up the database

Log in to MySQL and run:
source studentdb.sql;

This creates the studentdb database with a students table.

3. Compile the program

On Windows (PowerShell/CMD):

javac -cp .;lib/mysql-connector-j-8.0.33.jar src/Database.java


On Linux/Mac:

javac -cp .:lib/mysql-connector-j-8.0.33.jar src/Database.java

4. Run the program

On Windows:

java -cp .;lib/mysql-connector-j-8.0.33.jar Database


On Linux/Mac:

java -cp .:lib/mysql-connector-j-8.0.33.jar Database