package com.edumoca.soma.services.services;

import com.edumoca.soma.entities.BookChapterMap;
import com.edumoca.soma.entities.dtos.BookChapterMapDto;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.Map;
import java.util.Set;

public interface BookChapterMapService {
    public BookChapterMapDto createBookChapter(BookChapterMap bookChapterMapping);

    public Map<String, Set<BookChapterMapDto>> loadBookChapter(XSSFSheet bookChapterMapSheet, String bookChapterSheetName);
}
