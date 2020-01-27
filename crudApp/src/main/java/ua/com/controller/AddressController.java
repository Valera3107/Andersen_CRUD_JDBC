package ua.com.controller;

import ua.com.dao.AddressDAO;
import ua.com.model.Address;
import ua.com.service.AddressService;

import java.util.Scanner;

public class AddressController {
  private static AddressDAO dao = new AddressService();


  public static void getAll() {
    dao.getAll().forEach(System.out::println);
  }

  public static void getById() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Input address id:");
    try {
      long id = sc.nextLong();
      System.out.println(dao.get(id));
    } catch (Exception ex) {
      System.out.println("Invalid input!");
    }
    sc.close();
  }

  public static void create() {
    Address address = fillFields();
    System.out.println("Good created: " + dao.create(address));
  }

  public static void update() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Input id of the address: ");
    try {
      long id = sc.nextLong();
      Address address = fillFields();
      address.setId(id);
      System.out.println("Good updated: " + dao.update(address));
    } catch (Exception ex) {
      System.out.println("Invalid input");
    }
  }

  public static void delete() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Input address id: ");
    try {
      long id = sc.nextLong();
      System.out.println("Good deleted: " + dao.delete(id));
    } catch (Exception ex) {
      System.out.println("Invalid Input!");
    }
  }

  private static Address fillFields() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Input name of a address: ");
    String name = sc.nextLine();
    System.out.println("Input number of a address: ");
    int number = sc.nextInt();
    Address address = new Address(name, number);
    sc.close();
    return address;
  }
}
