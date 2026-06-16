package com.study.bookapi.controller;

import com.study.bookapi.model.Book;
import com.study.bookapi.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getALl(){
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id){
        return bookService.getById(id);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        bookService.delete(id);
    }

}
