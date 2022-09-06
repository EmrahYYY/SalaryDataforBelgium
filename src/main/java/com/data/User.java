package com.data;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 12)
    private String password;

    @Column(nullable = false,  length = 40)
    private String name;

    @Column
    private String stringDate;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Salary> salaries;

    public User() {
        setStringDate(stringDate);
    }

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
        setStringDate(stringDate);
    }

    public User(String email, String password, String name, List<Salary> salaries) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.salaries = salaries;
        setStringDate(stringDate);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Salary> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<Salary> salaries) {
        this.salaries = salaries;
    }

    public String getStringDate() {
        return stringDate;
    }

    public void setStringDate(String stringDate) {

        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formatDateTime = date.format(format);
        this.stringDate = formatDateTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
