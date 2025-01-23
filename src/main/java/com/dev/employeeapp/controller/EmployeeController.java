package com.dev.employeeapp.controller;

import com.dev.employeeapp.entity.Employee;
import com.dev.employeeapp.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee",employee);

        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){

        employeeService.save(employee);

        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int id,Model model){

        Employee tempEmployee = employeeService.findById(id);

        model.addAttribute("employee",tempEmployee);


        return "employees/employee-form";
    }
}
