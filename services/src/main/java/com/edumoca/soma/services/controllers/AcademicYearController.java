package com.edumoca.soma.services.controllers;

import com.edumoca.soma.entities.dtos.AcademicYearDTO;
import com.edumoca.soma.entities.models.AcademicYearRequest;
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

import com.edumoca.soma.entities.AcademicYear;
import com.edumoca.soma.services.services.AcademicYearService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/academicYear")
@AllArgsConstructor
public class AcademicYearController {

	private final AcademicYearService academicYearService;
	private final ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<AcademicYearDTO> createAcademicYear(@Valid @RequestBody AcademicYearRequest academicYearRequest) {
		return new ResponseEntity<>(academicYearService.createAcademicYear(modelMapper.map(academicYearRequest,AcademicYear.class)), HttpStatus.OK);
	}
	
	@PutMapping(value = "/{academicYearId}")
	public ResponseEntity<AcademicYearDTO> updateAcademicYear(@Valid @RequestBody AcademicYearRequest academicYearRequest,Integer academicYearId) {
		return new ResponseEntity<>(academicYearService.updateAcademicYear(modelMapper.map(academicYearRequest,AcademicYear.class),academicYearId),HttpStatus.OK);
	}

	@GetMapping(value = "/{institutionId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AcademicYearDTO>> getAcademicYearsByInstitution(@PathVariable("institutionId") Integer institutionId){
		return new ResponseEntity<>(academicYearService.getAcademicYearByInstitution(institutionId),HttpStatus.OK);
	}

	@GetMapping(value = "/{institutionId}/{academicYearId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AcademicYearDTO> getAcademicYearByInstitutionAndAcademicYear(@PathVariable("institutionId") Integer institutionId,@PathVariable("academicYearId") Integer academicYearId){
        return new ResponseEntity<>(academicYearService.getAcademicYearByInstitutionAndAcademicYear(institutionId,academicYearId),HttpStatus.OK);
	}

}
