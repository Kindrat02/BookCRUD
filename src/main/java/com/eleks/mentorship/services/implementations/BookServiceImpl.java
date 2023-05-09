package com.eleks.mentorship.services.implementations;

import com.eleks.mentorship.dtos.BookDTO;
import com.eleks.mentorship.entities.Book;
import com.eleks.mentorship.mappers.BookMapper;
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
    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookDTO> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).stream().map(bookMapper::toDTO).toList();
    }

    @Override
    public Optional<BookDTO> getBookById(Integer id) {
        return bookRepository.findById(id).map(bookMapper::toDTO);
    }

    @Override
    public BookDTO saveBook(BookDTO book) {
        Book savedBook = bookRepository.save(bookMapper.toModel(book));
        return bookMapper.toDTO(savedBook);
    }

    @Override
    public Optional<BookDTO> updateBook(BookDTO book) {
        return bookRepository
                .findById(book.getId())
                .map(b -> {
                    b.setAuthor(book.getAuthor());
                    b.setTitle(book.getTitle());
                    b.setPublishYear(book.getPublishYear());
                    b.setGenre(book.getGenre());

                    Book savedBook = bookRepository.save(b);
                    return bookMapper.toDTO(savedBook);
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
