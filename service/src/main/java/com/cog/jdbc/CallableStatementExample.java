package com.cog.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/*

DELIMITER //
CREATE PROCEDURE getUserById(IN userId INT)
BEGIN
    SELECT id, name, email FROM users WHERE id = userId;
END //
DELIMITER ;




 */

public class CallableStatementExample {
  public static void main(String[] args) {
    String jdbcURL = "jdbc:mysql://localhost:3306/testdb"; // Change DB name
    String username = "root";
    String password = "password";

    try {
      // 1. Load driver
      Class.forName("com.mysql.cj.jdbc.Driver");

      // 2. Establish connection
      Connection connection = DriverManager.getConnection(jdbcURL, username, password);
      System.out.println("✅ Connected to database!");

      // 3. Prepare callable statement for stored procedure
      String sql = "{CALL getUserById(?)}"; // ? = input parameter
      CallableStatement callableStatement = connection.prepareCall(sql);

      // 4. Set input parameter
      callableStatement.setInt(1, 1); // Pass userId = 1

      // 5. Execute stored procedure
      ResultSet rs = callableStatement.executeQuery();

      // 6. Process results
      while (rs.next()) {
        System.out.println(
            rs.getInt("id") + " | " + rs.getString("name") + " | " + rs.getString("email"));
      }

      // 7. Close connection
      connection.close();
      System.out.println("✅ Connection closed.");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
