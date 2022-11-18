package com.edumoca.soma.services.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.edumoca.soma.entities.models.TopicResponse;
import com.edumoca.soma.entities.dtos.TopicDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edumoca.soma.entities.Topic;
import com.edumoca.soma.entities.exception.DataNotFoundException;
import com.edumoca.soma.entities.repositories.TopicRepository;
import com.edumoca.soma.services.services.TopicService;

@Service
@AllArgsConstructor
public class TopicServiceImpl implements TopicService{

	private final TopicRepository topicRepository;
 	private final ModelMapper modelMapper;
	@Override
	public TopicDTO createTopic(Topic topic) {
		return modelMapper.map(topicRepository.save(topic),TopicDTO.class);
	}

	@Override
	public TopicDTO updateTopic(Topic topic,Integer topicId) {
		Optional<TopicResponse> topicResponse = Optional.ofNullable(topicRepository.getTopicByTopicId(topicId));
		if(topicResponse.isPresent())
			topic.setTopicId(topicId);
		return modelMapper.map(topicRepository.save(topic),TopicDTO.class);
	}

	@Override
	public List<TopicDTO> getAllTopicsByChapter(Integer chapterId){
		return topicRepository.getAllTopicByChapterId(chapterId).stream().map(t->{
			return modelMapper.map(t,TopicDTO.class);
		}).collect(Collectors.toList());
	}

	@Override
	public TopicDTO getTopicByChapterAndTopic(Integer chapterId,Integer topicId) {
        Optional<TopicResponse> topicResponse = Optional.ofNullable(topicRepository.getTopicByChapterIdAndTopicId(chapterId, topicId));
        if(topicResponse.isPresent()) {
			return modelMapper.map(topicResponse.get(),TopicDTO.class);
		}else
        	throw new DataNotFoundException("Topic with id not found.");
	}


//	@Override
//	public void loadTopicData(XSSFSheet topicSheet) {
//		topicSheet.rowIterator().forEachRemaining(row->{
//			if(row.getRowNum()!=0) {
//				Topic topic = new Topic();
//				topic.setName(row.getCell(0).getStringCellValue());
//				topicRepository.save(topic);
//			}
//		});
//	}
	
	

	
}
