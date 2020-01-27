package ua.com.model;

import java.util.Objects;

public class Address implements ModelInterface{
  private long id;
  private String name;
  private int number;
  private int userId;

  public Address() {
  }

  public Address(long id, String name, int number) {
    this.id = id;
    this.name = name;
    this.number = number;
  }

  public Address(long id, String name, int number, int userId) {
    this.id = id;
    this.name = name;
    this.number = number;
    this.userId = userId;
  }

  public Address(String name, int number) {
    this.name = name;
    this.number = number;
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

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Address address = (Address) o;
    return id == address.id &&
      number == address.number &&
      Objects.equals(name, address.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, number);
  }

  @Override
  public String toString() {
    return "Address{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", number=" + number +
      '}';
  }
}
