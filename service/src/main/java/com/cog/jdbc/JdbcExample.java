package com.cog.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcExample {
  public static void main(String[] args) {
    // Database credentials
    String jdbcURL = "jdbc:mysql://localhost:3306/testdb"; // Change DB name
    String username = "root"; // Change username
    String password = "password"; // Change password

    try {
      // 1. Load and register JDBC driver (optional for newer versions of Java)
      Class.forName("com.mysql.cj.jdbc.Driver");

      // 2. Establish connection
      Connection connection = DriverManager.getConnection(jdbcURL, username, password);
      System.out.println("Connected to database!");

      // 3. Create statement
      Statement statement = connection.createStatement();

      // 4. Execute query
      String sql = "SELECT id, name, email FROM users"; // Change table/columns
      ResultSet resultSet = statement.executeQuery(sql);

      // 5. Process results
      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        System.out.println(id + " | " + name + " | " + email);
      }

      // 6. Close resources
      resultSet.close();
      statement.close();
      connection.close();
      System.out.println("Connection closed.");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
