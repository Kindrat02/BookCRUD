package com.eleks.mentorship.repositories;

import com.eleks.mentorship.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

}