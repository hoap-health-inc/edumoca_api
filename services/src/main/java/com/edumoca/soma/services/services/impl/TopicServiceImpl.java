package com.edumoca.soma.services.services.impl;

import com.edumoca.soma.entities.Topic;
import com.edumoca.soma.entities.dtos.TopicDto;
import com.edumoca.soma.entities.models.TopicResponse;
import com.edumoca.soma.entities.repositories.TopicRepository;
import com.edumoca.soma.services.services.TopicService;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TopicServiceImpl implements TopicService{

	private final TopicRepository topicRepository;
 	private final ModelMapper modelMapper;
	@Override
	public TopicDto createTopic(Topic topic) {
		return modelMapper.map(topicRepository.save(topic), TopicDto.class);
	}

	@Override
	public TopicDto updateTopic(Topic topic, Integer topicId) {
		Optional<TopicResponse> topicResponse = Optional.ofNullable(topicRepository.getTopicByTopicId(topicId));
		if(topicResponse.isPresent())
			topic.setTopicId(topicId);
		return modelMapper.map(topicRepository.save(topic), TopicDto.class);
	}

	@Override
	public List<TopicDto> getTopicsListByBookChapter(Integer bookChapterId){
		return topicRepository.getTopicsListByBookChapterId(bookChapterId).stream().map(t->{
			return modelMapper.map(t, TopicDto.class);
		}).collect(Collectors.toList());
	}

	@Override
	public Map<String, Set<TopicDto>> loadTopic(XSSFSheet topicSheet, String topicSheetName){
		Map<String,Set<TopicDto>> topicMap = new HashMap<>();
		Set<TopicDto> topicSet = new HashSet<>();
		topicSheet.rowIterator().forEachRemaining(row->{
			if(row.getRowNum()>0){
               Topic topic = new Topic();
			   topic.setName(row.getCell(0).getStringCellValue());
			   topicRepository.save(topic);
			   TopicDto topicDto = new TopicDto();
			   topicDto.setTopicId(topic.getTopicId());
			   topicDto.setTopicName(topic.getName());
			   topicSet.add(topicDto);
			}
		});
		topicMap.put(topicSheetName,topicSet);
		return topicMap;
	}
}
