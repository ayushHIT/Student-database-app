CREATE DATABASE studentdb;
USE studentdb;

CREATE TABLE students (
    roll_no INT PRIMARY KEY,
    name VARCHAR(100),
    department VARCHAR(50),
    gpa FLOAT
);