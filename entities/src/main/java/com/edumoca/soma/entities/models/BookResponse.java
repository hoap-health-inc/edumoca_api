package com.edumoca.soma.entities.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BookResponse {
    int bookId;
    String bookName;
   // String gradeName;
    String subjectName;
    boolean hasDigitalCopy;
    String bookFormat;
    String bookLocation;
    String bookCoverPageLocation;
}
