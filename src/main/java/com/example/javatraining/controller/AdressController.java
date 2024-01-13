package com.example.javatraining.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.javatraining.entity.Adress;
import com.example.javatraining.entity.Student;
import com.example.javatraining.service.AdressService;

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
@RequestMapping("/adress")
@AllArgsConstructor
public class AdressController {

    private AdressService adressService;

    @GetMapping("/{id}")
    public ResponseEntity<Adress> findAdressById(@PathVariable @NonNull Long id) {
        return adressService.findAdressById(id);
    }

    @GetMapping
    public Page<Adress> findAllAdresss(@RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "5") int itensPorPagina) {
        return adressService.findAllAdresss(PageRequest.of(pagina, itensPorPagina));
    }

    @PostMapping
    public ResponseEntity<Adress> registerAdress(@RequestBody @NonNull Adress adress) {
        return adressService.registerAdress(adress);
    }

    @PostMapping("/student/{id}")
    public ResponseEntity<Student> registerAdressInStudent(@PathVariable @NonNull Long id,
            @RequestBody @NonNull Adress adress) {
        return adressService.registerAdressInStudent(id, adress);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Adress> updateAdress(@PathVariable @NonNull Long id, @RequestBody Adress adress) {
        return adressService.updateAdress(id, adress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdress(@PathVariable @NonNull Long id) {
        return adressService.deleteAdress(id);
    }
}
