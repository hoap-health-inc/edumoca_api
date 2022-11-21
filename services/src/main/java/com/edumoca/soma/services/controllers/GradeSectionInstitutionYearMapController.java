package com.edumoca.soma.services.controllers;

import com.edumoca.soma.entities.GradeSectionInstitutionYearMap;
import com.edumoca.soma.entities.dtos.GradeSectionInstitutionYearMapDto;
import com.edumoca.soma.entities.models.GradeSectionInstitutionYearMapRequest;
import com.edumoca.soma.services.services.GradeSectionInstitutionYearMappingService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/gradeSectionInstitutionYearMapping")
@AllArgsConstructor
public class GradeSectionInstitutionYearMapController {

	private final GradeSectionInstitutionYearMappingService gradeSectionInstitutionYearMappingService;
	private final ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<GradeSectionInstitutionYearMapDto> createGradeSectionInstitutionYearMapping(@Valid @RequestBody GradeSectionInstitutionYearMapRequest gradeSectionInstitutionYearMappingRequest) {
		return new ResponseEntity<>(gradeSectionInstitutionYearMappingService.createGradeSectionInstitutionYearMapping(modelMapper.map(gradeSectionInstitutionYearMappingRequest, GradeSectionInstitutionYearMap.class)), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{gradeSectionInstitutionYearMappingId}")
	public ResponseEntity<GradeSectionInstitutionYearMapDto> updateGradeSectionInstitutionYearMapping(@Valid @RequestBody GradeSectionInstitutionYearMapRequest gradeSectionInstitutionYearMappingRequest, @PathVariable("gradeSectionInstitutionYearMappingId") Integer gradeSectionInstitutionYearMappingId) {
		return new ResponseEntity<>(gradeSectionInstitutionYearMappingService.updateGradeSectionInstitutionYearMapping(modelMapper.map(gradeSectionInstitutionYearMappingRequest, GradeSectionInstitutionYearMap.class),gradeSectionInstitutionYearMappingId),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{institutionId}/all",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<GradeSectionInstitutionYearMapDto>> getAllGradeSectionInstitutionYearMappingByInstitution(@PathVariable("institutionId") Integer institutionId){
		return new ResponseEntity<>(gradeSectionInstitutionYearMappingService.getAllGradeSectionInstitutionYearMappingByInstitution(institutionId),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{institutionId}/{gradeSectionInstitutionYearMappingId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GradeSectionInstitutionYearMapDto> getGradeSectionInstitutionYearMappingByInstitutionAndGradeSectionInstitutionYearMapping(@PathVariable("institutionId") Integer institutionId, @PathVariable("gradeSectionInstitutionYearMappingId") Integer gradeSectionInstitutionYearMappingId) {
		return new ResponseEntity<>(gradeSectionInstitutionYearMappingService.getGradeSectionInstitutionYearMappingByInstitutionAndGradeSectionInstitutionYearMapping(institutionId,gradeSectionInstitutionYearMappingId),HttpStatus.OK);
	}
	
//	@GetMapping(value = "/{id}/allStudents",produces = MediaType.APPLICATION_JSON_VALUE)
//	public List<Student> getAllStudentByGradeSectionMappingId(@PathVariable("id") Integer gradeSectionYearMapId) {
//		//return gradeSectionYearMappingService.getGradeSectionYearInstitutionById(gradeSectionYearMapId);
//		return StreamSupport.stream(gradeSectionInstitutionYearMappingService.getGradeSectionYearInstitutionById(gradeSectionYearMapId).getStudents().spliterator(), false).collect(Collectors.toList());
//	}
	
}
