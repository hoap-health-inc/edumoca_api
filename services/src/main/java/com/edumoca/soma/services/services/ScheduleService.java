package com.edumoca.soma.services.services;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.edumoca.soma.entities.dtos.ScheduleDTO;
import com.edumoca.soma.entities.models.StudentScheduleDTO;
import com.edumoca.soma.entities.models.TeacherScheduleDTO;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.edumoca.soma.entities.Schedule;

public interface ScheduleService {
	ScheduleDTO createSchedule(Schedule schedule);
	ScheduleDTO updateSchedule(Schedule schedule,Integer scheduleId);
	List<ScheduleDTO> getAllSchedulesByInstitution(Integer institutionId);
	ScheduleDTO getScheduleByInstitutionAndSchedule(Integer institutionId,Integer scheduleId);

	List<StudentScheduleDTO> getScheduleByStudentLoginId(Integer userId, Date date);

	List<TeacherScheduleDTO> getScheduleByTeacherLoginId(Integer userId, Date date);

	Map<String, Set<ScheduleDTO>> loadSchedules(XSSFSheet schedulesSheet, String schedulesSheetName);
}
