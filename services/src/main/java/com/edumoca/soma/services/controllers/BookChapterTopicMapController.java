package com.edumoca.soma.services.controllers;

import com.edumoca.soma.entities.BookChapterTopicMap;
import com.edumoca.soma.entities.dtos.BookChapterTopicMapDto;
import com.edumoca.soma.entities.models.BookChapterTopicMapRequest;
import com.edumoca.soma.services.services.BookChapterTopicMapService;
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
@RequestMapping(value = "/bookChapterTopicMap")
@AllArgsConstructor
public class BookChapterTopicMapController {

   private final BookChapterTopicMapService bookChapterTopicMapService;
   private final ModelMapper modelMapper;

   @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<BookChapterTopicMapDto> createBookChapterTopicMap(@RequestBody BookChapterTopicMapRequest bookChapterTopicMapRequest){
       return new ResponseEntity<>(bookChapterTopicMapService.createBookChapterTopicMap(modelMapper.map(bookChapterTopicMapRequest, BookChapterTopicMap.class)), HttpStatus.OK);
   }
}
