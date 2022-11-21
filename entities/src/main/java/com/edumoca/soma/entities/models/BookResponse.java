package com.edumoca.soma.entities.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BookResponse {
    int bookId;
    String bookName;
    String subjectName;
    boolean hasDigitalCopy;
    String bookFormat;
    String bookLocationId;
    String coverPage;
}
