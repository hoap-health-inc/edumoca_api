package com.edumoca.soma.services.controllers;

import com.edumoca.soma.entities.Chapter;
import com.edumoca.soma.entities.dtos.ChapterDto;
import com.edumoca.soma.entities.models.ChapterRequest;
import com.edumoca.soma.services.services.ChapterService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/chapter")
@AllArgsConstructor
public class ChapterController {

	private final ChapterService chapterService;
	private final ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<ChapterDto> createChapter(@Valid @RequestBody ChapterRequest chapterRequest) {
		return new ResponseEntity<>(chapterService.createChapter(modelMapper.map(chapterRequest,Chapter.class)), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{chapterId}")
	public ResponseEntity<ChapterDto> updateChapter(@RequestBody ChapterRequest chapterRequest, @PathVariable("chapterId") Integer chapterId) {
		return new ResponseEntity<>(chapterService.updateChapter(modelMapper.map(chapterRequest,Chapter.class),chapterId),HttpStatus.OK);
	}

	@GetMapping(value = "/{bookId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ChapterDto>> getChaptersByBook(@PathVariable("bookId") Integer bookId){
		return new ResponseEntity<>(chapterService.getChapterListByBookId(bookId),HttpStatus.OK);
	}
}
