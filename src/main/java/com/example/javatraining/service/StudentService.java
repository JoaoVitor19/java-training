package com.example.javatraining.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.javatraining.entity.Student;
import com.example.javatraining.repository.StudentRepository;

import io.micrometer.common.lang.NonNull;
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

    public Page<Student> findAllStudents(PageRequest page) {
        return studentRepository.findAll(page);
    }

    public ResponseEntity<Student> registerStudent(Student student) {
            studentRepository.saveAndFlush(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    public ResponseEntity<Student> updateStudent(Student studentUpdated) {

        if (studentRepository.findById(studentUpdated.getId()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            studentRepository.saveAndFlush(studentUpdated);
            return ResponseEntity.status(HttpStatus.OK).body(studentUpdated);
        }
    }

    public ResponseEntity<String> deleteStudent(Long id) {
        Optional<Student> Student = studentRepository.findById(id);

        if (Student.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            studentRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Student deleted!");
        }
    }
}
