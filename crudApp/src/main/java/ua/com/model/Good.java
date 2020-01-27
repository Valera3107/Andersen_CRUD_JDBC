package ua.com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Good implements ModelInterface {
  private long id;
  private String name;
  private String description;
  private double price;
  private List<City> cityList = new ArrayList<>();
  private List<User> users = new ArrayList<>();

  public Good() {
  }

  public Good(String name, String description, double price) {
    this.name = name;
    this.description = description;
    this.price = price;
  }

  public Good(long id, String name, String description, double price) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
  }

  public Good(long id, String name, String description, double price, List<City> cityList) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.cityList = cityList;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public List<City> getCityList() {
    return cityList;
  }

  public void setCityList(List<City> cityList) {
    this.cityList = cityList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Good good = (Good) o;
    return id == good.id &&
      Double.compare(good.price, price) == 0 &&
      Objects.equals(name, good.name) &&
      Objects.equals(description, good.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, price);
  }

  @Override
  public String toString() {
    return "Good{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", description='" + description + '\'' +
      ", price=" + price + "$" +
      '}';
  }
}
