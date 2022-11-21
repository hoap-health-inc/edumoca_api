package com.edumoca.soma.entities.dtos;

import lombok.Data;

@Data
public class BookDto {
    private int bookId;
    private String bookName;
    private String subjectName;
    private boolean hasDigitalCopy;
    private String bookFormat;
    private String bookLocationId;
    private byte[] coverPage;
}
