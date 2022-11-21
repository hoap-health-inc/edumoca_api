package com.edumoca.soma.entities.models;

import lombok.Data;

@Data
public class BookChapterMapRequest {
    private int bookId;
    private int chapterId;
}
