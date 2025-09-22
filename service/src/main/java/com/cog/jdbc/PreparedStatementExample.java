package com.cog.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PreparedStatementExample {
  public static void main(String[] args) {
    String jdbcURL = "jdbc:mysql://localhost:3306/testdb"; // Change DB name
    String username = "root"; // Change username
    String password = "password"; // Change password

    try {
      // 1. Load JDBC driver
      Class.forName("com.mysql.cj.jdbc.Driver");

      // 2. Establish connection
      Connection connection = DriverManager.getConnection(jdbcURL, username, password);
      System.out.println("✅ Connected to database!");

      // -------------------------
      // INSERT Example
      // -------------------------
      String insertSQL = "INSERT INTO users (name, email) VALUES (?, ?)";
      PreparedStatement insertStmt = connection.prepareStatement(insertSQL);
      insertStmt.setString(1, "John Doe");
      insertStmt.setString(2, "john@example.com");
      int rowsInserted = insertStmt.executeUpdate();
      System.out.println("Rows inserted: " + rowsInserted);

      // -------------------------
      // UPDATE Example
      // -------------------------
      String updateSQL = "UPDATE users SET email=? WHERE name=?";
      PreparedStatement updateStmt = connection.prepareStatement(updateSQL);
      updateStmt.setString(1, "john.doe@newmail.com");
      updateStmt.setString(2, "John Doe");
      int rowsUpdated = updateStmt.executeUpdate();
      System.out.println("Rows updated: " + rowsUpdated);

      // -------------------------
      // SELECT Example
      // -------------------------
      String selectSQL = "SELECT id, name, email FROM users WHERE name=?";
      PreparedStatement selectStmt = connection.prepareStatement(selectSQL);
      selectStmt.setString(1, "John Doe");
      ResultSet rs = selectStmt.executeQuery();
      while (rs.next()) {
        System.out.println(
            rs.getInt("id") + " | " + rs.getString("name") + " | " + rs.getString("email"));
      }

      // -------------------------
      // DELETE Example
      // -------------------------
      String deleteSQL = "DELETE FROM users WHERE name=?";
      PreparedStatement deleteStmt = connection.prepareStatement(deleteSQL);
      deleteStmt.setString(1, "John Doe");
      int rowsDeleted = deleteStmt.executeUpdate();
      System.out.println("Rows deleted: " + rowsDeleted);

      // 3. Close connection
      connection.close();
      System.out.println("✅ Connection closed.");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
