package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.BookChapterMap;
import com.edumoca.soma.entities.models.BookChapterMapResponse;
import com.edumoca.soma.entities.models.ChapterResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookChapterMapRepository extends PagingAndSortingRepository<BookChapterMap,Integer> {

    @Query("select new com.edumoca.soma.entities.models.BookChapterMapResponse(bcm.bookChapterMapId,bcm.book.bookId,bcm.chapter.chapterId) from BookChapterMap bcm where bcm.bookChapterMapId= :bookChapterMapId")
    public BookChapterMapResponse getBookChapterMapByBookChapterMapId(@Param("bookChapterMapId") Integer bookChapterMapId);
}
