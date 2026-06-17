package com.study.bookapi.controller;

import com.study.bookapi.dto.BookRequest;
import com.study.bookapi.dto.BookResponse;
import com.study.bookapi.model.Book;
import com.study.bookapi.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public BookResponse toResponse(Book book){
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor());
    }

    @GetMapping
    public List<BookResponse> getALl(){
        List<Book> books =bookService.getAll();
        List<BookResponse> booksResponse = new ArrayList<>();
        for(Book book:books){
            booksResponse.add(toResponse(book));
        }

        return booksResponse;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getById(@PathVariable Long id){
        Book book = bookService.getById(id);
        return ResponseEntity.ok(toResponse(book));

    }

    @PostMapping
    public ResponseEntity<BookResponse> addBook(@Valid @RequestBody BookRequest request){
        Book book = bookService.addBook(new Book(null, request.title(), request.author()));
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(bookService.delete(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
