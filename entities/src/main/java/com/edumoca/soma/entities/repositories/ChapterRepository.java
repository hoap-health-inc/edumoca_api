package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.models.ChapterResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.edumoca.soma.entities.Chapter;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChapterRepository extends PagingAndSortingRepository<Chapter, Integer>{

	@Query("select new com.edumoca.soma.entities.models.ChapterResponse(ch.chapterId,ch.name) from Chapter ch where ch.book.bookId= :bookId")
	public List<ChapterResponse> getAllChaptersByBookId(@Param("bookId") Integer bookId);

	@Query("select new com.edumoca.soma.entities.models.ChapterResponse(ch.chapterId,ch.name) from Chapter ch where ch.book.bookId= :bookId and ch.chapterId= :chapterId")
	public ChapterResponse getChapterByBookIdAndChapterId(@Param("bookId") Integer bookId, @Param("chapterId") Integer chapterId);

	@Query("select new com.edumoca.soma.entities.models.ChapterResponse(ch.chapterId,ch.name) from Chapter ch where ch.chapterId= :chapterId")
	public ChapterResponse getChapterByChapterId(@Param("chapterId") Integer chapterId);
}
