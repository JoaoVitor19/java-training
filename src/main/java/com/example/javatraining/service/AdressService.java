package com.example.javatraining.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.javatraining.entity.Adress;
import com.example.javatraining.entity.Student;
import com.example.javatraining.repository.AdressRepository;
import com.example.javatraining.repository.StudentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdressService {

    private final AdressRepository adressRepository;
    private final StudentRepository studentRepository;

    public ResponseEntity<Adress> findAdressById(@NonNull Long id) {
        Optional<Adress> adress = adressRepository.findById(id);
        if (adress.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(adress.get());
    }

    public Page<Adress> findAllAdresss(@NonNull PageRequest page) {
        return adressRepository.findAll(page);
    }

    public ResponseEntity<Adress> registerAdress(@NonNull Adress adress) {
        adressRepository.saveAndFlush(adress);
        return ResponseEntity.status(HttpStatus.CREATED).body(adress);
    }

    public ResponseEntity<Student> registerAdressInStudent(@NonNull Long id, @NonNull Adress adress) {

        Optional<Student> student = studentRepository.findById(id);

        if (student.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Student studentUpdated = student.get();
        studentUpdated.setAdress(adress);
        adressRepository.save(adress);
        studentRepository.save(studentUpdated);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentUpdated);
    }

    public ResponseEntity<Adress> updateAdress(@NonNull Long id, Adress adress) {

        Optional<Adress> updateAdress = adressRepository.findById(id);

        if (updateAdress.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {

            Adress updatedAdress = updateAdress.get();

            updatedAdress.setCity(adress.getCity());
            updatedAdress.setCountry(adress.getCountry());
            updatedAdress.setState(adress.getState());
            updatedAdress.setStreet(adress.getStreet());
            updatedAdress.setZipCode(adress.getZipCode());

            adressRepository.saveAndFlush(updatedAdress);

            return ResponseEntity.status(HttpStatus.OK).body(updatedAdress);
        }
    }

    public ResponseEntity<String> deleteAdress(@NonNull Long id) {
        Optional<Adress> Adress = adressRepository.findById(id);

        if (Adress.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Adress not found!");
        } else {
            adressRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Adress deleted!");
        }
    }
}
