package entities;

import interfaces.Displayable;
import java.time.LocalDate;
import utils.HelperUtils;
public class Person implements Displayable {
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
    private boolean activeStatus;

    // Full constructor
    public Person(String id, String firstName, String lastName,
                  LocalDate dateOfBirth, String gender,
                  String phoneNumber, String email, String address,
                  String nationalId, int age, boolean activeStatus) {

        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setDateOfBirth(dateOfBirth);
        setGender(gender);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setAddress(address);
        setNationalId(nationalId);
        setAge(age);
        setActiveStatus(activeStatus);
    }

    // Overloaded
    public Person(String id, String firstName, String lastName) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
    }
//Getter

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getNationalId() {
        return nationalId;
    }

    public int getAge() {
        return age;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }

    //Setter
    public void setId(String id) {
        if (HelperUtils.isEmpty(id)) {
            IO.println("ID cannot be empty");
            return;
        }
        this.id = id;
    }
    public void setFirstName(String firstName) {
        if (HelperUtils.isEmpty(firstName)) {
            IO.println("First name cannot be empty");
            return;
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (HelperUtils.isEmpty(lastName)) {
            IO.println("Last name cannot be empty");
            return;
        }
        this.lastName = lastName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            IO.println("Date of birth cannot be empty");
            return;
        }
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public void setAge(int age) {
        if (!HelperUtils.isValidAge(age)) {
            IO.println("Age must be between 0 and 120");
            return;
        }
        this.age = age;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    @Override
    public void displayInfo() {
        IO.println("ID: " + id);
        IO.println("First Name: " + firstName);
        IO.println("Last Name: " + lastName);
        IO.println("Date of Birth: " + dateOfBirth);
        IO.println("Gender: " + gender);
        IO.println("Phone: " + phoneNumber);
        IO.println("Email: " + email);
        IO.println("Address: " + address);
        IO.println("National ID: " + nationalId);
        IO.println("Age: " + age);
        IO.println("Active Status: " + activeStatus);
    }

    @Override
    public void displaySummry() {

    }

    @Override
    public void displaySummary() {
        IO.println("ID: " + id);
        IO.println("Name: " + getFullName());
    }


    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", age=" + age +
                ", activeStatus=" + activeStatus +
                '}';
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Person)) {
            return false;
        }

        Person other = (Person) obj;

        return id.equals(other.id);
    }

    public boolean isAdult() {
        return age >= 18;
    }
    
}




