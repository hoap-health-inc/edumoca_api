package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.Topic;
import com.edumoca.soma.entities.models.TopicResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicRepository extends PagingAndSortingRepository<Topic, Integer>{

//	@Query("select new com.edumoca.soma.entities.models.TopicResponse(t.topicId,t.name) from Topic t where t.chapter.name= :chapterId")
//	public List<TopicResponse> getAllTopicByChapterId(Integer chapterId);
//
//	@Query("select new com.edumoca.soma.entities.models.TopicResponse(t.topicId,t.name) from Topic t where t.chapter.chapterId= :chapterId and t.topicId= :topicId")
//	public TopicResponse getTopicByChapterIdAndTopicId(Integer chapterId, Integer topicId);

	@Query("select new com.edumoca.soma.entities.models.TopicResponse(t.topicId,t.name) from Topic t where t.topicId= :topicId")
	public TopicResponse getTopicByTopicId(@Param("topicId") Integer topicId);

	@Query("select new com.edumoca.soma.entities.models.TopicResponse(bctm.topic.topicId,bctm.topic.name) from BookChapterTopicMap bctm where bctm.bookChapterMapping.bookChapterMapId= :bookChapterId")
    public List<TopicResponse> getTopicsListByBookChapterId(@Param("bookChapterId") Integer bookChapterId);
}
