package ua.com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class City implements ModelInterface{
  private long id;
  private String city;
  private String code;
  private List<Good> goods = new ArrayList<>();

  public City() {
  }

  public City(long id, String city, String code) {
    this.id = id;
    this.city = city;
    this.code = code;
  }

  public City(String city, String code, List<Good> goods) {
    this.city = city;
    this.code = code;
    this.goods = goods;
  }

  public City(String city, String code) {
    this.city = city;
    this.code = code;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public List<Good> getGoods() {
    return goods;
  }

  public void setGoods(List<Good> goods) {
    this.goods = goods;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    City city1 = (City) o;
    return id == city1.id &&
      Objects.equals(city, city1.city) &&
      Objects.equals(code, city1.code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, city, code);
  }

  @Override
  public String toString() {
    return "City{" +
      "id=" + id +
      ", city='" + city + '\'' +
      ", code='" + code + '\'' +
      '}';
  }
}
