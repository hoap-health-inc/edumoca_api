package com.edumoca.soma.services.controllers;

import com.edumoca.soma.entities.Topic;
import com.edumoca.soma.entities.dtos.TopicDto;
import com.edumoca.soma.entities.models.TopicRequest;
import com.edumoca.soma.services.services.TopicService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/topic")
@AllArgsConstructor
public class TopicController {

	private final TopicService topicService;
	private final ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<TopicDto> createTopic(@Valid @RequestBody TopicRequest topicRequest) {
		return new ResponseEntity<>(topicService.createTopic(modelMapper.map(topicRequest,Topic.class)), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{topicId}")
	public ResponseEntity<TopicDto> updateTopic(@Valid @RequestBody TopicRequest topicRequest, @PathVariable("topicId") Integer topicId) {
		return new ResponseEntity<>(topicService.updateTopic(modelMapper.map(topicRequest,Topic.class),topicId),HttpStatus.OK);
	}

	@GetMapping(value = "/{bookChapterId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TopicDto>> getTopicsListByBookChapter(@PathVariable("bookChapterId") Integer bookChapterId){
		return new ResponseEntity<>(topicService.getTopicsListByBookChapter(bookChapterId),HttpStatus.OK);
	}


}
