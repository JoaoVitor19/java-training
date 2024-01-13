package com.example.javatraining.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.javatraining.entity.Course;
import com.example.javatraining.service.CourseService;

import lombok.AllArgsConstructor;

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
@RequestMapping("/course")
@AllArgsConstructor
public class CourseController {

    private CourseService courseService;

    @GetMapping("/{id}")
    public ResponseEntity<Course> findCourseById(@PathVariable @NonNull Long id) {
        return courseService.findCourseById(id);
    }

    @GetMapping
    public Page<Course> findAllCourses(@RequestParam(defaultValue = "0") int pagina, @RequestParam(defaultValue = "5") int itensPorPagina ) {
        return courseService.findAllCourses(PageRequest.of(pagina, itensPorPagina));
    }

    @PostMapping
    public ResponseEntity<Course> registerCourse(@RequestBody @NonNull Course course) {
        return courseService.registerCourse(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable @NonNull Long id, @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable @NonNull Long id) {
        return courseService.deleteCourse(id);
    }
}
