import java.sql.*;
import java.util.Scanner;

public class Database {
    public static void main(String args[]) {
        String url = "jdbc:mysql://localhost:3306/studentdb?serverTimezone=UTC";
        String user = "root";
        String pass = "06978";


        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected to Database!");

            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("\n--- Student Database Menu ---");
                System.out.println("1. Add Student");
                System.out.println("2. View All Students");
                System.out.println("3. Search Student by Roll No");
                System.out.println("4. Update Student GPA");
                System.out.println("5. Delete Student");
                System.out.println("6. Exit");
                System.out.print("Enter choice: ");

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1 -> addStudent(conn, sc);
                    case 2 -> viewStudents(conn);
                    case 3 -> searchStudent(conn, sc);
                    case 4 -> updateStudent(conn, sc);
                    case 5 -> deleteStudent(conn, sc);
                    case 6 -> { 
                        System.out.println(" Exiting..."); 
                        conn.close();
                        return; 
                    }
                    default -> System.out.println("Invalid choice");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add student
    private static void addStudent(Connection conn, Scanner sc) throws SQLException {
            System.out.print("Enter roll no.: ");
            int roll = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter name: ");
            String name = sc.nextLine();
            System.out.print("Enter dept.: ");
            String dept = sc.nextLine();
            System.out.print("Enter gpa: ");
            float gpa = sc.nextFloat();

            String query = "INSERT INTO students VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, roll);
                stmt.setString(2, name);
                stmt.setString(3, dept);
                stmt.setFloat(4, gpa);
                stmt.executeUpdate();
                System.out.println("Student added!");
            }
        }

    // View all students
    private static void viewStudents(Connection conn) throws SQLException {
        String query = "SELECT * FROM students";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("\n--- Student Records ---");
            while (rs.next()) {
                System.out.println(rs.getInt("roll_no") + " | " +
                                   rs.getString("name") + " | " +
                                   rs.getString("department") + " | GPA: " +
                                   rs.getFloat("gpa"));
            }
        }
    }

    // Search student
    private static void searchStudent(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter roll no.: ");
        int roll = sc.nextInt();

        String query = "SELECT * FROM students WHERE roll_no = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, roll);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Found: " + rs.getString("name") +
                                   " | Dept: " + rs.getString("department") +
                                   " | GPA: " + rs.getFloat("gpa"));
            } else {
                System.out.println("Student not found.");
            }
        }
    }

    // Update GPA
    private static void updateStudent(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter roll no.: ");
        int roll = sc.nextInt();
        System.out.print("Enter new GPA: ");
        float gpa = sc.nextFloat();

        String query = "UPDATE students SET gpa = ? WHERE roll_no = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setFloat(1, gpa);
            stmt.setInt(2, roll);
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("GPA updated!");
            else System.out.println("Student not found.");
        }
    }

    // Delete student
    private static void deleteStudent(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter roll no.: ");
        int roll = sc.nextInt();

        String query = "DELETE FROM students WHERE roll_no = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, roll);
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("Student deleted!");
            else System.out.println("Student not found.");
        }
    }
}
