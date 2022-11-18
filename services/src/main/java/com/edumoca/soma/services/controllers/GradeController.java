package com.edumoca.soma.services.controllers;

import java.util.List;

import com.edumoca.soma.entities.dtos.GradeDTO;
import com.edumoca.soma.entities.models.GradeRequest;
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

import com.edumoca.soma.entities.Grade;
import com.edumoca.soma.services.services.GradeService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/grade")
@AllArgsConstructor
public class GradeController {
	
	private final GradeService gradeService;
	private final ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<GradeDTO> createGrade(@Valid @RequestBody GradeRequest gradeRequest){
		 return new ResponseEntity<>(gradeService.createGrade(modelMapper.map(gradeRequest,Grade.class)), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{gradeId}")
	public ResponseEntity<GradeDTO> updateGrade(@Valid @RequestBody GradeRequest gradeRequest,@PathVariable("gradeId") Integer gradeId) {
		return new ResponseEntity<>(gradeService.updateGrade(modelMapper.map(gradeRequest,Grade.class),gradeId),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{institutionId}/all",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<GradeDTO>> getAllGradesByInstitution(@PathVariable("institutionId") Integer institutionId){
		return new ResponseEntity<>(gradeService.getAllGradesByInstitution(institutionId),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{institutionId}/{gradeId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GradeDTO> getGradeByInstitutionAndGrade(@PathVariable("institutionId") Integer institutionId, @PathVariable("gradeId") Integer gradeId) {
		return new ResponseEntity<>(gradeService.getGradeByInstitutionAndGrade(institutionId,gradeId),HttpStatus.OK);
	}
}
