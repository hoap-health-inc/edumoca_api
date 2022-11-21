package com.edumoca.soma.services.services.impl;

import com.edumoca.soma.entities.Book;
import com.edumoca.soma.entities.Chapter;
import com.edumoca.soma.entities.GradeSectionInstitutionMap;
import com.edumoca.soma.entities.Subject;
import com.edumoca.soma.entities.dtos.BookDto;
import com.edumoca.soma.entities.dtos.ChapterDto;
import com.edumoca.soma.entities.exception.DataNotFoundException;
import com.edumoca.soma.entities.models.ChapterResponse;
import com.edumoca.soma.entities.repositories.ChapterRepository;
import com.edumoca.soma.entities.repositories.TopicRepository;
import com.edumoca.soma.services.services.ChapterService;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChapterServiceImpl implements ChapterService{

	private final ChapterRepository chapterRepository;
	private final TopicRepository topicRepository;
 	private final ModelMapper modelMapper;
	@Override
	public ChapterDto createChapter(Chapter chapter) {
		return modelMapper.map(chapterRepository.save(chapter), ChapterDto.class);
	}

	@Override
	public ChapterDto updateChapter(Chapter chapter, Integer chapterId) {
		Optional<ChapterResponse> chapterResponse = Optional.ofNullable(chapterRepository.getChapterByChapterId(chapterId));
		if(chapterResponse.isPresent())
			chapter.setChapterId(chapterId);
		return modelMapper.map(chapterRepository.save(chapter), ChapterDto.class);
	}

	@Override
	public List<ChapterDto> getChapterListByBookId(Integer chapterId){
		return chapterRepository.getChapterListByBookId(chapterId).stream().map(ch->{
			return modelMapper.map(ch, ChapterDto.class);
		}).collect(Collectors.toList());
	}

	@Override
	public Map<String, Set<ChapterDto>> loadChapter(XSSFSheet chaptersSheet, String chaptersSheetName) {
		Map<String,Set<ChapterDto>> chapterMap = new HashMap<>();
		Set<ChapterDto> chapterSet = new HashSet<>();
		chaptersSheet.rowIterator().forEachRemaining(row->{
			if(row.getRowNum()>0){
					Chapter chapter = new Chapter();
					chapter.setName(row.getCell(0).getStringCellValue());
					chapterRepository.save(chapter);
					ChapterDto chapterDto = new ChapterDto();
					chapterDto.setChapterId(chapter.getChapterId());
					chapterDto.setChapterName(chapter.getName());
					chapterSet.add(chapterDto);
			}
		});
		chapterMap.put(chaptersSheetName,chapterSet);
		return chapterMap;
	}

}
