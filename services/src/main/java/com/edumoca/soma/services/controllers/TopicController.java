package com.edumoca.soma.services.controllers;

import com.edumoca.soma.entities.dtos.TopicDTO;
import com.edumoca.soma.entities.models.TopicRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edumoca.soma.entities.Topic;
import com.edumoca.soma.services.services.TopicService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/topic")
@AllArgsConstructor
public class TopicController {

	private final TopicService topicService;
	private final ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<TopicDTO> createTopic(@Valid @RequestBody TopicRequest topicRequest) {
		return new ResponseEntity<>(topicService.createTopic(modelMapper.map(topicRequest,Topic.class)), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{topicId}")
	public ResponseEntity<TopicDTO> updateTopic(@Valid @RequestBody TopicRequest topicRequest,@PathVariable("topicId") Integer topicId) {
		return new ResponseEntity<>(topicService.updateTopic(modelMapper.map(topicRequest,Topic.class),topicId),HttpStatus.OK);
	}

	@GetMapping(value = "/{chapterId}/all",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TopicDTO>> getTopicsByChapter(@PathVariable("chapterId") Integer chapterId){
		return new ResponseEntity<>(topicService.getAllTopicsByChapter(chapterId),HttpStatus.OK);
	}

	@GetMapping(value = "/{chapterId}/{topicId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TopicDTO> getTopicByChapterAndTopic(@PathVariable("chapterId") Integer chapterId, @PathVariable("topicId") Integer topicId) {
		return new ResponseEntity<>(topicService.getTopicByChapterAndTopic(chapterId,topicId),HttpStatus.OK);
	}

}
