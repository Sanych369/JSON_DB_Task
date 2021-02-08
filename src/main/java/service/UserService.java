package service;

import model.User;
import util.Converter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UserService {
    private final Scanner sc = new Scanner(System.in);

    public void find(String phone) {
        User currentUser = null;
        List<User> users = Converter.toPOJO();
        for (User user : Objects.requireNonNull(users)) {
            if (user.getPhoneNumbers().contains(phone)) {
                currentUser = user;
            }
        }
        System.out.print(currentUser == null ? "User with phone number " + phone + " not found!\n"
                : currentUser.toString());
    }

    public void list(String str) {
        List<User> users = Converter.toPOJO();
        assert users != null;
        if (users.size() > 0) {
            if ("firstName".equals(str) || "lastName".equals(str)
                    || "middleName".equals(str) || "email".equals(str)
                    || "company".equals(str) || "position".equals(str)) {

                System.out.print("List of users sorted by " + str + '\n');

                switch (str) {
                    case "firstName":
                        users.sort(Comparator.comparing(User::getFirstName));
                        break;
                    case "lastName":
                        users.sort(Comparator.comparing(User::getLastName));
                        break;
                    case "middleName":
                        users.sort(Comparator.comparing(User::getMiddleName));
                        break;
                    case "position":
                        users.sort(Comparator.comparing(User::getPosition));
                        break;
                    case "company":
                        users.sort(Comparator.comparing(User::getNameOfCompany));
                        break;
                    default:
                        users.sort(Comparator.comparing(User::getEmail));
                        break;
                }
                printUsers(users);
            } else {
                System.out.println("List of users sorted by ID:");
                users.sort(Comparator.comparing(User::getId));
                for (User user : Objects.requireNonNull(users)) {
                    System.out.println(user.toString());
                }
            }
        } else {
            System.out.print("List of users is empty!\n");
        }
    }


    public void insert(List<User> userList) {
        System.out.println("Create new user:");
        User user = createUser();
        Objects.requireNonNull(userList).add(user);
        Converter.toJSON(userList);
        System.out.print("New user " + user.getFirstName() +
                " " + user.getLastName() +
                " with ID = " + user.getId() +
                " successfully created!\n");
    }

    public void update(List<User> userList, long id) {
        boolean userExists = false;
        List<User> users = Converter.toPOJO();
        for (int i = 0; i < Objects.requireNonNull(users).size(); i++) {
            if (users.get(i).getId() == id) {
                System.out.println(users.get(i).toString());
                User user = createUser();
                user.setId(id);
                userList.remove(users.get(i));
                userList.add(user);
                Converter.toJSON(userList);
                userExists = true;
            }
        }
        System.out.print(userExists ? "User with ID = " + id + " successfully updated!\n"
                : "User with ID " + id + " not found!\n");
    }

    private User createUser() {
        System.out.println("Enter first name:");
        String firstName = sc.next();
        System.out.println("Enter last name:");
        String lastName = sc.next();
        System.out.println("Enter middle name:");
        String middleName = sc.next();
        System.out.println("Enter position:");
        String position = sc.next();
        System.out.println("Enter name of company:");
        String nameOfCompany = sc.next();
        System.out.println("Enter email:");
        String email = sc.next();
        System.out.println("Enter phone number(s) separated by a space:");
        sc.nextLine();
        String phone = sc.nextLine();
        List<String> phones = Arrays.asList(phone.split(" "));
        return new User(firstName, lastName, middleName, position, nameOfCompany, email, phones);
    }

    public void delete(List<User> userList, long id) {
        boolean userExists = false;
        List<User> users = Converter.toPOJO();
        for (int i = 0; i < Objects.requireNonNull(users).size(); i++) {
            if (users.get(i).getId() == id) {
                userList.remove(users.get(i));
                Converter.toJSON(userList);
                userExists = true;
            }
        }
        System.out.print(userExists ? "User with ID " + id + " successfully deleted!\n"
                : "User with ID " + id + " does not exist!\n");
    }

    private void printUsers(List<User> users) {
        for (User user : Objects.requireNonNull(users)) {
            System.out.print(user.toString() + '\n');
        }
    }
}
