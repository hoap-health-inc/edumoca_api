package com.edumoca.soma.services.controllers;

import com.edumoca.soma.entities.AcademicYear;
import com.edumoca.soma.entities.dtos.AcademicYearDto;
import com.edumoca.soma.entities.models.AcademicYearRequest;
import com.edumoca.soma.services.services.AcademicYearService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/academicYear")
@AllArgsConstructor
public class AcademicYearController {

	private final AcademicYearService academicYearService;
	private final ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<AcademicYearDto> createAcademicYear(@Valid @RequestBody AcademicYearRequest academicYearRequest) {
		return new ResponseEntity<>(academicYearService.createAcademicYear(modelMapper.map(academicYearRequest,AcademicYear.class)), HttpStatus.OK);
	}
	
	@PutMapping(value = "/{academicYearId}")
	public ResponseEntity<AcademicYearDto> updateAcademicYear(@Valid @RequestBody AcademicYearRequest academicYearRequest, Integer academicYearId) {
		return new ResponseEntity<>(academicYearService.updateAcademicYear(modelMapper.map(academicYearRequest,AcademicYear.class),academicYearId),HttpStatus.OK);
	}

	@GetMapping(value = "/{institutionId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AcademicYearDto>> getAcademicYearsByInstitution(@PathVariable("institutionId") Integer institutionId){
		return new ResponseEntity<>(academicYearService.getAcademicYearByInstitution(institutionId),HttpStatus.OK);
	}

	@GetMapping(value = "/{institutionId}/{academicYearId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AcademicYearDto> getAcademicYearByInstitutionAndAcademicYear(@PathVariable("institutionId") Integer institutionId, @PathVariable("academicYearId") Integer academicYearId){
        return new ResponseEntity<>(academicYearService.getAcademicYearByInstitutionAndAcademicYear(institutionId,academicYearId),HttpStatus.OK);
	}

}
