package com.controller;


import com.services.implementations.Nest;
import com.services.implementations.SalaryServiceImpl;
import com.services.implementations.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class SalaryController {


    SalaryServiceImpl salaryService;

    UserServiceImpl userService;

    @Autowired
    public SalaryController(SalaryServiceImpl salaryService, UserServiceImpl userService) {
        this.salaryService = salaryService;
        this.userService = userService;
    }


    @GetMapping("/homePage")
    public String getAllDatas(Model model) {

        model.addAttribute("salary", salaryService.salaries());
        model.addAttribute("average", salaryService.averageSalaries());

        return "index";
    }


    @GetMapping("/getProvince")
    public String compere(Nest nest, Model model) throws Exception {

        if (salaryService.averageSalaryInSingleProvince(nest.getOne().getProvince()) > 0 && salaryService.averageSalaryInSingleProvince(nest.getTwo().getProvince()) > 0) {

            model.addAttribute("province", salaryService.averageSalaryInSingleProvince(nest.getOne().getProvince()));
            model.addAttribute("province1", salaryService.averageSalaryInSingleProvince(nest.getTwo().getProvince()));
            model.addAttribute("provinceName", nest.getOne().getProvince());
            model.addAttribute("provinceName1", nest.getTwo().getProvince());

            if (salaryService.averageSalaryInSingleProvince(nest.getOne().getProvince())
                    > salaryService.averageSalaryInSingleProvince(nest.getTwo().getProvince())) {
                model.addAttribute("compareTwo",
                        salaryService.compereProvinces(nest.getOne().getProvince(), nest.getTwo().getProvince()));
                model.addAttribute("provinceName4", nest.getOne().getProvince());
                model.addAttribute("provinceName3", nest.getTwo().getProvince());
            } else {
                model.addAttribute("compareTwo",
                        salaryService.compereProvinces(nest.getOne().getProvince(), nest.getTwo().getProvince()));
                model.addAttribute("provinceName3", nest.getOne().getProvince());
                model.addAttribute("provinceName4", nest.getTwo().getProvince());

            }
        } else {
            model.addAttribute("province", " Doesn't found data");
            model.addAttribute("province1", " Doesn't found data");
        }

        return "compare";


    }

    @GetMapping("singleProvince/{name}")
    public String showSingleProvince(@PathVariable String name, Model model) throws Exception {

        model.addAttribute("averageForSingleProvince", salaryService.averageSalaryInSingleProvince(name));
        model.addAttribute("nameOfProvince", name);

        return "singleProvince";
    }


    @GetMapping("singleSector/{sector}")
    public String showSingelSector(@PathVariable String sector, Model model) throws Exception {

        model.addAttribute("averageForSingleSector", salaryService.averageSalaryInSingleSector(sector));
        model.addAttribute("nameOfSector", sector);

        return "singleSector";
    }

}
