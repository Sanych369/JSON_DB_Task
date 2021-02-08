package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private static long IDCount = 0;

    @JsonProperty("id")
    private long id;

    @JsonProperty("First Name")
    private String firstName;

    @JsonProperty("Last Name")
    private String lastName;

    @JsonProperty("Middle Name")
    private String middleName;

    @JsonProperty("Position")
    private String position;

    @JsonProperty("Name of Company")
    private String nameOfCompany;

    @JsonProperty("Email")
    private String email;

    @JsonProperty("Phone Numbers")
    private List<String> phoneNumbers;

    public User(String firstName, String lastName, String middleName, String position, String nameOfCompany, String email, List<String> phoneNumbers) {
        this.id = IDCount++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.position = position;
        this.nameOfCompany = nameOfCompany;
        this.email = email;
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public String toString() {
        return "User " +
                "id = " + id + '\n' +
                "Имя = " + firstName + '\n' +
                "Фамилия = " + lastName + '\n' +
                "Отчество = " + middleName + '\n' +
                "Должность = " + position + '\n' +
                "Наименование компании = " + nameOfCompany + '\n' +
                "email = " + email + '\n' +
                "Номера телефонов = " + phoneNumbers +
                '\n';
    }
}
