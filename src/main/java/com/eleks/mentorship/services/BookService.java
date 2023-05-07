package com.eleks.mentorship.services;

import com.eleks.mentorship.entities.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks(Pageable pageable);
    Optional<Book> getBookById(Integer id);
    Book saveBook(Book book);
    Optional<Book> updateBook(Book book);
    boolean deleteBook(Integer id);
}
