package com.edumoca.soma.services.services;

import com.edumoca.soma.entities.Chapter;
import com.edumoca.soma.entities.dtos.BookDto;
import com.edumoca.soma.entities.dtos.ChapterDto;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ChapterService {
	ChapterDto createChapter(Chapter chapter);
	ChapterDto updateChapter(Chapter chapter, Integer chapterId);
	List<ChapterDto> getChapterListByBookId(Integer bookId);

	public Map<String, Set<ChapterDto>> loadChapter(XSSFSheet chaptersSheet, String chaptersSheetName);
}
