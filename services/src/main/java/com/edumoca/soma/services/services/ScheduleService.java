package com.edumoca.soma.services.services;

import com.edumoca.soma.entities.Schedule;
import com.edumoca.soma.entities.dtos.ScheduleDto;
import com.edumoca.soma.entities.dtos.StudentScheduleDto;
import com.edumoca.soma.entities.dtos.TeacherScheduleDto;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ScheduleService {
	ScheduleDto createSchedule(Schedule schedule);
	ScheduleDto updateSchedule(Schedule schedule, Integer scheduleId);
	List<ScheduleDto> getAllSchedulesByInstitution(Integer institutionId);
	ScheduleDto getScheduleByInstitutionAndSchedule(Integer institutionId, Integer scheduleId);

	List<StudentScheduleDto> getScheduleByStudentLoginId(Integer userId, Date date);

	List<TeacherScheduleDto> getScheduleByTeacherLoginId(Integer userId, Date date);

	Map<String, Set<ScheduleDto>> loadSchedules(XSSFSheet schedulesSheet, String schedulesSheetName);
}
