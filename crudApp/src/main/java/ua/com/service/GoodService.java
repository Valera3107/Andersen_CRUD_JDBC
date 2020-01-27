package ua.com.service;

import ua.com.connection.Connector;
import ua.com.dao.GoodDAO;
import ua.com.model.City;
import ua.com.model.Good;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodService implements GoodDAO {
  private Connection connection;
  private String insert = "insert into orderdb.goods(name, description, price) values(?,?,?)";
  private String update = "update orderdb.goods set name=?, description=?, price=? where id=?";
  private String read = "select * from orderdb.goods where id=?";
  private String readAll = "select * from orderdb.goods";
  private String delete = "delete from orderdb.goods where id=?";
  private String addCity = "insert into orderdb.city_has_goods(city_id, goods_id) values(?,?)";
  private String deleteCityFromGood = "delete from orderdb.city_has_goods where goods_id=? and city_id=?";
  private String getGoodCities = "select * from orderdb.goods g inner join orderdb.city c inner join orderdb.city_has_goods cg on g.id = cg.goods_id and c.id = cg.city_id where g.id=?";

  public List<City> getCities(long goodId) {
    List<City> cities = new ArrayList<>();
    connection = Connector.getConnection();
    try {
      PreparedStatement pr = connection.prepareStatement(getGoodCities);
      pr.setLong(1, goodId);
      ResultSet rs = pr.executeQuery();
      while (rs.next()) {
        long id = rs.getLong(5);
        String name = rs.getString(6);
        String code = rs.getString(7);
        cities.add(new City(id, name, code));
      }
      rs.close();
      pr.close();
      connection.close();
      return cities;
    } catch (Exception ex) {
      System.out.println("Invalid input!");
    }
    return cities;
  }

  public boolean deleteCityFromGood(long cityId, long goodId) {
    connection = Connector.getConnection();
    try {
      PreparedStatement ps = connection.prepareStatement(deleteCityFromGood);
      ps.setLong(1, goodId);
      ps.setLong(2, cityId);
      ps.executeUpdate();
      connection.close();
      return true;
    } catch (Exception ex) {
      System.out.println("Invalid input!");
    }
    return false;
  }

  public boolean addCityToGood(long goodId, long cityId) {
    connection = Connector.getConnection();
    try {
      PreparedStatement ps = connection.prepareStatement(addCity);
      ps.setLong(1, cityId);
      ps.setLong(2, goodId);
      ps.execute();
      connection.close();
      return true;
    } catch (Exception ex) {
      System.out.println("Invalid input data!");
    }
    return false;
  }

  @Override
  public boolean create(Good obj) {
    connection = Connector.getConnection();
    try {
      PreparedStatement ps = connection.prepareStatement(insert);
      ps.setString(1, obj.getName());
      ps.setString(2, obj.getDescription());
      ps.setDouble(3, obj.getPrice());
      ps.execute();
      connection.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean update(Good obj) {
    connection = Connector.getConnection();
    try {
      PreparedStatement ps = connection.prepareStatement(update);
      ps.setString(1, obj.getName());
      ps.setString(2, obj.getDescription());
      ps.setDouble(3, obj.getPrice());
      ps.setLong(4, obj.getId());
      ps.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public Good get(long id) {
    connection = Connector.getConnection();
    try {
      PreparedStatement ps = connection.prepareStatement(read);
      ps.setLong(1, id);
      ResultSet rs = ps.executeQuery();
      Good good = null;
      if (rs.next()) {
        long goodId = rs.getLong(1);
        String name = rs.getString(2);
        String description = rs.getString(3);
        double price = rs.getDouble(4);
        good = new Good(goodId, name, description, price);
      }
      connection.close();
      return good;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public boolean delete(Good obj) {
    return ServiceUtils.delete(obj, delete);
  }

  @Override
  public boolean delete(long id) {
    return ServiceUtils.delete(id, delete);
  }

  @Override
  public List<Good> getAll() {
    List<Good> addresses = new ArrayList<>();
    connection = Connector.getConnection();
    try {
      PreparedStatement ps = connection.prepareStatement(readAll);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        long goodId = rs.getLong(1);
        String name = rs.getString(2);
        String description = rs.getString(3);
        double price = rs.getDouble(4);
        addresses.add(new Good(goodId, name, description, price));
      }
      connection.close();
      return addresses;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return addresses;
  }
}
