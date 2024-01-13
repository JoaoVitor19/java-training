package com.example.javatraining.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.javatraining.entity.Course;
import com.example.javatraining.repository.CourseRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public Page<Course> findAllCourses(@NonNull PageRequest page) {
        return courseRepository.findAll(page);
    }

    public ResponseEntity<Course> registerCourse(@NonNull Course course) {

        Optional<List<Course>> courses = courseRepository.findByName(course.getName());

        if(courses.get().size() > 0){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        courseRepository.saveAndFlush(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }

    public ResponseEntity<Course> findCourseById(@NonNull Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(course.get());
    }

    public ResponseEntity<Course> updateCourse(@NonNull Long id, Course course) {

        Optional<Course> updateCourse = courseRepository.findById(id);

        if (updateCourse.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {

            Course updatedCourse = updateCourse.get();
            updatedCourse.setName(course.getName());
            updatedCourse.setDescription(course.getDescription());
            courseRepository.save(updatedCourse);

            return ResponseEntity.status(HttpStatus.OK).body(updatedCourse);
        }
    }

    public ResponseEntity<String> deleteCourse(@NonNull Long id) {
        Optional<Course> Course = courseRepository.findById(id);

        if (Course.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found!");
        } else {
            courseRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Course deleted!");
        }
    }
}
