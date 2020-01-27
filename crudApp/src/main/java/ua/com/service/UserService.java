package ua.com.service;

import ua.com.connection.Connector;
import ua.com.dao.UserDAO;
import ua.com.model.Address;
import ua.com.model.Good;
import ua.com.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService implements UserDAO {

  private Connection connection;
  private String insert = "insert into orderdb.user(name, surname, phone, email) values(?,?,?,?)";
  private String update = "update orderdb.user set name=?, surname=?, phone=?, email=? where id=?";
  private String delete = "delete from orderdb.user where id=?";
  private String readAll = "select * from orderdb.user";
  private String read = "select * from orderdb.user where id=?";
  private String makeOrder = "insert into orderdb.orders(user_id, goods_id) values(?,?)";
  private String deleteOrder = "delete from orderdb.orders where goods_id=? and user_id=?";
  private String getUserOrders =
    "select * from orderdb.user u inner join orderdb.goods g inner join orderdb.orders r on u.id = r.user_id and g.id = r.goods_id where u.id = ?";
  private String addAddress = "update orderdb.address set user_id=? where id=?";
  private String deleteAddressFromUser = "update orderdb.address set user_id=null where user_id=?";
  private String getUserAddresses = "select * from orderdb.address where user_id=?";

  public List<Address> getUserAddresses(long userId){
    List<Address> addresses = new ArrayList<>();
    connection = Connector.getConnection();
    try{
      PreparedStatement pr = connection.prepareStatement(getUserAddresses);
      pr.setLong(1, userId);
      ResultSet rs = pr.executeQuery();
      while (rs.next()){
        long id = rs.getLong(1);
        String name = rs.getString(2);
        int number = rs.getInt(3);
        addresses.add(new Address(id, name, number));
      }
      rs.close();
      pr.close();
      connection.close();
      return addresses;
    } catch (Exception ex){
      System.out.println("Invalid input!");
    }
    return addresses;
  }

  public boolean addAddress(long addressId, long userId){
    connection = Connector.getConnection();
    try{
      PreparedStatement ps = connection.prepareStatement(addAddress);
      ps.setLong(1, userId);
      ps.setLong(2, addressId);
      ps.executeUpdate();
      ps.close();
      connection.close();
      return true;
    }catch (Exception ex){
      System.out.println("Invalid input!");
    }
    return false;
  }

  public boolean deleteAddress(long userId){
    connection = Connector.getConnection();
    try{
      PreparedStatement ps = connection.prepareStatement(deleteAddressFromUser);
      ps.setLong(1, userId);
      ps.executeUpdate();
      ps.close();
      connection.close();
      return true;
    }catch (Exception ex){
      System.out.println("Invalid input!");
    }
    return false;
  }

  public boolean makeOrder(long goodId, long userId) {
    connection = Connector.getConnection();
    try {
      PreparedStatement ps = connection.prepareStatement(makeOrder);
      ps.setLong(1, userId);
      ps.setLong(2, goodId);
      ps.execute();
      return true;
    } catch (Exception ex) {
      System.out.println("Something went wrong!");
    }
    return false;
  }

  public boolean deleteOrder(long goodId, long userId) {
    connection = Connector.getConnection();
    try {
      PreparedStatement ps = connection.prepareStatement(deleteOrder);
      ps.setLong(1, goodId);
      ps.setLong(2, userId);
      ps.executeUpdate();
      connection.close();
      ps.close();
      return true;
    } catch (Exception ex) {
      System.out.println("Invalid input!");
    }
    return false;
  }

  public List<Good> getOrders(long id) {
    List<Good> goods = new ArrayList<>();
    connection = Connector.getConnection();
    try {
      PreparedStatement ps = connection.prepareStatement(getUserOrders);
      ps.setLong(1, id);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        long goodsId = rs.getLong(6);
        String name = rs.getString(7);
        String description = rs.getString(8);
        double price = rs.getDouble(9);
        goods.add(new Good(goodsId, name, description, price));
      }
      connection.close();
      ps.close();
    } catch (Exception ex) {
      System.out.println("Invalid Input!");
    }
    return goods;
  }

  @Override
  public boolean create(User obj) {
    connection = Connector.getConnection();
    try {
      PreparedStatement ps = connection.prepareStatement(insert);
      fillFields(obj, ps);
      ps.execute();
      connection.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  private void fillFields(User obj, PreparedStatement ps) throws SQLException {
    ps.setString(1, obj.getName());
    ps.setString(2, obj.getSurname());
    ps.setString(3, obj.getPhone());
    ps.setString(4, obj.getEmail());
  }

  @Override
  public boolean update(User obj) {
    connection = Connector.getConnection();
    try {
      PreparedStatement ps = connection.prepareStatement(update);
      fillFields(obj, ps);
      ps.setLong(5, obj.getId());
      ps.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public User get(long id) {
    connection = Connector.getConnection();
    try {
      PreparedStatement ps = connection.prepareStatement(read);
      ps.setLong(1, id);
      ResultSet rs = ps.executeQuery();
      User user = null;
      if (rs.next()) {
        long userId = rs.getLong(1);
        String name = rs.getString(2);
        String surname = rs.getString(3);
        String phone = rs.getString(4);
        String email = rs.getString(5);
        user = new User(userId, name, surname, phone, email);
      }
      connection.close();
      return user;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public boolean delete(User obj) {
    return ServiceUtils.delete(obj, delete);
  }

  @Override
  public boolean delete(long id) {
    return ServiceUtils.delete(id, delete);
  }

  @Override
  public List<User> getAll() {
    List<User> users = new ArrayList<>();
    connection = Connector.getConnection();
    try {
      PreparedStatement ps = connection.prepareStatement(readAll);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        long id = rs.getLong(1);
        String name = rs.getString(2);
        String surname = rs.getString(3);
        String phone = rs.getString(4);
        String email = rs.getString(5);
        users.add(new User(id, name, surname, phone, email));
      }
      connection.close();
      return users;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return users;
  }
}
