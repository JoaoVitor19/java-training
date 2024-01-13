package com.example.javatraining.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.javatraining.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<List<Course>> findByName(@Param("courseName") String name);
}
