package com.eleks.mentorship.services.implementations;

import com.eleks.mentorship.entities.Book;
import com.eleks.mentorship.repositories.BookRepository;
import com.eleks.mentorship.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;
    private BookService bookService;
    private Book book;

    @BeforeEach
    public void setup() {
        book = new Book(1, "Title", "Author", "fiction", 2023);
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    void getAllBooks_shouldReturnEmptyList() {
        Pageable wholePage = Pageable.unpaged();
        when(bookRepository.findAll(wholePage)).thenReturn(Page.empty());

        List<Book> result = bookService.getAllBooks(wholePage);

        verify(bookRepository).findAll(wholePage);
        assertEquals(0, result.size());
    }

    @Test
    void getBookById_shouldReturnNotEmptyOptional() {
        Integer id = book.getId();
        when(bookRepository.findById(anyInt())).thenReturn(Optional.of(book));

        Optional<Book> result = bookService.getBookById(id);

        verify(bookRepository).findById(id);
        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }

    @Test
    void saveBook_shouldreturnSavedObject() {
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book result = bookService.saveBook(book);

        verify(bookRepository).save(book);
        assertEquals("Title", result.getTitle());
    }

    @Test
    void updateBook_shouldReturnUpdatedObject() {
        Book newBook = new Book(1, "New Title", "Author", "fiction", 2022);
        when(bookRepository.findById(anyInt())).thenReturn(Optional.of(book));

        bookService.updateBook(newBook);

        ArgumentCaptor<Book> argCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(argCaptor.capture());
        assertEquals(1, argCaptor.getValue().getId());
        assertEquals("New Title", argCaptor.getValue().getTitle());
        assertEquals(2022, argCaptor.getValue().getPublishYear());
    }

    @Test
    void deleteBook_shouldReturnFalse() {
        when(bookRepository.existsById(anyInt())).thenReturn(false);

        boolean result = bookService.deleteBook(book.getId());

        verify(bookRepository).existsById(1);
        verify(bookRepository, never()).deleteById(1);
        assertFalse(result);
    }

    @Test
    void deleteBook_shouldReturnTrue() {
        when(bookRepository.existsById(anyInt())).thenReturn(true);

        boolean result = bookService.deleteBook(book.getId());

        verify(bookRepository).existsById(1);
        verify(bookRepository).deleteById(1);
        assertTrue(result);
    }
}