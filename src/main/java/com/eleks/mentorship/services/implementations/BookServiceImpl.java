package com.eleks.mentorship.services.implementations;

import com.eleks.mentorship.entities.Book;
import com.eleks.mentorship.repositories.BookRepository;
import com.eleks.mentorship.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).stream().toList();
    }

    @Override
    public Optional<Book> getBookById(Integer id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> updateBook(Book book) {
        return bookRepository
                .findById(book.getId())
                .map(b -> {
                    b.setAuthor(book.getAuthor());
                    b.setTitle(book.getTitle());
                    b.setPublishYear(book.getPublishYear());
                    b.setGenre(book.getGenre());

                    return bookRepository.save(b);
        });
    }

    @Override
    public boolean deleteBook(Integer id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
