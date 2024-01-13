package com.example.javatraining.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.javatraining.entity.Student;
import com.example.javatraining.repository.StudentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public ResponseEntity<Student> findStudentById(@NonNull Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(student.get());
    }

    public Page<Student> findAllStudents(@NonNull PageRequest page) {
        return studentRepository.findAll(page);
    }

    public ResponseEntity<Student> registerStudent(@NonNull Student student) {
        studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    public ResponseEntity<Student> updateStudent(@NonNull Long id, @NonNull Student student) {

        Optional<Student> updateStudent = studentRepository.findById(id);

        if (updateStudent.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {

            Student updatedStudent = updateStudent.get();

            updatedStudent.setName(student.getName());
            updatedStudent.setEmail(student.getEmail());
            updatedStudent.setDateOfBirth(student.getDateOfBirth());

            studentRepository.save(updatedStudent);

            return ResponseEntity.status(HttpStatus.OK).body(updatedStudent);
        }
    }

    public ResponseEntity<String> deleteStudent(@NonNull Long id) {
        Optional<Student> Student = studentRepository.findById(id);

        if (Student.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found!");
        } else {
            studentRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Student deleted!");
        }
    }
}
