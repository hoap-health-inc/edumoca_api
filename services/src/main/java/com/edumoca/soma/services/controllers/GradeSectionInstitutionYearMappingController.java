package com.edumoca.soma.services.controllers;

import java.util.List;
import java.util.Set;

import com.edumoca.soma.entities.dtos.GradeSectionInstitutionYearMappingDTO;
import com.edumoca.soma.entities.models.GradeSectionInstitutionYearMappingRequest;
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

import com.edumoca.soma.entities.GradeSectionInstitutionYearMapping;
import com.edumoca.soma.services.services.GradeSectionInstitutionYearMappingService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/gradeSectionInstitutionYearMapping")
@AllArgsConstructor
public class GradeSectionInstitutionYearMappingController {

	private final GradeSectionInstitutionYearMappingService gradeSectionInstitutionYearMappingService;
	private final ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<GradeSectionInstitutionYearMappingDTO> createGradeSectionInstitutionYearMapping(@Valid @RequestBody GradeSectionInstitutionYearMappingRequest gradeSectionInstitutionYearMappingRequest) {
		return new ResponseEntity<>(gradeSectionInstitutionYearMappingService.createGradeSectionInstitutionYearMapping(modelMapper.map(gradeSectionInstitutionYearMappingRequest,GradeSectionInstitutionYearMapping.class)), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{gradeSectionInstitutionYearMappingId}")
	public ResponseEntity<GradeSectionInstitutionYearMappingDTO> updateGradeSectionInstitutionYearMapping(@Valid @RequestBody GradeSectionInstitutionYearMappingRequest gradeSectionInstitutionYearMappingRequest,@PathVariable("gradeSectionInstitutionYearMappingId") Integer gradeSectionInstitutionYearMappingId) {
		return new ResponseEntity<>(gradeSectionInstitutionYearMappingService.updateGradeSectionInstitutionYearMapping(modelMapper.map(gradeSectionInstitutionYearMappingRequest,GradeSectionInstitutionYearMapping.class),gradeSectionInstitutionYearMappingId),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{institutionId}/all",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<GradeSectionInstitutionYearMappingDTO>> getAllGradeSectionInstitutionYearMappingByInstitution(@PathVariable("institutionId") Integer institutionId){
		return new ResponseEntity<>(gradeSectionInstitutionYearMappingService.getAllGradeSectionInstitutionYearMappingByInstitution(institutionId),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{institutionId}/{gradeSectionInstitutionYearMappingId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GradeSectionInstitutionYearMappingDTO> getGradeSectionInstitutionYearMappingByInstitutionAndGradeSectionInstitutionYearMapping(@PathVariable("institutionId") Integer institutionId,@PathVariable("gradeSectionInstitutionYearMappingId") Integer gradeSectionInstitutionYearMappingId) {
		return new ResponseEntity<>(gradeSectionInstitutionYearMappingService.getGradeSectionInstitutionYearMappingByInstitutionAndGradeSectionInstitutionYearMapping(institutionId,gradeSectionInstitutionYearMappingId),HttpStatus.OK);
	}
	
//	@GetMapping(value = "/{id}/allStudents",produces = MediaType.APPLICATION_JSON_VALUE)
//	public List<Student> getAllStudentByGradeSectionMappingId(@PathVariable("id") Integer gradeSectionYearMapId) {
//		//return gradeSectionYearMappingService.getGradeSectionYearInstitutionById(gradeSectionYearMapId);
//		return StreamSupport.stream(gradeSectionInstitutionYearMappingService.getGradeSectionYearInstitutionById(gradeSectionYearMapId).getStudents().spliterator(), false).collect(Collectors.toList());
//	}
	
}
