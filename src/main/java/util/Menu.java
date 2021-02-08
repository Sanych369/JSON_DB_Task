package util;

import model.User;
import service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final UserService userService = new UserService();
    private boolean stop = false;
    private final List<User> userList = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);

    public void start() {

        Converter.toJSON(userList); // Создаём пустой файл в resources

        while (!stop) {
            System.out.println("\nEnter help to view a list of commands of enter your command or 0 to exit:");
            String str = sc.nextLine().trim();
            switch (str) {
                case "help":
                    help();
                    break;
                case "insert":
                    userService.insert(userList);
                    break;
                case "update":
                    System.out.println("Update user:");
                    System.out.println("Enter the ID of the user you want to update:");
                    try {
                        long id = Integer.parseInt(sc.nextLine().trim());
                        userService.update(userList, id);
                    } catch (NumberFormatException e) {
                        System.out.println("Please, enter number!");
                    }
                    break;
                case "delete":
                    System.out.println("Delete user:");
                    System.out.println("Enter the id of the user you want to delete:");
                    try {
                        long id = Integer.parseInt(sc.nextLine().trim());
                        userService.delete(userList, id);
                    } catch (NumberFormatException e) {
                        System.out.println("Please, enter number!");
                    }
                    break;
                case "list firstName":
                    userService.list("firstName");
                    break;
                case "list lastName":
                    userService.list("lastName");
                    break;
                case "list middleName":
                    userService.list("middleName");
                    break;
                case "list email":
                    userService.list("email");
                    break;
                case "list company":
                    userService.list("company");
                    break;
                case "list position":
                    userService.list("position");
                    break;
                case "list":
                    userService.list("");
                    break;
                case "find":
                    System.out.println("Enter phone number to find user:");
                    String phone = sc.nextLine().trim();
                    userService.find(phone);
                    break;
                case "0":
                    sc.close();
                    stop = true;
                    System.out.println("JSON file deleting...");
                    Converter.deleteJSON();
                    System.out.println("Application closing...");
                default:
                    break;
            }
        }
    }

    private void help() {
        System.out.println("\nCOMMANDS:\n" +
                "insert - добавить нового клиента.\n" +
                "\n" +
                "update - редактировать клиента по ID.\n" +
                "\n" +
                "delete - удалить клиента по ID.\n" +
                "\n" +
                "list - вывести список всех клиентов по ID.\n" +
                "\n" +
                "list *fieldName* - вывести список клиентов отсортированным по указанному полю.\n" +
                "\n" +
                "find - поиск клиента по номеру телефона.\n");
    }
}