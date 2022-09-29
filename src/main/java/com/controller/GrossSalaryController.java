package com.controller;

import com.data.GrossSalary;
import com.services.implementations.GrossSalaryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(method = RequestMethod.GET)
public class GrossSalaryController {


    GrossSalaryImpl netSalary;


    @Autowired
    public GrossSalaryController(GrossSalaryImpl netSalary) {
        this.netSalary = netSalary;
    }

    @PostMapping("/grossToNet")
    public String grossToNet(GrossSalary grossSalary, Model model) {


        try {

            model.addAttribute("grossToNet", netSalary.convertGrossSalaryToNetSalary(grossSalary));


        } catch (Exception exception) {

            return "exception";
        }
        return "grossToNet";
    }

}
