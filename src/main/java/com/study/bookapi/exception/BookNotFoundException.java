package com.study.bookapi.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(long id){
        super("Book not found with id: " + id);
    }
}
