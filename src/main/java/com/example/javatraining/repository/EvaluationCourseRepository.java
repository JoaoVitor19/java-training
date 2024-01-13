package com.example.javatraining.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.javatraining.entity.EvaluationCourse;

@Repository
public interface EvaluationCourseRepository extends JpaRepository<EvaluationCourse, Long> {

}
