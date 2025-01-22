package com.dev.employeeapp.controller;

import com.dev.employeeapp.entity.Employee;
import com.dev.employeeapp.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    //add mapping for list
    @GetMapping("/list")
    public String listEmployee(Model model){

        List<Employee> employees = employeeService.findAll();

        model.addAttribute("employees",employees);

        return "list-employees";
    }
}
