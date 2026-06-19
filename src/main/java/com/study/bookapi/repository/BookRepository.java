package com.study.bookapi.repository;

import com.study.bookapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByOwnerUsername(String username);
    Optional<Book> findByIdAndOwnerUsername(Long id,String username);

}
