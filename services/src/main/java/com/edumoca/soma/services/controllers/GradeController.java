package com.edumoca.soma.services.controllers;

import com.edumoca.soma.entities.Grade;
import com.edumoca.soma.entities.dtos.GradeDto;
import com.edumoca.soma.entities.models.GradeRequest;
import com.edumoca.soma.services.services.GradeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/grade")
@AllArgsConstructor
public class GradeController {
	
	private final GradeService gradeService;
	private final ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<GradeDto> createGrade(@Valid @RequestBody GradeRequest gradeRequest){
		 return new ResponseEntity<>(gradeService.createGrade(modelMapper.map(gradeRequest,Grade.class)), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{gradeId}")
	public ResponseEntity<GradeDto> updateGrade(@Valid @RequestBody GradeRequest gradeRequest, @PathVariable("gradeId") Integer gradeId) {
		return new ResponseEntity<>(gradeService.updateGrade(modelMapper.map(gradeRequest,Grade.class),gradeId),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{institutionId}/all",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<GradeDto>> getAllGradesByInstitution(@PathVariable("institutionId") Integer institutionId){
		return new ResponseEntity<>(gradeService.getAllGradesByInstitution(institutionId),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{institutionId}/{gradeId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GradeDto> getGradeByInstitutionAndGrade(@PathVariable("institutionId") Integer institutionId, @PathVariable("gradeId") Integer gradeId) {
		return new ResponseEntity<>(gradeService.getGradeByInstitutionAndGrade(institutionId,gradeId),HttpStatus.OK);
	}
}
