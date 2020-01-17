package com.aaa.controller;

import com.aaa.model.Employee;
import com.aaa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {


    private EmployeeService empService;

    public EmployeeController(EmployeeService empService){
        this.empService = empService;
    }

    @PostMapping("/saveEmp")
    public Long saveEmp(@RequestBody Employee e){
        return empService.insert(e);
    }

    @GetMapping("/")
    public List<Employee> getAll(){
        return empService.findAll();
    }

}
