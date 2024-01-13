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
import com.example.javatraining.entity.EvaluationCourse;
import com.example.javatraining.entity.Student;
import com.example.javatraining.repository.CourseRepository;
import com.example.javatraining.repository.EvaluationCourseRepository;
import com.example.javatraining.repository.StudentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EvaluationCourseService {

    private final StudentRepository studentRepository;
    private final EvaluationCourseRepository evaluationCourseRepository;
    private final CourseRepository courseRepository;

    public ResponseEntity<String> evaluate(@NonNull Long studentId, String nameCourse, Integer scoreEvaluation) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }

        Optional<List<Course>> courses = courseRepository.findByName(nameCourse);

        if (courses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }

        if (courses.get().size() > 1) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Have more than one courses with same name");
        }

        Course course = courses.get().get(0);

        EvaluationCourse evaluationCourse = new EvaluationCourse();
        evaluationCourse.setStudent(student.get());
        evaluationCourse.setCourse(course);
        evaluationCourse.setEvaluationScore(scoreEvaluation);

        evaluationCourseRepository.saveAndFlush(evaluationCourse);
        return ResponseEntity.ok("Evaluation score has been saved!");
    }

    public Page<EvaluationCourse> findAllEvaluates(@NonNull PageRequest pageRequest) {
        return evaluationCourseRepository.findAll(pageRequest);
    }

    public ResponseEntity<EvaluationCourse> findById(@NonNull Long id) {
        Optional<EvaluationCourse> evaluationCourse = evaluationCourseRepository.findById(id);
        if (evaluationCourse.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(evaluationCourse.get());
    }

    public ResponseEntity<EvaluationCourse> updateEvaluationCourse(@NonNull Long id, Integer scoreEvaluate) {
        Optional<EvaluationCourse> evaluationCourse = evaluationCourseRepository.findById(id);

        if (evaluationCourse.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        EvaluationCourse evaluationCourseUpdated = evaluationCourse.get();

        evaluationCourseUpdated.setEvaluationScore(scoreEvaluate);
        evaluationCourseRepository.save(evaluationCourseUpdated);
        return ResponseEntity.ok(evaluationCourseUpdated);
    }

    public ResponseEntity<String> deleteEvaluation(@NonNull Long id) {
        Optional<EvaluationCourse> evaluation = evaluationCourseRepository.findById(id);

        if (evaluation.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evaluation not found");
        }

        evaluationCourseRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Evaluation has been deleted");
    }

}
