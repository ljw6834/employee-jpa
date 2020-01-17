package com.aaa.service;

import com.aaa.model.Employee;
import com.aaa.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository repo;

    @Override
    @Transactional
    public Long insert(Employee e) {
        Employee emp = repo.save(e);
        return emp.getId();
    }

    @Override
    public List<Employee> findAll() {
        return repo.findAll();

    }
}
