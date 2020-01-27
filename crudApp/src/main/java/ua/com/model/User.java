package ua.com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User implements ModelInterface{
  private long id;
  private String name;
  private String surname;
  private String phone;
  private String email;
  private List<Good> goodList = new ArrayList<>();

  public User() {
  }

  public User(String name, String surname, String phone, String email) {
    this.name = name;
    this.surname = surname;
    this.phone = phone;
    this.email = email;
  }

  public User(long id, String name, String surname, String phone, String email) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.phone = phone;
    this.email = email;
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

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return id == user.id &&
      Objects.equals(name, user.name) &&
      Objects.equals(surname, user.surname) &&
      Objects.equals(phone, user.phone) &&
      Objects.equals(email, user.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, surname, phone, email);
  }

  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", surname='" + surname + '\'' +
      ", phone='" + phone + '\'' +
      ", email='" + email + '\'' +
      '}';
  }
}
