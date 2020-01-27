package ua.com.service;

import ua.com.connection.Connector;
import ua.com.model.ModelInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ServiceUtils {
  private static Connection connection;

  public static <T extends ModelInterface> boolean delete(T obj, String delete) {
    connection = Connector.getConnection();
    try {
      PreparedStatement ps = connection.prepareStatement(delete);
      ps.setLong(1, obj.getId());
      ps.executeUpdate();
      connection.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static <T extends ModelInterface> boolean delete(Long id, String delete) {
    connection = Connector.getConnection();
    try {
      PreparedStatement ps = connection.prepareStatement(delete);
      ps.setLong(1, id);
      ps.executeUpdate();
      connection.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
}
