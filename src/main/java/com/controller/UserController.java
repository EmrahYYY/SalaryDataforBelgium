package com.controller;


import com.services.implementations.Nest2;
import com.services.implementations.SalaryServiceImpl;
import com.services.implementations.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {


    SalaryServiceImpl salaryService;

    UserServiceImpl userService;

    @Autowired
    public UserController(SalaryServiceImpl salaryService, UserServiceImpl userService) {
        this.salaryService = salaryService;
        this.userService = userService;
    }


    @GetMapping("/addSalary")
    public String addSalary(Nest2 nest2) {


        try {

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encoderPassword = encoder.encode(nest2.getOne().getPassword());
            nest2.getOne().setPassword(encoderPassword);

            userService.addUser(nest2.getOne());
            nest2.getTwo().setUser(nest2.getOne());
            salaryService.addSalary(nest2.getTwo());
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return "registerAndAddSalary";
    }

}
