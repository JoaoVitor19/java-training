package com.example.javatraining.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.javatraining.entity.Book;
import com.example.javatraining.repository.BookRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public ResponseEntity<Book> findBookById(@NonNull Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(book.get());
    }

    public Page<Book> findAllBooks(@NonNull PageRequest page) {
        return bookRepository.findAll(page);
    }

    public ResponseEntity<Book> registerBook(@NonNull Book book) {
        bookRepository.saveAndFlush(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    public ResponseEntity<Book> updateBook(@NonNull Long id, Book book) {

        Optional<Book> updateBook = bookRepository.findById(id);

        if (updateBook.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {

            Book updatedBook = updateBook.get();
            updatedBook.setAuthor(book.getAuthor());
            updatedBook.setPages(book.getPages());
            updatedBook.setTitle(book.getTitle());
            bookRepository.save(updatedBook);

            return ResponseEntity.status(HttpStatus.OK).body(updatedBook);
        }
    }

    public ResponseEntity<String> deleteBook(@NonNull Long id) {
        Optional<Book> Book = bookRepository.findById(id);

        if (Book.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found!");
        } else {
            bookRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Book deleted!");
        }
    }
}
