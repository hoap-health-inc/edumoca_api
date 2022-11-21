package com.edumoca.soma.services.services.impl;

import com.edumoca.soma.entities.BookChapterMap;
import com.edumoca.soma.entities.BookChapterTopicMap;
import com.edumoca.soma.entities.Topic;
import com.edumoca.soma.entities.dtos.BookChapterTopicMapDto;
import com.edumoca.soma.entities.dtos.InstitutionDto;
import com.edumoca.soma.entities.dtos.TopicDto;
import com.edumoca.soma.entities.repositories.BookChapterMapRepository;
import com.edumoca.soma.entities.repositories.BookChapterTopicMapRepository;
import com.edumoca.soma.entities.repositories.TopicRepository;
import com.edumoca.soma.services.services.BookChapterTopicMapService;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@AllArgsConstructor
public class BookChapterTopicMapServiceImpl implements BookChapterTopicMapService {

    private final BookChapterTopicMapRepository bookChapterTopicMapRepository;
    private final ModelMapper modelMapper;
    private final BookChapterMapRepository bookChapterMapRepository;
    private final TopicRepository topicRepository;

    @Override
    public BookChapterTopicMapDto createBookChapterTopicMap(BookChapterTopicMap bookChapterTopicMap) {
        return modelMapper.map(bookChapterTopicMapRepository.save(bookChapterTopicMap), BookChapterTopicMapDto.class);
    }

    @Override
    public Map<String, Set<BookChapterTopicMapDto>> loadBookChapterTopic(XSSFSheet bookChapterTopicSheet, String bookChapterTopicSheetName) throws IOException {
        Map<String,Set<BookChapterTopicMapDto>> bookChapterTopicMap = new HashMap<>();
        Set<BookChapterTopicMapDto> bookChapterTopicSet = new HashSet<>();
        bookChapterTopicSheet.rowIterator().forEachRemaining(row->{
            if(row.getRowNum()>0){
                BookChapterTopicMap bookChapterTopicMap1 = new BookChapterTopicMap();
                bookChapterTopicMap1.setBookChapterMapping(modelMapper.map(bookChapterMapRepository.getBookChapterMapByBookChapterMapId(new Double(row.getCell(0).getNumericCellValue()).intValue()), BookChapterMap.class));
                bookChapterTopicMap1.setTopic(modelMapper.map(topicRepository.getTopicByTopicId(new Double(row.getCell(1).getNumericCellValue()).intValue()),Topic.class));
                bookChapterTopicMapRepository.save(bookChapterTopicMap1);
                BookChapterTopicMapDto bookChapterTopicMapDto = new BookChapterTopicMapDto();
                bookChapterTopicMapDto.setBookChapterMapId(bookChapterTopicMap1.getBookChapterTopicMappingId());
                bookChapterTopicMapDto.setBookChapterTopicId(bookChapterTopicMap1.getBookChapterMapping().getBookChapterMapId());
                bookChapterTopicMapDto.setTopicId(bookChapterTopicMap1.getTopic().getTopicId());
                bookChapterTopicSet.add(bookChapterTopicMapDto);
            }
        });
        bookChapterTopicMap.put(bookChapterTopicSheetName,bookChapterTopicSet);
        return bookChapterTopicMap;
    }
}
