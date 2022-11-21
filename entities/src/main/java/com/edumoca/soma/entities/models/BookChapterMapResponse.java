package com.edumoca.soma.entities.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BookChapterMapResponse {
    int bookChapterId;
    int bookId;
    int chapterId;
}
