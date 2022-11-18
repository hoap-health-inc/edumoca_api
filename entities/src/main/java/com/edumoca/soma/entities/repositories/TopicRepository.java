package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.models.TopicResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.edumoca.soma.entities.Topic;

import java.util.List;

public interface TopicRepository extends PagingAndSortingRepository<Topic, Integer>{

	@Query("select new com.edumoca.soma.entities.models.TopicResponse(t.topicId,t.name) from Topic t where t.chapter.name= :chapterId")
	public List<TopicResponse> getAllTopicByChapterId(Integer chapterId);

	@Query("select new com.edumoca.soma.entities.models.TopicResponse(t.topicId,t.name) from Topic t where t.chapter.chapterId= :chapterId and t.topicId= :topicId")
	public TopicResponse getTopicByChapterIdAndTopicId(Integer chapterId, Integer topicId);

	@Query("select new com.edumoca.soma.entities.models.TopicResponse(t.topicId,t.name) from Topic t where t.topicId= :topicId")
	public TopicResponse getTopicByTopicId(Integer topicId);


}
