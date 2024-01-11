package com.example.entity.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Student;
import com.example.service.StudentService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {

    private StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<Student> findStudentById(@PathVariable int id) {
        return studentService.findStudentById(id);
    }

    @GetMapping
    public List<Student> findAllStudents() {
        return studentService.findAllStudents();
    }

    @PostMapping
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        return studentService.registerStudent(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int Id, @RequestBody Student student) {
        return studentService.updateStudent(Id, student);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteStudent(int id) {
        return studentService.deleteStudent(id);
    }
}
