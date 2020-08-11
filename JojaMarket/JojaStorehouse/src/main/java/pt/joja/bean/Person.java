package pt.joja.bean;

import java.util.List;
import java.util.Map;

public class Person {

    private int age;

    public Person() {
    }

    public Person(Car car) {
        this.car = car;
        System.out.println("可以为car赋值的有参构造器");
    }

    Map<String, Book> nameAndBooks;

    public Map<String, Book> getNameAndBooks() {
        return nameAndBooks;
    }

    public void setNameAndBooks(Map<String, Book> nameAndBooks) {
        this.nameAndBooks = nameAndBooks;
    }

    List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    private int salary;

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", nameAndBooks=" + nameAndBooks +
                ", books=" + books +
                ", car=" + car +
                ", salary=" + salary +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
