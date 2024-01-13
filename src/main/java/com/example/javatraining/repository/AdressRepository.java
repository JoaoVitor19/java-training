package com.example.javatraining.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.javatraining.entity.Adress;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Long> {
    
}
