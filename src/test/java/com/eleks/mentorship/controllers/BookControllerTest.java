package com.eleks.mentorship.controllers;

import com.eleks.mentorship.dtos.BookDTO;
import com.eleks.mentorship.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private BookDTO book;

    @BeforeEach
    public void setup() {
        book = new BookDTO(null, "Title", "Author", "fiction", 1937);
    }

    @Test
    @DisplayName("POST /api/book - OK")
    void addBook_shouldReturnValidObject() throws Exception {
        BookDTO bookDtoReturn = new BookDTO(1, "Title", "Author", "fiction", 1937);
        when(bookService.saveBook(any(BookDTO.class))).thenReturn(bookDtoReturn);

        mockMvc.perform(post("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(book)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("POST /api/book - BAD REQUEST")
    void addBook_shouldThrowException() throws Exception {
        book.setId(1);

        mockMvc.perform(post("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(book)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("PUT /api/book - OK")
    void updateBook_shouldReturnValidObject() throws Exception {
        book.setId(1);
        when(bookService.updateBook(any(BookDTO.class))).thenReturn(Optional.ofNullable(book));

        mockMvc.perform(put("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(book)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("PUT /api/book - BAD REQUEST")
    void updateBook_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(put("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(book)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("DELETE /api/book/{id} - OK")
    void deleteBook_shouldReturnOK() throws Exception {
        when(bookService.deleteBook(anyInt())).thenReturn(true);

        mockMvc.perform(delete("/book/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /api/book/{id} - GONE")
    void deleteBook_shouldReturnGone() throws Exception {
        when(bookService.deleteBook(anyInt())).thenReturn(false);

        mockMvc.perform(delete("/book/1"))
                .andExpect(status().isGone());
    }

    @Test
    @DisplayName("GET /api/book/{id} - OK")
    void getBook_shouldReturnObject() throws Exception {
        when(bookService.getBookById(anyInt())).thenReturn(Optional.of(book));

        mockMvc.perform(get("/book/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/book/{id} - NOT FOUND")
    void getBook_shouldReturnNotFound() throws Exception {
        when(bookService.getBookById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(get("/book/123456")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /api/book/{id} - BAD REQUEST")
    void getBook_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/book/-1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
