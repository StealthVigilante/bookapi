package com.study.bookapi.service;


import com.study.bookapi.dto.BookRequest;
import com.study.bookapi.exception.BookNotFoundException;
import com.study.bookapi.model.Book;
import com.study.bookapi.model.User;
import com.study.bookapi.repository.BookRepository;
import com.study.bookapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }


    //Change this
    public Book getById(Long id, String username){
        return bookRepository.findByIdAndOwnerUsername(id,username).orElseThrow(()->new BookNotFoundException(id));
    }

    //And this
    public List<Book> getAll(String username){
        return bookRepository.findByOwnerUsername(username);
    }

    public Book addBook(BookRequest request, String username){
        User owner = userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("User does not exist"));
        Book book = new Book(null,request.title(),request.author(),owner);
        return bookRepository.save(book);
    }

    public boolean delete(Long id, String username){
        Optional<Book> book = bookRepository.findByIdAndOwnerUsername(id,username);
        if(book.isPresent()){
            bookRepository.delete(book.get());
            return true;
        }
        return false;
    }
}
