package com.study.bookapi.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Long id;
    private String title;
    private String author;
}
