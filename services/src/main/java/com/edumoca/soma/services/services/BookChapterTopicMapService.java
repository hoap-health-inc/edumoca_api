package com.edumoca.soma.services.services;

import com.edumoca.soma.entities.BookChapterTopicMap;
import com.edumoca.soma.entities.dtos.BookChapterTopicMapDto;
import com.edumoca.soma.entities.dtos.InstitutionDto;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public interface BookChapterTopicMapService {

    public BookChapterTopicMapDto createBookChapterTopicMap(BookChapterTopicMap bookChapterTopicMap);

    public Map<String, Set<BookChapterTopicMapDto>>  loadBookChapterTopic(XSSFSheet bookChapterTopicSheet, String bookChapterTopicSheetName)throws IOException;

}
