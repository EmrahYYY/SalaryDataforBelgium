package com.services.implementations;

import com.data.Salary;
import com.repository.SalaryRepository;
import com.services.interfaces.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {


    SalaryRepository salaryRepository;


    @Autowired
    public SalaryServiceImpl(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    @Override
    public void addSalary(Salary salary) {

        salaryRepository.save(salary);
    }

    @Override
    public List<Salary> salaries() {

        List<Salary> salaries = salaryRepository.findAll();



        Collections.reverse(salaries);



        return salaries.subList(0, 15);
    }




    public double averageSalaries() {
        List<Salary> salariess = salaryRepository.findAll();
        int count = 0;

        double total = 0;

        for (Salary salary : salariess) {

            if (salary.getSalaryOfUser() > 0) {
                total += salary.getSalaryOfUser();
                count++;
            }

        }

        return Math.round(total / count);
    }


    public double compereProvinces(String province1, String province2) throws Exception {

        if (averageSalaryInSingleProvince(province1) > averageSalaryInSingleProvince(province2)) {
            return Math.round(averageSalaryInSingleProvince(province1) / averageSalaryInSingleProvince(province2) * 100) - 100;
        } else {
            return Math.round(averageSalaryInSingleProvince(province2) / averageSalaryInSingleProvince(province1) * 100) - 100;
        }
    }

    public double averageSalaryInSingleProvince(String province) throws Exception {
        List<Salary> salariess = salaryRepository.findSalarysByProvinceName(province);
        int count = 0;
        double total = 0;


        for (Salary salary : salariess) {

            if (salary.getSalaryOfUser() > 0) {
                total += salary.getSalaryOfUser();
                count++;
            }

        }

        if (Math.round(total / count) < 0.0) {
            throw new Exception("Doesn't find any data");

        } else {
            return Math.round(total / count);
        }


    }


    public double averageSalaryInSingleSector(String sector) throws Exception {
        List<Salary> salariess = salaryRepository.findSalarysBySectorName(sector);
        int count = 0;
        double total = 0;


        for (Salary salary : salariess) {

            if (salary.getSalaryOfUser() > 0) {
                total += salary.getSalaryOfUser();
                count++;
            }

        }


        if (Math.round(total / count) < 0.0) {
            throw new Exception("Doesn't find any data");

        } else {
            return Math.round(total / count);
        }


    }



    public int adjustSalaryForInflation(int salaryOfUser, int year) {

        /*  source for inflation of Belgium:
         https://www.macrotrends.net/countries/BEL/belgium/inflation-rate-cpi#   */


        switch (year) {
            case 2000:
                salaryOfUser *= 1.0254;
            case 2001:
                salaryOfUser *= 1.0247;
            case 2002:
                salaryOfUser *= 1.0165;
            case 2003:
                salaryOfUser *= 1.0159;
            case 2004:
                salaryOfUser *= 1.0210;
            case 2005:
                salaryOfUser *= 1.0278;
            case 2006:
                salaryOfUser *= 1.0179;
            case 2007:
                salaryOfUser *= 1.0182;
            case 2008:
                salaryOfUser *= 1.0449;
            case 2009:
                salaryOfUser *= 0.9995;
            case 2010:
                salaryOfUser *= 1.0219;
            case 2011:
                salaryOfUser *= 1.0353;
            case 2012:
                salaryOfUser *= 1.0284;
            case 2013:
                salaryOfUser *= 1.0111;
            case 2014:
                salaryOfUser *= 1.0034;
            case 2015:
                salaryOfUser *= 1.0056;
            case 2016:
                salaryOfUser *= 1.0197;
            case 2017:
                salaryOfUser *= 1.0213;
            case 2018:
                salaryOfUser *= 1.0205;
            case 2019:
                salaryOfUser *= 1.0144;
            case 2020:
                salaryOfUser *= 1.0074;
            case 2021:
                salaryOfUser *= 1.0244;


        }

        return salaryOfUser;

    }

}
