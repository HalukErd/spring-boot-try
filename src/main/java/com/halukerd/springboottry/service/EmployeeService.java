package com.halukerd.springboottry.service;

import com.halukerd.springboottry.controller.EmployeeController;
import com.halukerd.springboottry.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeController employeeController;

    @Autowired
    public EmployeeService(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    public List<Employee> getAllEmployee() {
        return employeeController.getAllEmployee();
    }

    public Employee createEmployee(Employee employee) {
        return employeeController.createEmployee(employee);
    }
}