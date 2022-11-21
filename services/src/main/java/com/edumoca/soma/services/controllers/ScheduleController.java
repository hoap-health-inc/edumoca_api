package com.edumoca.soma.services.controllers;

import com.edumoca.soma.entities.Schedule;
import com.edumoca.soma.entities.dtos.ScheduleDto;
import com.edumoca.soma.entities.dtos.StudentScheduleDto;
import com.edumoca.soma.entities.dtos.TeacherScheduleDto;
import com.edumoca.soma.entities.models.ScheduleRequest;
import com.edumoca.soma.services.services.ScheduleService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(value = "/schedule")
@AllArgsConstructor
public class ScheduleController {

	private final ScheduleService scheduleService;
	private final ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<ScheduleDto> createSchedule(@Valid @RequestBody ScheduleRequest scheduleRequest) {
		return new ResponseEntity<>(scheduleService.createSchedule(modelMapper.map(scheduleRequest,Schedule.class)), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{scheduleId}")
	public ResponseEntity<ScheduleDto> updateSchedule(@Valid @RequestBody ScheduleRequest scheduleRequest, @PathVariable("scheduleId") Integer scheduleId) {
		return new ResponseEntity<>(scheduleService.updateSchedule(modelMapper.map(scheduleRequest,Schedule.class),scheduleId),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{institutionId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ScheduleDto>> getAllSchedulesByInstitution(@PathVariable("institutionId") Integer institutionId){
		return new ResponseEntity<>(scheduleService.getAllSchedulesByInstitution(institutionId),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{institutionId}/{scheduleId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ScheduleDto> getScheduleByInstitutionAndSchedule(@PathVariable("institutionId") Integer institutionId, @PathVariable("scheduleId") Integer scheduleId) {
		return new ResponseEntity<>(scheduleService.getScheduleByInstitutionAndSchedule(institutionId,scheduleId),HttpStatus.OK);
	}

	@GetMapping(value = "/{userId}/studentSchedules",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StudentScheduleDto>> getSchedulesByStudentLoginId(@PathVariable("userId") Integer studentUserId){
		return new ResponseEntity<>(scheduleService.getScheduleByStudentLoginId(studentUserId,new Date()),HttpStatus.OK);
	}

	@GetMapping(value = "/{userId}/teacherSchedules",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TeacherScheduleDto>> getSchedulesByTeacherLoginId(@PathVariable("userId") Integer teacherUserId){
		return new ResponseEntity<>(scheduleService.getScheduleByTeacherLoginId(teacherUserId,new Date()),HttpStatus.OK);
	}
}
