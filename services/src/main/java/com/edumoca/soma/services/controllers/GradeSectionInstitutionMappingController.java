package com.edumoca.soma.services.controllers;

import com.edumoca.soma.entities.dtos.GradeSectionInstitutionMappingDTO;
import com.edumoca.soma.entities.models.GradeSectionInstitutionMappingRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edumoca.soma.entities.GradeSectionInstitutionMapping;
import com.edumoca.soma.services.services.GradeSectionInstitutionMappingService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/gradeSectionInstitutionMapping")
@AllArgsConstructor
public class GradeSectionInstitutionMappingController {

	private final GradeSectionInstitutionMappingService gradeSectionInstitutionMappingService;
	private final ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<GradeSectionInstitutionMappingDTO> createGradeSectionInstitutionMapping(@Valid @RequestBody GradeSectionInstitutionMappingRequest gradeSectionInstitutionMappingRequest) {
		return new ResponseEntity<>(gradeSectionInstitutionMappingService.createGradeSectionInstitutionMapping(modelMapper.map(gradeSectionInstitutionMappingRequest,GradeSectionInstitutionMapping.class)),HttpStatus.CREATED);
	}

	@PutMapping(value = "/{gradeSectionInstitutionMappingId}")
	public ResponseEntity<GradeSectionInstitutionMappingDTO> updateGradeSectionInstitutionMapping(@Valid @RequestBody GradeSectionInstitutionMappingRequest gradeSectionInstitutionMappingRequest,@PathVariable("gradeSectionInstitutionMappingId") Integer gradeSectionInstitutionMappingId){
		return new ResponseEntity<>(gradeSectionInstitutionMappingService.updateGradeSectionInstitutionMapping(modelMapper.map(gradeSectionInstitutionMappingRequest,GradeSectionInstitutionMapping.class),gradeSectionInstitutionMappingId),HttpStatus.OK);
	}

	@GetMapping(value = "/{institutionId}/all",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<GradeSectionInstitutionMappingDTO>> getAllGradeSectionInstitutionMappingByInstitution(@PathVariable("institutionId") Integer institutionId){
        return new ResponseEntity<>(gradeSectionInstitutionMappingService.getAllGradeSectionInstitutionMappingByInstitution(institutionId),HttpStatus.OK);
	}

	@GetMapping(value = "/{institutionId}/{gradeSectionInstitutionMappingId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GradeSectionInstitutionMappingDTO> getGradeSectionInstitutionMappingByInstitutionAndGradeSectionInstitutionMappingId(@PathVariable("institutionId") Integer institutionId,@PathVariable("gradeSectionInstitutionMappingId") Integer gradeSectionInstitutionMappingId){
		return new ResponseEntity<>(gradeSectionInstitutionMappingService.getGradeSectionInstitutionMappingByInstitutionAndGradeSectionInstitutionMappingId(institutionId,gradeSectionInstitutionMappingId),HttpStatus.OK);
	}
}
