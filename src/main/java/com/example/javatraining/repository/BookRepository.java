package com.example.javatraining.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.javatraining.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
}
