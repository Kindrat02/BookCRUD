package com.eleks.mentorship.services;

import com.eleks.mentorship.dtos.BookDTO;
import com.eleks.mentorship.entities.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDTO> getAllBooks(Pageable pageable);
    Optional<BookDTO> getBookById(Integer id);
    BookDTO saveBook(BookDTO book);
    Optional<BookDTO> updateBook(BookDTO book);
    boolean deleteBook(Integer id);
}
