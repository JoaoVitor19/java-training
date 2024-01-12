package com.example.javatraining.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.javatraining.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
}
