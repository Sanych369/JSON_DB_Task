package service;

import model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.Converter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final UserService userService = new UserService();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        Converter.toJSON(userList);
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @AfterAll
    static void deleteJSON() {
        Converter.deleteJSON();
    }

    User user;
    User user1;
    User user2;
    List<User> userList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        user = new User(0, "one", "one", "aaa", "PositionB", "aaa", "aaa@aaa", new ArrayList<>(Arrays.asList("+131231", "+623123", "+811")));
        user1 = new User(1, "bbb", "bbb", "bbb", "PositionA", "bbb", "bbb@bbb", new ArrayList<>(Arrays.asList("+13121", "+922", "+7222")));
        user2 = new User(2, "sas", "sas", "sas", "Position", "sas", "sas@sas", new ArrayList<>(Arrays.asList("+1111", "+333", "+422")));
        userList.add(user1);
        userList.add(user);
        userList.add(user2);
        Converter.toJSON(userList);
    }

    @Test
    @DisplayName("Поиск по номеру телефона пользователя, которого нет")
    public void findByNumberUserExist() {
        String phone = "+3565645646464".trim();
        userService.find(phone);
        assertEquals(outContent.toString(), "User with phone number " + phone + " not found!\n");
    }

    @Test
    @DisplayName("Поиск по номеру телефона существующего пользователя")
    public void findByNumberUserDoesNotExist() {
        String phone = "+333".trim();
        userService.find(phone);
        assertEquals(outContent.toString(), user2.toString());
    }

    @Test
    @DisplayName("Вывод сортированного листа по имени")
    public void listSortingByFirstName() {
        String firstName = "firstName";
        userService.list(firstName);
        userList.sort(Comparator.comparing(User::getFirstName));
        StringBuilder actual = new StringBuilder("List of users sorted by " + firstName + '\n');
        for (User value : userList) {
            actual.append(value.toString()).append('\n');
        }
        assertEquals(outContent.toString(), actual.toString());
    }

    @Test
    @DisplayName("Вывод сортированного листа по фамилии")
    public void listSortingByLastName() {
        String lastName = "lastName";
        userService.list(lastName);
        userList.sort(Comparator.comparing(User::getLastName));
        StringBuilder actual = new StringBuilder("List of users sorted by " + lastName + '\n');
        for (User value : userList) {
            actual.append(value.toString()).append('\n');
        }
        assertEquals(outContent.toString(), actual.toString());
    }

    @Test
    @DisplayName("Вывод сортированного листа по отчеству")
    public void listSortingByMiddleName() {
        String middleName = "middleName";
        userService.list(middleName);
        userList.sort(Comparator.comparing(User::getMiddleName));
        StringBuilder actual = new StringBuilder("List of users sorted by " + middleName + '\n');
        for (User value : userList) {
            actual.append(value.toString()).append('\n');
        }
        assertEquals(outContent.toString(), actual.toString());
    }

    @Test
    @DisplayName("Вывод сортированного листа по эл.почте")
    public void listSortingByEmail() {
        String email = "email";
        userService.list(email);
        userList.sort(Comparator.comparing(User::getEmail));
        StringBuilder actual = new StringBuilder("List of users sorted by " + email + '\n');
        for (User value : userList) {
            actual.append(value.toString()).append('\n');
        }
        assertEquals(outContent.toString(), actual.toString());
    }

    @Test
    @DisplayName("Вывод сортированного листа по компаниям")
    public void listSortingByCompany() {
        String company = "company";
        userService.list(company);
        userList.sort(Comparator.comparing(User::getNameOfCompany));
        StringBuilder actual = new StringBuilder("List of users sorted by " + company + '\n');
        for (User value : userList) {
            actual.append(value.toString()).append('\n');
        }
        assertEquals(outContent.toString(), actual.toString());
    }

    @Test
    @DisplayName("Вывод сортированного листа по должности")
    public void listSortingByPosition() {
        String position = "position";
        userService.list(position);
        userList.sort(Comparator.comparing(User::getPosition));
        StringBuilder actual = new StringBuilder("List of users sorted by " + position + '\n');
        for (User value : userList) {
            actual.append(value.toString()).append('\n');
        }
        assertEquals(outContent.toString(), actual.toString());
    }

    @Test
    @DisplayName("Удаление пользователя по существующему ID")
    public void deleteUser() {
        long id = 2;
        userService.delete(userList, id);
        String actual = "User with ID " + id + " successfully deleted!\n";
        assertEquals(outContent.toString(), actual);
    }

    @Test
    @DisplayName("Удаление пользователя, которого нет")
    public void itShouldDelete() {
        long id = 4;
        userService.delete(userList, id);
        String actual = "User with ID " + id + " does not exist!\n";
        assertEquals(outContent.toString(), actual);
    }
}