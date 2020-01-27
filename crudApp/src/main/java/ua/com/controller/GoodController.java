package ua.com.controller;

import ua.com.model.Good;
import ua.com.service.GoodService;

import java.util.Scanner;

public class GoodController {
  private static GoodService dao = new GoodService();

  public static void getAll() {
    dao.getAll().forEach(System.out::println);
  }

  public static void getGoodsCities() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Input good id: ");
    long id = sc.nextLong();
    dao.getCities(id).forEach(System.out::println);
  }

  public static void deleteCityFromGood() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Input city id: ");
    long cityId = sc.nextLong();
    System.out.println("Input good id: ");
    long goodId = sc.nextLong();
    System.out.println("Delete city from good: " + dao.deleteCityFromGood(cityId, goodId));
  }

  public static void addCityToGood() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Input city id: ");
    long cityId = sc.nextLong();
    System.out.println("Input good id: ");
    long goodId = sc.nextLong();
    System.out.println("Add city to the good: " + dao.addCityToGood(goodId, cityId));
  }

  public static void getById() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Input good id:");
    try {
      long id = sc.nextLong();
      System.out.println(dao.get(id));
    } catch (Exception ex) {
      System.out.println("Invalid input!");
    }
    sc.close();
  }

  public static void create() {
    Good good = fillFields();
    System.out.println("Good created: " + dao.create(good));
  }

  public static void update() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Input id of the good: ");
    try {
      long id = sc.nextLong();
      Good good = fillFields();
      good.setId(id);
      System.out.println("Good updated: " + dao.update(good));
    } catch (Exception ex) {
      System.out.println("Invalid input");
    }
  }

  public static void delete() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Input good id: ");
    try {
      long id = sc.nextLong();
      System.out.println("Good deleted: " + dao.delete(id));
    } catch (Exception ex) {
      System.out.println("Invalid Input!");
    }
  }

  private static Good fillFields() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Input name of a good: ");
    String name = sc.nextLine();
    System.out.println("Input description of a good: ");
    String code = sc.nextLine();
    System.out.println("Input description of a good: ");
    double price = sc.nextDouble();
    Good good = new Good(name, code, price);
    sc.close();
    return good;
  }
}
