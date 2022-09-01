package com.services.implementations;

import com.data.Salary;
import org.springframework.stereotype.Service;

@Service
public class Nest {

    private Salary one = new Salary();

    private Salary two = new Salary();

    public Salary getOne() {
        return one;
    }

    public void setOne(Salary one) {
        this.one = one;
    }

    public Salary getTwo() {
        return two;
    }

    public void setTwo(Salary two) {
        this.two = two;
    }
}