package com.edumoca.soma.services.services.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.edumoca.soma.entities.models.ChapterResponse;
import com.edumoca.soma.entities.dtos.ChapterDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.edumoca.soma.entities.Chapter;
import com.edumoca.soma.entities.exception.DataNotFoundException;
import com.edumoca.soma.entities.repositories.ChapterRepository;
import com.edumoca.soma.entities.repositories.TopicRepository;
import com.edumoca.soma.services.services.ChapterService;

@Service
@AllArgsConstructor
public class ChapterServiceImpl implements ChapterService{

	private final ChapterRepository chapterRepository;
	private final TopicRepository topicRepository;
 	private final ModelMapper modelMapper;
	@Override
	public ChapterDTO createChapter(Chapter chapter) {
		return modelMapper.map(chapterRepository.save(chapter),ChapterDTO.class);
	}

	@Override
	public ChapterDTO updateChapter(Chapter chapter,Integer chapterId) {
		Optional<ChapterResponse> chapterResponse = Optional.ofNullable(chapterRepository.getChapterByChapterId(chapterId));
		if(chapterResponse.isPresent())
			chapter.setChapterId(chapterId);
		return modelMapper.map(chapterRepository.save(chapter),ChapterDTO.class);
	}

	@Override
	public List<ChapterDTO> getAllChaptersByBook(Integer chapterId){
		return chapterRepository.getAllChaptersByBookId(chapterId).stream().map(ch->{
			return modelMapper.map(ch,ChapterDTO.class);
		}).collect(Collectors.toList());
	}

	@Override
	public ChapterDTO getChapterByBookAndChapter(Integer bookId,Integer chapterId) {
		Optional<ChapterResponse> chapterResponse = Optional.ofNullable(chapterRepository.getChapterByBookIdAndChapterId(bookId, chapterId));
		if(chapterResponse.isPresent()) {
			return modelMapper.map(chapterResponse.get(),ChapterDTO.class);
		}else
			throw new DataNotFoundException("Chapter with id not found.");
	}


//	@Override
//	public void loadChapterData(XSSFSheet chapterSheet) {
//		chapterSheet.rowIterator().forEachRemaining(row -> {
//            if (row.getRowNum() != 0) {
//               Chapter chapter = new Chapter();
//               chapter.setName(row.getCell(0).getStringCellValue());
//               String topics[] = {};
//               if(row.getCell(1).getStringCellValue().contains("@@")) {
//                  topics = row.getCell(1).getStringCellValue().split("@@");
//               }else {
//            	   topics = new String[] {row.getCell(1).getStringCellValue()};
//               }
//               Set<Topic> topicsSet = new HashSet<>();
//               Arrays.stream(topics).forEach(topic->{
//            	  Topic topicObj = topicRepository.findByNameIgnoreCase(topic.trim());
//            	  topicsSet.add(topicObj);
//               });
//               chapter.setTopic(topicsSet);
//               chapterRepository.save(chapter);
//            }
//        });
//	}
}
