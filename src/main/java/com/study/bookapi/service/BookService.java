package com.study.bookapi.service;


import com.study.bookapi.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BookService {
    private final List<Book> books;

    public BookService() {
        this.books = new ArrayList<>();
    }

    public Book getById(Long id){
        for(Book book:books){
            if(Objects.equals(book.getId(), id)){
                return book;
            }
        }
        return null;
    }

    public List<Book> getAll(){
        return books;
    }

    public Book addBook(Book book){
        Long newId = (long) (books.size()+1);
        book.setId(newId);
        books.add(book);
        return book;
    }

    public void delete(Long id){
        books.removeIf(book -> Objects.equals(book.getId(), id));
    }


}
