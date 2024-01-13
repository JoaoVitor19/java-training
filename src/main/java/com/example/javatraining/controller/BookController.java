package com.example.javatraining.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.javatraining.entity.Book;
import com.example.javatraining.service.BookService;

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
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

    private BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable @NonNull Long id) {
        return bookService.findBookById(id);
    }

    @GetMapping
    public Page<Book> findAllBooks(@RequestParam(defaultValue = "0") int pagina, @RequestParam(defaultValue = "5") int itensPorPagina ) {
        return bookService.findAllBooks(PageRequest.of(pagina, itensPorPagina));
    }

    @PostMapping
    public ResponseEntity<Book> registerBook(@RequestBody @NonNull Book book) {
        return bookService.registerBook(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable @NonNull Long id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable @NonNull Long id) {
        return bookService.deleteBook(id);
    }
}
