package com.edumoca.soma.services.services;

import com.edumoca.soma.entities.Topic;
import com.edumoca.soma.entities.dtos.TopicDto;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TopicService {
	TopicDto createTopic(Topic topic);
	TopicDto updateTopic(Topic topic, Integer topicId);
	List<TopicDto> getTopicsListByBookChapter(Integer bookChapterId);

	public Map<String, Set<TopicDto>> loadTopic(XSSFSheet topicSheet, String topicSheetName) throws IOException;
}
