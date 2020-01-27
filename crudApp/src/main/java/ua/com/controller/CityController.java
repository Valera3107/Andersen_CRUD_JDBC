package ua.com.controller;

import ua.com.dao.CityDAO;
import ua.com.model.City;
import ua.com.service.CityService;

import java.util.Scanner;

public class CityController {
  private static CityDAO dao = new CityService();


  public static void getAll() {
    dao.getAll().forEach(System.out::println);
  }

  public static void getById() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Input city id:");
    try {
      long id = sc.nextLong();
      System.out.println(dao.get(id));
    } catch (Exception ex) {
      System.out.println("Invalid input!");
    }
    sc.close();
  }

  public static void create() {
    City city = fillFields();
    System.out.println("City created: " + dao.create(city));
  }

  public static void update() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Input id of the city: ");
    try {
      long id = sc.nextLong();
      City city = fillFields();
      city.setId(id);
      System.out.println("User updated: " + dao.update(city));
    } catch (Exception ex) {
      System.out.println("Invalid input");
    }
  }

  public static void delete() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Input city id: ");
    try {
      long id = sc.nextLong();
      System.out.println("City deleted: " + dao.delete(id));
    } catch (Exception ex) {
      System.out.println("Invalid Input!");
    }
  }

  private static City fillFields() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Input name of a city: ");
    String name = sc.nextLine();
    System.out.println("Input code of a city: ");
    String code = sc.nextLine();
    City city = new City(name, code);
    sc.close();
    return city;
  }
}
