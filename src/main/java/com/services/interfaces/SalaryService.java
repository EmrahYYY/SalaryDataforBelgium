package com.services.interfaces;

import com.data.Salary;

import java.util.List;

public interface SalaryService {

    public void addSalary(Salary salary);

    public List<Salary> salaries();

}
