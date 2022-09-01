package com.services.implementations;

import com.data.Salary;
import com.repository.SalaryRepository;
import com.services.interfaces.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        List<Salary> salaries= salaryRepository.findAll();

        Collections.reverse(salaries);

        return salaries;
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

        if (averageSalaryInSingleProvince(province1)>averageSalaryInSingleProvince(province2))
        {return  Math.round(averageSalaryInSingleProvince(province1) / averageSalaryInSingleProvince(province2) * 100) -100 ;}
        else {return Math.round( averageSalaryInSingleProvince(province2)/averageSalaryInSingleProvince(province1) * 100) -100;  }
    }

    public double averageSalaryInSingleProvince(String province) throws Exception {
        List<Salary> salariess = salaryRepository.findSalaryByProvinceName(province);
        int count = 0;
        double total = 0;


        for (Salary salary : salariess) {

            if (salary.getSalaryOfUser() > 0) {
                total += salary.getSalaryOfUser();
                count++;
            }

        }

        System.out.println("average Salary in " + province + " is : " + Math.round(total / count));

        if (Math.round(total / count)<0.0){
            System.out.println();
            throw  new Exception("Doesn't find any data");

        } else { return Math.round(total / count);}



    }

}
