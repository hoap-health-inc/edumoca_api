package com.edumoca.soma.services.controllers;

import com.edumoca.soma.entities.GradeSectionInstitutionMap;
import com.edumoca.soma.entities.dtos.GradeSectionInstitutionMapDto;
import com.edumoca.soma.entities.models.GradeSectionInstitutionMapRequest;
import com.edumoca.soma.services.services.GradeSectionInstitutionMappingService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/gradeSectionInstitutionMapping")
@AllArgsConstructor
public class GradeSectionInstitutionMapController {

	private final GradeSectionInstitutionMappingService gradeSectionInstitutionMappingService;
	private final ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<GradeSectionInstitutionMapDto> createGradeSectionInstitutionMapping(@Valid @RequestBody GradeSectionInstitutionMapRequest gradeSectionInstitutionMappingRequest) {
		return new ResponseEntity<>(gradeSectionInstitutionMappingService.createGradeSectionInstitutionMapping(modelMapper.map(gradeSectionInstitutionMappingRequest, GradeSectionInstitutionMap.class)),HttpStatus.CREATED);
	}

	@PutMapping(value = "/{gradeSectionInstitutionMappingId}")
	public ResponseEntity<GradeSectionInstitutionMapDto> updateGradeSectionInstitutionMapping(@Valid @RequestBody GradeSectionInstitutionMapRequest gradeSectionInstitutionMappingRequest, @PathVariable("gradeSectionInstitutionMappingId") Integer gradeSectionInstitutionMappingId){
		return new ResponseEntity<>(gradeSectionInstitutionMappingService.updateGradeSectionInstitutionMapping(modelMapper.map(gradeSectionInstitutionMappingRequest, GradeSectionInstitutionMap.class),gradeSectionInstitutionMappingId),HttpStatus.OK);
	}

	@GetMapping(value = "/{institutionId}/all",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<GradeSectionInstitutionMapDto>> getAllGradeSectionInstitutionMappingByInstitution(@PathVariable("institutionId") Integer institutionId){
        return new ResponseEntity<>(gradeSectionInstitutionMappingService.getAllGradeSectionInstitutionMappingByInstitution(institutionId),HttpStatus.OK);
	}

	@GetMapping(value = "/{institutionId}/{gradeSectionInstitutionMappingId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GradeSectionInstitutionMapDto> getGradeSectionInstitutionMappingByInstitutionAndGradeSectionInstitutionMappingId(@PathVariable("institutionId") Integer institutionId, @PathVariable("gradeSectionInstitutionMappingId") Integer gradeSectionInstitutionMappingId){
		return new ResponseEntity<>(gradeSectionInstitutionMappingService.getGradeSectionInstitutionMappingByInstitutionAndGradeSectionInstitutionMappingId(institutionId,gradeSectionInstitutionMappingId),HttpStatus.OK);
	}
}
