package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.entity.Student;

@Service
public class StudentService {

    // private ArrayList<Student> studentList = new ArrayList<Student>();
    
    private static ArrayList<Student> studentList = new ArrayList<Student>();

    public ResponseEntity<Student> findStudentById(int id) {
        Student student = studentList.get(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    public List<Student> findAllStudents() {
        return studentList;
    }

    public ResponseEntity<Student> registerStudent(Student student) {
        if (studentList.contains(student)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(student);
        }
    }

    public ResponseEntity<Student> updateStudent(int Id, Student student) {

        Student existingStudent = studentList.get(Id);

        if (existingStudent == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            studentList.set(Id, student);
            return ResponseEntity.status(HttpStatus.OK).body(existingStudent);
        }
    }

    public ResponseEntity<String> deleteStudent(int id) {
        Student existingStudent = studentList.get(id);

        if (existingStudent == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            studentList.remove(id);
            return ResponseEntity.status(HttpStatus.OK).body("Student removed!");
        }
    }
}
