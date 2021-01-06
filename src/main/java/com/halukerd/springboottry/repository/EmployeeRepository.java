package com.halukerd.springboottry.repository;

import com.halukerd.springboottry.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
