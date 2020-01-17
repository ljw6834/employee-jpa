package com.aaa.service;

import com.aaa.model.Employee;

import java.util.List;

public interface EmployeeService {

    Long insert (Employee e);

    List<Employee> findAll ();

}
