package com.eleks.mentorship.mappers;

import com.eleks.mentorship.dtos.BookDTO;
import com.eleks.mentorship.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO toDTO(Book model);
    Book toModel(BookDTO dto);
}