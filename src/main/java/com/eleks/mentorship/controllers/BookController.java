package com.eleks.mentorship.controllers;

import com.eleks.mentorship.entities.Book;
import com.eleks.mentorship.exceptions.ResourceNotFoundException;
import com.eleks.mentorship.services.BookService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks(Pageable.unpaged());
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/books-paginated")
    public ResponseEntity<List<Book>> getAllBooksPaginated(@Min(0) @RequestParam(defaultValue = "0") Integer page,
                                                           @Min(1) @RequestParam(defaultValue = "1") Integer size) {
        List<Book> books = bookService.getAllBooks(PageRequest.of(page, size));
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBook(@Min(1) @PathVariable(name = "id") Integer bookId) {
        Book book = bookService.getBookById(bookId).orElseThrow(ResourceNotFoundException::new);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/book")
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        Book newBook = bookService.saveBook(book);
        return new ResponseEntity<>(newBook, HttpStatus.OK);
    }

    @PutMapping("/book")
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        Book updatedBook = bookService.updateBook(book).orElseThrow(ResourceNotFoundException::new);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity deleteBook(@Min(1) @PathVariable(name = "id") Integer bookId) {
        return new ResponseEntity(bookService.deleteBook(bookId) ? HttpStatus.OK : HttpStatus.GONE);
    }
}
