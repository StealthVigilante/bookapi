package com.study.bookapi.service;


import com.study.bookapi.exception.BookNotFoundException;
import com.study.bookapi.model.Book;
import com.study.bookapi.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getById(Long id){
        return bookRepository.findById(id).orElseThrow(()->new BookNotFoundException(id));
    }

    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public boolean delete(Long id){
        if(bookRepository.existsById(id)){
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
