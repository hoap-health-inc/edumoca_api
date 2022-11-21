package com.edumoca.soma.entities.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Data
public class BookRequest implements Serializable {
    @JsonIgnore
    private int bookId;
    @NotBlank(message = "Book Name is required")
    private String bookName;
    private GradeIdRequest gradeIdRequest;
    private Set<SectionIdRequest> sectionIdRequestSet;
    private SubjectIdRequest subjectIdRequest;
    private boolean hasDigitalCopy;
    private String bookFormat;
}
