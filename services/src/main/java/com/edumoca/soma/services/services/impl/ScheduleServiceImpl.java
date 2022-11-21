package com.edumoca.soma.services.services.impl;

import com.edumoca.soma.entities.CourseSession;
import com.edumoca.soma.entities.GradeSectionInstitutionYearMap;
import com.edumoca.soma.entities.Schedule;
import com.edumoca.soma.entities.TeacherGradeSectionSubjectMap;
import com.edumoca.soma.entities.dtos.ScheduleDto;
import com.edumoca.soma.entities.dtos.StudentScheduleDto;
import com.edumoca.soma.entities.dtos.TeacherScheduleDto;
import com.edumoca.soma.entities.exception.DataNotFoundException;
import com.edumoca.soma.entities.models.ScheduleResponse;
import com.edumoca.soma.entities.repositories.CourseSessionRepository;
import com.edumoca.soma.entities.repositories.GradeSectionInstitutionYearMapRepository;
import com.edumoca.soma.entities.repositories.ScheduleRepository;
import com.edumoca.soma.entities.repositories.TeacherGradeSectionSubjectMapRepository;
import com.edumoca.soma.services.services.ScheduleService;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{

	private final ScheduleRepository scheduleRepository;
	private final GradeSectionInstitutionYearMapRepository gradeSectionInstitutionYearMappingRepository;
	private final TeacherGradeSectionSubjectMapRepository teacherGradeSectionSubjectMappingRepository;
	private final CourseSessionRepository courseSessionRepository;

	private final ModelMapper modelMapper;
	@Override
	public ScheduleDto createSchedule(Schedule schedule) {
		return modelMapper.map(scheduleRepository.save(schedule), ScheduleDto.class);
	}

	@Override
	public ScheduleDto updateSchedule(Schedule schedule, Integer scheduleId) {
		Optional<ScheduleResponse> scheduleResponse = Optional.ofNullable(scheduleRepository.getScheduleByScheduleId(scheduleId));
		if(scheduleResponse.isPresent())
			schedule.setScheduleId(scheduleId);
		return modelMapper.map(scheduleRepository.save(schedule), ScheduleDto.class);
	}

	@Override
	public List<ScheduleDto> getAllSchedulesByInstitution(Integer institutionId) {
		return scheduleRepository.getAllScheduleByInstitution(institutionId).stream().map(s->{
			return modelMapper.map(s, ScheduleDto.class);
		}).collect(Collectors.toList());
	}

	@Override
	public ScheduleDto getScheduleByInstitutionAndSchedule(Integer institutionId, Integer scheduleId) {
		Optional<ScheduleResponse> scheduleResponse = Optional.ofNullable(scheduleRepository.getScheduleByInstitutionIdAndScheduleId(institutionId,scheduleId));
		if(scheduleResponse.isPresent())
			return modelMapper.map(scheduleResponse.get(), ScheduleDto.class);
		else throw new DataNotFoundException("Schedule with id not found");
	}

	@Override
	public List<StudentScheduleDto> getScheduleByStudentLoginId(Integer userId, Date currentDate) {
		return scheduleRepository.getScheduleByStudentLoginId(userId,currentDate).stream().map(user->{
			List<DayOfWeek> dayOfWeekSet = scheduleRepository.getDayOfWeekByScheduleId(user.getScheduleId());
			StudentScheduleDto studentScheduleDTO = new StudentScheduleDto();
		    BeanUtils.copyProperties(user,studentScheduleDTO);
		    studentScheduleDTO.setDayOfWeekSet(dayOfWeekSet);
			return studentScheduleDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public List<TeacherScheduleDto> getScheduleByTeacherLoginId(Integer userId, Date currentDate) {
		return scheduleRepository.getScheduleByTeacherLoginId(userId,currentDate).stream().map(user->{
			List<DayOfWeek> dayOfWeekSet = scheduleRepository.getDayOfWeekByScheduleId(user.getScheduleId());
			TeacherScheduleDto teacherScheduleDTO = new TeacherScheduleDto();
			BeanUtils.copyProperties(user,teacherScheduleDTO);
			teacherScheduleDTO.setDayOfWeekSet(dayOfWeekSet);
			return teacherScheduleDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public Map<String,Set<ScheduleDto>> loadSchedules(XSSFSheet schedulesSheet, String schedulesSheetName) {
		Map<String,Set<ScheduleDto>> schedulesMap = new HashMap<>();
		Set<ScheduleDto> schedulesSet = new HashSet<>();
        schedulesSheet.rowIterator().forEachRemaining(row->{
			if(row.getRowNum()>0){
				Schedule schedule = new Schedule();
				schedule.setScheduleName(row.getCell(0).getStringCellValue());
				schedule.setStartDate(row.getCell(1).getDateCellValue());
				schedule.setEndDate(row.getCell(2).getDateCellValue());
				String[] dayOfWeeks = row.getCell(3).getStringCellValue().split(",");
				Set<DayOfWeek> dayWeekSet = new HashSet<>();
				for(String weekName : dayOfWeeks) {
					dayWeekSet.add(DayOfWeek.valueOf(weekName));
				}
				schedule.setDayOfWeeks(dayWeekSet);
				schedule.setRecurring(row.getCell(4).getBooleanCellValue());
				schedule.setFullDay(row.getCell(5).getBooleanCellValue());
				Optional<GradeSectionInstitutionYearMap> gradeSectionInstitutionYearMapping = gradeSectionInstitutionYearMappingRepository.findById(new Double(row.getCell(6).getNumericCellValue()).intValue());
				gradeSectionInstitutionYearMapping.ifPresent(schedule::setGradeSectionInstitutionYearMap);
				Optional<TeacherGradeSectionSubjectMap> teacherGradeSectionSubjectMapping = teacherGradeSectionSubjectMappingRepository.findById(new Double(row.getCell(7).getNumericCellValue()).intValue());
				teacherGradeSectionSubjectMapping.ifPresent(schedule::setTeacherGradeSectionSubjectMap);
				Optional<CourseSession> courseSession = courseSessionRepository.findById(new Double(row.getCell(8).getNumericCellValue()).intValue());
				courseSession.ifPresent(schedule::setSession);
				scheduleRepository.save(schedule);
				ScheduleDto scheduleDTO = new ScheduleDto();
				scheduleDTO.setScheduleId(schedule.getScheduleId());
				scheduleDTO.setScheduleName(schedule.getScheduleName());
				schedulesSet.add(scheduleDTO);
			}
		});
		schedulesMap.put(schedulesSheetName,schedulesSet);
		return schedulesMap;
	}
}
