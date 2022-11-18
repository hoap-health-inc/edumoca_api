package com.edumoca.soma.services.controllers;

import com.edumoca.soma.entities.dtos.ChapterDTO;
import com.edumoca.soma.entities.models.ChapterRequest;
import com.edumoca.soma.entities.models.ChapterResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edumoca.soma.entities.Chapter;
import com.edumoca.soma.services.services.ChapterService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/chapter")
@AllArgsConstructor
public class ChapterController {

	private final ChapterService chapterService;
	private final ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<ChapterDTO> createChapter(@Valid @RequestBody ChapterRequest chapterRequest) {
		return new ResponseEntity<>(chapterService.createChapter(modelMapper.map(chapterRequest,Chapter.class)), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{chapterId}")
	public ResponseEntity<ChapterDTO> updateChapter(@RequestBody ChapterRequest chapterRequest,@PathVariable("chapterId") Integer chapterId) {
		return new ResponseEntity<>(chapterService.updateChapter(modelMapper.map(chapterRequest,Chapter.class),chapterId),HttpStatus.OK);
	}

	@GetMapping(value = "/{bookId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ChapterDTO>> getAllChaptersByBook(@PathVariable("bookId") Integer bookId){
		return new ResponseEntity<>(chapterService.getAllChaptersByBook(bookId),HttpStatus.OK);
	}
	@GetMapping(value = "/{bookId}/{chapterId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ChapterDTO> getChapterByBookAndChapter(@PathVariable("bookId") Integer bookId, @PathVariable("chapterId") Integer chapterId) {
		return new ResponseEntity<>(chapterService.getChapterByBookAndChapter(bookId,chapterId),HttpStatus.OK);
	}
}
