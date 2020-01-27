package ua.com.controller;

import java.util.Scanner;

public class Program {
  public static void start() {
    int choice = 0;
    Scanner sc = new Scanner(System.in);
    boolean isStop = false;

    showChoices();
    while (!isStop) {
      sc.nextLine();
      choice = sc.nextInt();
      switch (choice) {
        case 1:
          AddressController.create();
          break;
        case 2:
          AddressController.update();
          break;
        case 3:
          AddressController.delete();
          break;
        case 4:
          AddressController.getAll();
          break;
        case 5:
          AddressController.getById();
          break;
        case 6:
          CityController.create();
          break;
        case 7:
          CityController.update();
          break;
        case 8:
          CityController.delete();
          break;
        case 9:
          CityController.getAll();
          break;
        case 10:
          CityController.getById();
          break;
        case 11:
          GoodController.create();
          break;
        case 12:
          GoodController.update();
          break;
        case 13:
          GoodController.delete();
          break;
        case 14:
          GoodController.getAll();
          break;
        case 15:
          GoodController.getById();
          break;
        case 16:
          GoodController.getGoodsCities();
          break;
        case 17:
          GoodController.deleteCityFromGood();
          break;
        case 18:
          GoodController.addCityToGood();
          break;
        case 19:
          UserController.create();
          break;
        case 20:
          UserController.update();
          break;
        case 21:
          UserController.delete();
          break;
        case 22:
          UserController.getAll();
          break;
        case 23:
          UserController.getById();
          break;
        case 24:
          UserController.makeOrder();
          break;
        case 25:
          UserController.deleteOrder();
          break;
        case 26:
          UserController.getOrders();
          break;
        case 27:
          UserController.getUserAddresses();
          break;
        case 28:
          UserController.deleteAddress();
          break;
        case 29:
          UserController.addAddressToUser();
          break;
        case 30:
          isStop = true;
          break;
        case 31:
          showChoices();
          break;
        default:
          System.out.println("Invalid input!");
          break;
      }
    }
  }

  private static void showChoices() {
    System.out.println(
      "1 - Add address\n" +
        "2 - Update address\n" +
        "3 - Delete address\n" +
        "4 - Get all addresses\n" +
        "5 - Get address by id\n" +
        "6 - Add city\n" +
        "7 - Update city\n" +
        "8 - Delete city\n" +
        "9 - Get all city\n" +
        "10 - Get city by id\n" +
        "11 - Add good\n" +
        "12 - Update good\n" +
        "13 - Delete good\n" +
        "14 - Get all good\n" +
        "15 - Get good by id\n" +
        "16 - Get good's cities\n" +
        "17 - Delete city from the good\n" +
        "18 - Add city to the good\n" +
        "19 - Add user\n" +
        "20 - Update user\n" +
        "21 - Delete user\n" +
        "22 - Get all user\n" +
        "23 - Get user by id\n" +
        "24 - Make order\n" +
        "25 - Delete order\n" +
        "26 - Show orders\n" +
        "27 - Get user addresses\n" +
        "28 - Delete address from the user\n" +
        "29 - Add address to the user\n" +
        "30 - Stop work\n" +
        "31- Show choices\n"
    );
  }
}

