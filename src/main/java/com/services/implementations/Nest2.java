package com.services.implementations;

import com.data.Salary;
import com.data.User;
import org.springframework.stereotype.Service;

@Service
public class Nest2 {

    private User one = new User();

    private Salary two = new Salary();

    public User getOne() {
        return one;
    }

    public void setOne(User one) {
        this.one = one;
    }

    public Salary getTwo() {
        return two;
    }

    public void setTwo(Salary two) {
        this.two = two;
    }
}