package com.edumoca.soma.services.controllers;

import java.util.Date;
import java.util.List;

import com.edumoca.soma.entities.dtos.ScheduleDTO;
import com.edumoca.soma.entities.models.ScheduleRequest;
import com.edumoca.soma.entities.models.StudentScheduleDTO;
import com.edumoca.soma.entities.models.TeacherScheduleDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edumoca.soma.entities.Schedule;
import com.edumoca.soma.services.services.ScheduleService;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/schedule")
@AllArgsConstructor
public class ScheduleController {

	private final ScheduleService scheduleService;
	private final ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<ScheduleDTO> createSchedule(@Valid @RequestBody ScheduleRequest scheduleRequest) {
		return new ResponseEntity<>(scheduleService.createSchedule(modelMapper.map(scheduleRequest,Schedule.class)), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{scheduleId}")
	public ResponseEntity<ScheduleDTO> updateSchedule(@Valid @RequestBody ScheduleRequest scheduleRequest,@PathVariable("scheduleId") Integer scheduleId) {
		return new ResponseEntity<>(scheduleService.updateSchedule(modelMapper.map(scheduleRequest,Schedule.class),scheduleId),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{institutionId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ScheduleDTO>> getAllSchedulesByInstitution(@PathVariable("institutionId") Integer institutionId){
		return new ResponseEntity<>(scheduleService.getAllSchedulesByInstitution(institutionId),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{institutionId}/{scheduleId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ScheduleDTO> getScheduleByInstitutionAndSchedule(@PathVariable("institutionId") Integer institutionId,@PathVariable("scheduleId") Integer scheduleId) {
		return new ResponseEntity<>(scheduleService.getScheduleByInstitutionAndSchedule(institutionId,scheduleId),HttpStatus.OK);
	}

	@GetMapping(value = "/{userId}/studentSchedules",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StudentScheduleDTO>> getSchedulesByStudentLoginId(@PathVariable("userId") Integer studentUserId){
		return new ResponseEntity<>(scheduleService.getScheduleByStudentLoginId(studentUserId,new Date()),HttpStatus.OK);
	}

	@GetMapping(value = "/{userId}/teacherSchedules",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TeacherScheduleDTO>> getSchedulesByTeacherLoginId(@PathVariable("userId") Integer teacherUserId){
		return new ResponseEntity<>(scheduleService.getScheduleByTeacherLoginId(teacherUserId,new Date()),HttpStatus.OK);
	}
}
