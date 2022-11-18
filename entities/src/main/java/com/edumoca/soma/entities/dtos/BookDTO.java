package com.edumoca.soma.entities.dtos;

import lombok.Data;

@Data
public class BookDTO {
    private int bookId;
    private String bookName;
    //private String gradeName;
    private String subjectName;
    private boolean hasDigitalCopy;
    private String bookFormat;
    private byte[] book;
    private byte[] coverPage;
}
