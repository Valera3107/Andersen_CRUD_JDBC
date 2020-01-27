package ua.com.service;

import ua.com.connection.Connector;
import ua.com.dao.CityDAO;
import ua.com.model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityService implements CityDAO {
  private Connection connection;
  private String insert = "insert into orderdb.city(city, code) values(?,?)";
  private String update = "update orderdb.city set city=?, code=? where id=?";
  private String delete = "delete from orderdb.city where id=?";
  private String read = "select * from orderdb.city where id=?";
  private String readAll = "select * from orderdb.city";

  @Override
  public boolean create(City obj) {
    connection = Connector.getConnection();
    try {
      PreparedStatement ps = connection.prepareStatement(insert);
      ps.setString(1, obj.getCity());
      ps.setString(2, obj.getCode());
      ps.execute();
      connection.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean update(City obj) {
    connection = Connector.getConnection();
    try {
      PreparedStatement ps = connection.prepareStatement(update);
      ps.setString(1, obj.getCity());
      ps.setString(2, obj.getCode());
      ps.setLong(3, obj.getId());
      ps.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public City get(long id) {
    connection = Connector.getConnection();
    try {
      PreparedStatement ps = connection.prepareStatement(read);
      ps.setLong(1, id);
      ResultSet rs = ps.executeQuery();
      City city = null;
      if (rs.next()) {
        long cityId = rs.getLong(1);
        String name = rs.getString(2);
        String code = rs.getString(3);
        city = new City(cityId, name, code);
      }
      connection.close();
      return city;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public boolean delete(City obj) {
    return ServiceUtils.delete(obj, delete);
  }

  @Override
  public boolean delete(long id) {
    return ServiceUtils.delete(id, delete);
  }

  @Override
  public List<City> getAll() {
    List<City> addresses = new ArrayList<>();
    connection = Connector.getConnection();
    try {
      PreparedStatement ps = connection.prepareStatement(readAll);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        long id = rs.getLong(1);
        String name = rs.getString(2);
        String code = rs.getString(3);
        addresses.add(new City(id, name, code));
      }
      connection.close();
      return addresses;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return addresses;
  }
}
