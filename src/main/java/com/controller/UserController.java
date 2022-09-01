package com.controller;


import com.services.implementations.SalaryServiceImpl;
import com.services.implementations.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class UserController {


    SalaryServiceImpl salaryService;

    UserServiceImpl userService;

    @Autowired
    public UserController(SalaryServiceImpl salaryService, UserServiceImpl userService) {
        this.salaryService = salaryService;
        this.userService = userService;
    }


}
