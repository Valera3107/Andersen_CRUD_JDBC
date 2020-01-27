package ua.com.controller;

import ua.com.model.User;
import ua.com.service.UserService;

import java.util.Scanner;

public class UserController {
  private static UserService dao = new UserService();

  public static void getAll() {
    dao.getAll().forEach(System.out::println);
  }

  public static void getUserAddresses(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Input user id: ");
    long id = sc.nextLong();
    dao.getUserAddresses(id).forEach(System.out::println);
  }

  public static void addAddressToUser(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Input user id: ");
    long userId = sc.nextLong();
    System.out.println("Input address id: ");
    long addressId = sc.nextLong();
    System.out.println("Add address to user: "+dao.addAddress(addressId, userId));
  }

  public static void deleteAddress(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Input user id: ");
    long id = sc.nextLong();
    System.out.println("Delete user's address: "+dao.deleteAddress(id));
  }

  public static void getOrders() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Input user id: ");
    long id = sc.nextLong();
    dao.getOrders(id).forEach(System.out::println);
  }

  public static void deleteOrder() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Input user id: ");
    long userId = sc.nextLong();
    System.out.println("Input good id: ");
    long goodId = sc.nextLong();
    System.out.println("Delete user's order: " + dao.deleteOrder(goodId, userId));
  }

  public static void makeOrder() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Input user id: ");
    long userId = sc.nextLong();
    System.out.println("Input good id: ");
    long goodId = sc.nextLong();
    System.out.println("Create order: " + dao.makeOrder(goodId, userId));
  }

  public static void getById() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Input user id:");
    try {
      long id = sc.nextLong();
      System.out.println(dao.get(id));
    } catch (Exception ex) {
      System.out.println("Invalid input!");
    }
    sc.close();
  }

  public static void create() {
    User user = fillFields();
    System.out.println("User created: " + dao.create(user));
  }

  public static void update() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Input user id: ");
    try {
      long id = sc.nextLong();
      User user = fillFields();
      user.setId(id);
      System.out.println("User updated: " + dao.update(user));
    } catch (Exception ex) {
      System.out.println("Invalid input");
    }
  }

  public static void delete() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Input user id: ");
    try {
      long id = sc.nextLong();
      System.out.println("User deleted: " + dao.delete(id));
    } catch (Exception ex) {
      System.out.println("Invalid Input!");
    }
  }

  private static User fillFields() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Input name: ");
    String name = sc.nextLine();
    System.out.println("Input surname: ");
    String surname = sc.nextLine();
    System.out.println("Input phone number: ");
    String phone = sc.nextLine();
    System.out.println("Input email: ");
    String email = sc.nextLine();
    User user = new User(name, surname, phone, email);
    sc.close();
    return user;
  }
}
