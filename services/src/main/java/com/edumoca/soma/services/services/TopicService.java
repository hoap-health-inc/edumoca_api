package com.edumoca.soma.services.services;

import com.edumoca.soma.entities.dtos.TopicDTO;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.edumoca.soma.entities.Topic;

import java.util.List;

public interface TopicService {
	TopicDTO createTopic(Topic topic);
	TopicDTO updateTopic(Topic topic,Integer topicId);
	List<TopicDTO> getAllTopicsByChapter(Integer chapterId);
	TopicDTO getTopicByChapterAndTopic(Integer chapterId,Integer topicId);

//	public void loadTopicData(XSSFSheet topicSheet);
}
