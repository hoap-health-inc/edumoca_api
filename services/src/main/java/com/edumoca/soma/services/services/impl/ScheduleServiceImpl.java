package com.edumoca.soma.services.services.impl;

import com.edumoca.soma.entities.*;
import com.edumoca.soma.entities.dtos.ScheduleDto;
import com.edumoca.soma.entities.dtos.StudentScheduleDto;
import com.edumoca.soma.entities.dtos.TeacherScheduleDto;
import com.edumoca.soma.entities.exception.DataNotFoundException;
import com.edumoca.soma.entities.models.ScheduleResponse;
import com.edumoca.soma.entities.models.ScheduleStatusResponse;
import com.edumoca.soma.entities.repositories.*;
import com.edumoca.soma.services.services.ScheduleService;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{

	private final ScheduleRepository scheduleRepository;
	private final GradeSectionInstitutionYearMapRepository gradeSectionInstitutionYearMappingRepository;
	private final TeacherGradeSectionSubjectMapRepository teacherGradeSectionSubjectMappingRepository;
	private final CourseSessionRepository courseSessionRepository;
	private final ScheduleStatusRepository scheduleStatusRepository;

	private final ModelMapper modelMapper;
	@Override
	public ScheduleDto createSchedule(Schedule schedule) {
		Optional<CourseSession> session = courseSessionRepository.findById(schedule.getSession().getSessionId());
		if(session.isPresent()){
			List<ScheduleResponse> schedules = scheduleRepository.getSchedulesByStartDateAndEndDateBetween(schedule.getStartDate(),schedule.getEndDate(),session.get().getStartTime(), session.get().getEndTime());
			if(schedules.size()>0){
				for(ScheduleResponse schedule1 : schedules){
					ScheduleStatus scheduleStatus = new ScheduleStatus();
					scheduleStatus.setStatus("Cancelled");
					scheduleStatus.setStartDate(schedule.getStartDate());
					scheduleStatus.setEndDate(schedule.getEndDate());
					scheduleStatus.setSchedule(modelMapper.map(schedule1,Schedule.class));
					scheduleStatusRepository.save(scheduleStatus);
				}
			}
		}
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
			Set<ScheduleStatusResponse> scheduleStatusSet = scheduleStatusRepository.getScheduleStatusByScheduleId(currentDate,user.getScheduleId());
			StudentScheduleDto studentScheduleDTO = new StudentScheduleDto();
		    BeanUtils.copyProperties(user,studentScheduleDTO);
		    studentScheduleDTO.setDayOfWeekSet(dayOfWeekSet);
            studentScheduleDTO.setScheduleStatusSet(scheduleStatusSet);
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
				LocalDate startDt = row.getCell(1).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate endDt = row.getCell(2).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
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
				//for build Duration object
				CourseSession cs = courseSession.get();
				LocalTime startTime = cs.getStartTime();
				LocalTime endTime = cs.getEndTime();
				LocalDateTime startDateTime = LocalDateTime.of(startDt,startTime);
				LocalDateTime endDateTime = LocalDateTime.of(endDt,endTime);
				schedule.setScheduleDuration(Duration.between(startDateTime,endDateTime));
				CourseSession courseSession2 = schedule.getSession();
				List<ScheduleResponse> schedules = scheduleRepository.getSchedulesByStartDateAndEndDateBetween(row.getCell(1).getDateCellValue(),row.getCell(2).getDateCellValue(),courseSession2.getStartTime(),courseSession2.getEndTime());
				scheduleRepository.save(schedule);
				if(schedules.size()>0){
					for(ScheduleResponse schedule1 : schedules){
						ScheduleStatus scheduleStatus = new ScheduleStatus();
						scheduleStatus.setStatus("Cancelled");
						scheduleStatus.setStartDate(schedule.getStartDate());
						scheduleStatus.setEndDate(schedule.getEndDate());
						scheduleStatus.setSchedule(modelMapper.map(schedule1,Schedule.class));
						scheduleStatusRepository.save(scheduleStatus);
						System.out.println(schedule1.getScheduleName()+"--"+schedule1.getSessionId()+"---");
					}
				}
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
