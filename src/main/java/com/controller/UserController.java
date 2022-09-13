package com.controller;


import com.services.implementations.Nest2;
import com.services.implementations.SalaryServiceImpl;
import com.services.implementations.UserServiceImpl;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(method = RequestMethod.GET)
public class UserController {


    SalaryServiceImpl salaryService;

    UserServiceImpl userService;

    @Autowired
    public UserController(SalaryServiceImpl salaryService, UserServiceImpl userService) {
        this.salaryService = salaryService;
        this.userService = userService;
    }


    @GetMapping("/register")
    public String showRegisterPage(Model model) {

        model.addAttribute("nest2", new Nest2());

        return "registerAndAddSalary";
    }


    @PostMapping("/addSalary")
    public String addSalary(Nest2 nest2, Model model) {


        model.addAttribute("AddedValue",  nest2.getTwo().getSalaryOfUser() );
        model.addAttribute("year",  nest2.getTwo().getYear() );

        try {

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encoderPassword = encoder.encode(nest2.getOne().getPassword());
            nest2.getOne().setPassword(encoderPassword);

            userService.addUser(nest2.getOne());
            nest2.getTwo().setUser(nest2.getOne());

            nest2.getTwo().setSalaryOfUser(salaryService.adjustSalaryForInflation((int) nest2.getTwo().getSalaryOfUser(), nest2.getTwo().getYear()));

            salaryService.addSalary(nest2.getTwo());


        } catch (Exception exception) {

            return "exception";
        }

        model.addAttribute("current",  nest2.getTwo().getSalaryOfUser() );

        return "salaryAddingSucces";
    }





}
