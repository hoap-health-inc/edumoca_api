package com.edumoca.soma.services.controllers;
import java.util.List;

import com.edumoca.soma.entities.dtos.InstitutionDTO;
import com.edumoca.soma.entities.models.InstitutionRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.edumoca.soma.entities.Institution;
import com.edumoca.soma.services.services.InstitutionService;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/institution")
@AllArgsConstructor
public class InstitutionController {

	private final InstitutionService institutionService;

	private final ModelMapper modelMapper;


	@PostMapping
	public ResponseEntity<InstitutionDTO> createInstitution(@Valid @RequestBody InstitutionRequest institutionRequest){
		return new ResponseEntity<>(institutionService.createInstitution(modelMapper.map(institutionRequest,Institution.class)),HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{institutionId}")
	public ResponseEntity<InstitutionDTO> updateInstitution(@Valid @RequestBody InstitutionRequest institutionRequest, @PathVariable("institutionId") Integer institutionId) {
		return new ResponseEntity<>(institutionService.updateInstitution(modelMapper.map(institutionRequest,Institution.class),institutionId), HttpStatus.OK);
	}
	
	@GetMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InstitutionDTO>> getAllInstitution(){
		return new ResponseEntity<>(institutionService.getAllInstitutions(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{institutionId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<InstitutionDTO> getInstituteById(@PathVariable("institutionId") Integer institutionId) {
		return new ResponseEntity<>(institutionService.getInstitutionById(institutionId),HttpStatus.OK);
	}

//	@DeleteMapping(value = "/{institutionId}")
//	public void deleteInstitution(@PathVariable("institutionId") Integer institutionId){
//
//	}
	
}
