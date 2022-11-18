package com.edumoca.soma.services.services;

import com.edumoca.soma.entities.dtos.ChapterDTO;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.edumoca.soma.entities.Chapter;

import java.util.List;

public interface ChapterService {
	ChapterDTO createChapter(Chapter chapter);
	ChapterDTO updateChapter(Chapter chapter,Integer chapterId);
	List<ChapterDTO> getAllChaptersByBook(Integer bookId);
	ChapterDTO getChapterByBookAndChapter(Integer bookId,Integer chapterId);

//	public void loadChapterData(XSSFSheet chapterSheet);
}
