package ua.com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
  private static final String URL = "jdbc:mysql://localhost:3306/orderdb" + "?serverTimezone=UTC";
  private static final String USER = "root";
  private static final String PASSWORD = "zenbook3107";

  public static Connection getConnection() {
    Connection connection;
    try {
      connection = DriverManager.getConnection(URL, USER, PASSWORD);
      return connection;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
