package ua.com.service;

import ua.com.connection.Connector;
import ua.com.dao.AddressDAO;
import ua.com.model.Address;
import ua.com.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressService implements AddressDAO {

  private Connection connection;
  private String insert = "insert into orderdb.address(name, number) values(?,?)";
  private String update = "update orderdb.address set name=?, number=? values(?,?) where id=?";
  private String readAll = "select * from orderdb.address";
  private String read = "select * from orderdb.address where id=?";
  private String delete = "delete from orderdb.address where id=?";

  @Override
  public boolean create(Address obj) {
    connection = Connector.getConnection();
    try {
      PreparedStatement ps = connection.prepareStatement(insert);
      ps.setString(1, obj.getName());
      ps.setInt(2, obj.getNumber());
      ps.execute();
      connection.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean update(Address obj) {
    connection = Connector.getConnection();
    try {
      PreparedStatement ps = connection.prepareStatement(update);
      ps.setString(1, obj.getName());
      ps.setInt(2, obj.getNumber());
      ps.setLong(3, obj.getId());
      ps.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public Address get(long id) {
    connection = Connector.getConnection();
    try {
      PreparedStatement ps = connection.prepareStatement(read);
      ps.setLong(1, id);
      ResultSet rs = ps.executeQuery();
      Address address = null;
      if (rs.next()) {
        long addressId = rs.getLong(1);
        String name = rs.getString(2);
        int number = rs.getInt(3);
        address = new Address(addressId, name, number);
      }
      connection.close();
      return address;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public boolean delete(Address obj) {
    return ServiceUtils.delete(obj, delete);
  }

  @Override
  public boolean delete(long id) {
    return ServiceUtils.delete(id, delete);
  }

  @Override
  public List<Address> getAll() {
    List<Address> addresses = new ArrayList<>();
    connection = Connector.getConnection();
    try {
      PreparedStatement ps = connection.prepareStatement(readAll);
      ResultSet rs = ps.executeQuery();
      while(rs.next()){
        long id = rs.getLong(1);
        String name = rs.getString(2);
        int number = rs.getInt(3);
        addresses.add(new Address(id, name, number));
      }
      connection.close();
      return addresses;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return addresses;
  }
}
