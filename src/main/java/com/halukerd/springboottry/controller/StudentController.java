package com.halukerd.springboottry.controller;

import com.halukerd.springboottry.model.Student;
import com.halukerd.springboottry.repository.StudentRepository;
import com.halukerd.springboottry.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(produces = "application/json")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(value = "{id}", produces = "application/json")
    public Student getStudentById(@Param("id") Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping(consumes = "application/json")
    public void saveStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
    }
}