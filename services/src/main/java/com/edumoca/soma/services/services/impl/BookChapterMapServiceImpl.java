package com.edumoca.soma.services.services.impl;

import com.edumoca.soma.entities.Book;
import com.edumoca.soma.entities.BookChapterMap;
import com.edumoca.soma.entities.Chapter;
import com.edumoca.soma.entities.dtos.BookChapterMapDto;
import com.edumoca.soma.entities.repositories.BookChapterMapRepository;
import com.edumoca.soma.entities.repositories.BookRepository;
import com.edumoca.soma.entities.repositories.ChapterRepository;
import com.edumoca.soma.services.services.BookChapterMapService;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class BookChapterMapServiceImpl implements BookChapterMapService {

    private final BookChapterMapRepository bookChapterMapRepository;
    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;
    private final ChapterRepository chapterRepository;
    @Override
    public BookChapterMapDto createBookChapter(BookChapterMap bookChapterMapping) {
        return modelMapper.map(bookChapterMapRepository.save(bookChapterMapping), BookChapterMapDto.class);
    }

    @Override
    public Map<String, Set<BookChapterMapDto>> loadBookChapter(XSSFSheet bookChapterMapSheet, String bookChapterSheetName){
        Map<String,Set<BookChapterMapDto>> bookChapterMap = new HashMap<>();
        Set<BookChapterMapDto> bookChapterSet = new HashSet<>();
        bookChapterMapSheet.rowIterator().forEachRemaining(row->{
            if(row.getRowNum()>0){
                BookChapterMap bookChapterMap1 = new BookChapterMap();
                bookChapterMap1.setBook(modelMapper.map(bookRepository.getBookByBookId(new Double(row.getCell(0).getNumericCellValue()).intValue()), Book.class));
                bookChapterMap1.setChapter(modelMapper.map(chapterRepository.getChapterByChapterId(new Double(row.getCell(1).getNumericCellValue()).intValue()),Chapter.class));
                bookChapterMapRepository.save(bookChapterMap1);
                BookChapterMapDto bookChapterMapDto = new BookChapterMapDto();
                bookChapterMapDto.setBookId(bookChapterMap1.getBook().getBookId());
                bookChapterMapDto.setChapterId(bookChapterMap1.getChapter().getChapterId());
                bookChapterSet.add(bookChapterMapDto);
            }
        });
        bookChapterMap.put(bookChapterSheetName,bookChapterSet);
        return bookChapterMap;
    }


}
