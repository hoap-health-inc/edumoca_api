package com.edumoca.soma.services.services.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.edumoca.soma.entities.Institution;
import com.edumoca.soma.entities.Period;
import com.edumoca.soma.entities.dtos.CourseSessionDTO;
import com.edumoca.soma.entities.models.CourseSessionResponse;
import com.edumoca.soma.entities.repositories.InstitutionRepository;
import com.edumoca.soma.entities.repositories.PeriodRepository;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.edumoca.soma.entities.CourseSession;
import com.edumoca.soma.entities.exception.DataNotFoundException;
import com.edumoca.soma.entities.repositories.CourseSessionRepository;
import com.edumoca.soma.services.services.CourseSessionService;

@Service
@AllArgsConstructor
public class CourseSessionServiceImpl implements CourseSessionService{

	private final CourseSessionRepository courseSessionRepository;
	private final PeriodRepository periodRepository;
	private final InstitutionRepository institutionRepository;

	private final ModelMapper modelMapper;

	@Override
	public CourseSessionDTO createCourseSession(CourseSession courseSession) {
		return modelMapper.map(courseSessionRepository.save(courseSession),CourseSessionDTO.class);
	}

	@Override
	public CourseSessionDTO updateCourseSession(CourseSession courseSession,Integer courseSessionId) {
		Optional<CourseSessionResponse> courseSessionResponse = Optional.ofNullable(courseSessionRepository.getCourseSessionBySessionId(courseSessionId));
		if(courseSessionResponse.isPresent())
			courseSession.setSessionId(courseSessionId);
		return modelMapper.map(courseSessionRepository.save(courseSession),CourseSessionDTO.class);
	}

	@Override
	public List<CourseSessionDTO> getAllCourseSessionByInstitution(Integer institutionId){
		return courseSessionRepository.getAllCourseSessionByInstitutionId(institutionId).stream().map(cs->{
			return modelMapper.map(cs,CourseSessionDTO.class);
		}).collect(Collectors.toList());
	}

	@Override
	public CourseSessionDTO getCourseSessionByInstitutionAndSession(Integer institutionId,Integer courseSessionId) {
		Optional<CourseSessionResponse> courseSessionResponse = Optional.ofNullable(courseSessionRepository.getCourseSessionByInstitutionIdAndSessionId(institutionId,courseSessionId));
		if(courseSessionResponse.isPresent())
			return modelMapper.map(courseSessionResponse.get(),CourseSessionDTO.class);
		else throw new DataNotFoundException("CourseSession with id not found");
	}

	@Override
	public Map<String, Set<CourseSessionDTO>> loadCourseSessions(XSSFSheet courseSessionSheet, String courseSessionSheetName) {
		Map<String, Set<CourseSessionDTO>> courseSessionMap = new HashMap<>();
		Set<CourseSessionDTO> courseSessionSet = new HashSet<>();
		courseSessionSheet.rowIterator().forEachRemaining(row->{
			if(row.getRowNum()>0){
				CourseSession courseSession = new CourseSession();
				courseSession.setStartTime(row.getCell(0).getLocalDateTimeCellValue().toLocalTime());
				courseSession.setEndTime(row.getCell(1).getLocalDateTimeCellValue().toLocalTime());
				Optional<Period> period = periodRepository.findById(new Double(row.getCell(2).getNumericCellValue()).intValue());
				period.ifPresent(courseSession::setPeriod);
				Optional<Institution> institution = institutionRepository.findById(new Double(row.getCell(3).getNumericCellValue()).intValue());
				institution.ifPresent(courseSession::setInstitution);
				courseSessionRepository.save(courseSession);
				CourseSessionDTO courseSessionDTO = new CourseSessionDTO();
				courseSessionDTO.setCourseSessionId(courseSession.getSessionId());
				courseSessionSet.add(courseSessionDTO);
			}

		});
		courseSessionMap.put(courseSessionSheetName,courseSessionSet);
		return courseSessionMap;
	}
}
