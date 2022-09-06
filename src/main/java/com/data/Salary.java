package com.data;


import javax.persistence.*;

@Entity
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(unique = false)
    private int salaryOfUser;

    @Column
    private String sector;

    @Column
    private String province;

    @Column
    private int year;

    @ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;

    public Salary() {
    }


    public Salary(int salaryOfUser, String sector, String province) {
        this.salaryOfUser = salaryOfUser;
        this.sector = sector;
        this.province = province;
    }

    public Salary(int salary, String sector, String province, int year) {
        this.salaryOfUser = salary;
        this.sector = sector;
        this.province = province;
        this.year = year;
    }

    public Salary(int salary, String sector, String province, int year, User user) {
        this.salaryOfUser = salary;
        this.sector = sector;
        this.province = province;
        this.year = year;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSalaryOfUser() {
        return salaryOfUser;
    }

    public void setSalaryOfUser(int salaryOfUser) {

        this.salaryOfUser = salaryOfUser;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", salaryOfUser=" + salaryOfUser +
                ", sector='" + sector + '\'' +
                ", province='" + province + '\'' +
                ", year=" + year +
                ", user=" + user +
                '}';
    }
}
