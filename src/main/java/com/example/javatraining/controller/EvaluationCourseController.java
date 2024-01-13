package com.example.javatraining.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.javatraining.entity.EvaluationCourse;
import com.example.javatraining.service.EvaluationCourseService;

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

@RestController
@RequestMapping("/evaluation")
@AllArgsConstructor
public class EvaluationCourseController {

    private final EvaluationCourseService evaluationCourseService;

    @PostMapping
    public ResponseEntity<String> evaluate(
            @RequestParam @NonNull Long studentId,
            @RequestParam String nameCourse,
            @RequestParam Integer evaluateScore) {
        return evaluationCourseService.evaluate(studentId, nameCourse, evaluateScore);
    }

    @GetMapping
    public Page<EvaluationCourse> findAllEvaluates(@RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "5") int itensPorPagina) {
        return evaluationCourseService.findAllEvaluates(PageRequest.of(pagina, itensPorPagina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluationCourse> findById(@PathVariable @NonNull Long id) {
        return evaluationCourseService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluationCourse> updateEvaluation(@PathVariable @NonNull Long id,
            @RequestParam Integer scoreEvaluate) {
        return evaluationCourseService.updateEvaluationCourse(id, scoreEvaluate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvaluation(@PathVariable @NonNull Long id) {
        return evaluationCourseService.deleteEvaluation(id);
    }

}
