package com.eleks.mentorship.dtos;

import com.eleks.mentorship.validators.PublishYearConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Integer id;
    @NotNull
    @NotBlank
    private String title;
    @NotNull
    @NotBlank
    private String author;
    @NotNull
    @NotBlank
    private String genre;
    @PublishYearConstraint
    private Integer publishYear;
}