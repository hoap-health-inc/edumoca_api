package com.edumoca.soma.services.controllers;

import com.edumoca.soma.entities.BookChapterMap;
import com.edumoca.soma.entities.dtos.BookChapterMapDto;
import com.edumoca.soma.entities.models.BookChapterMapRequest;
import com.edumoca.soma.services.services.BookChapterMapService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/bookChapterMap")
@AllArgsConstructor
public class BookChapterMapController {

    private final BookChapterMapService bookChapterMapService;
    private final ModelMapper modelMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookChapterMapDto> createBookChapter(@RequestBody BookChapterMapRequest bookChapterMapRequest){
        return new ResponseEntity<>(bookChapterMapService.createBookChapter(modelMapper.map(bookChapterMapRequest, BookChapterMap.class)), HttpStatus.CREATED);
    }
}
