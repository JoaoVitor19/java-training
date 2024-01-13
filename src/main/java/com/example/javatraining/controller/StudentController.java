package com.example.javatraining.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.javatraining.entity.Student;
import com.example.javatraining.service.StudentService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<Student> findStudentById(@PathVariable @NonNull Long id) {
        return studentService.findStudentById(id);
    }

    @GetMapping
    public Page<Student> findAllStudents(@RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "5") int itensPorPagina) {
        return studentService.findAllStudents(PageRequest.of(pagina, itensPorPagina));
    }

    @PostMapping
    public ResponseEntity<Student> registerStudent(@RequestBody @NonNull Student student) {
        System.out.println("oi");
        return studentService.registerStudent(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable @NonNull Long id,
            @RequestBody @NonNull Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable @NonNull Long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping("/nativesql/{name}")
    public List<Student> findByNameWithNativeQuery(@PathVariable @NonNull String name) {
        return studentService.findByNameWithNativeQuery(name);
    }

    @GetMapping("/jpql/{name}")
    public List<Student> findByNameWithJPQL(@PathVariable @NonNull String name) {
        return studentService.findByNameWithJPQL(name);
    }
}
