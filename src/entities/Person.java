package entities;

import org.w3c.dom.DOMStringList;

import java.time.LocalDate;

public class Person {
private String id;
private String firstName;
private String lastName;
private LocalDate dateOfBirth;
private String gender;
private String phoneNumber;
private String email;
private String address;
private String nationalId;
private int age;
private boolean active;

    public Person(String id, String firstName, String lastName,
                  LocalDate dateOfBirth, String gender,
                  String phoneNumber, String email,
                  String address, String nationalId,
                  int age, boolean active) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.nationalId = nationalId;
        this.age = age;
        this.active = active;
    }
    //overloaded
public Person(String id, String firstName, String List){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
}

}
