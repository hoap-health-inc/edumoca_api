package com.edumoca.soma.services.controllers;

import java.util.List;

import com.edumoca.soma.entities.dtos.PeriodDTO;
import com.edumoca.soma.entities.models.PeriodRequest;
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

import com.edumoca.soma.entities.Period;
import com.edumoca.soma.services.services.PeriodService;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/period")
@AllArgsConstructor
public class PeriodController {

	private final PeriodService periodService;
	private final ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<PeriodDTO> createPeriod(@Valid @RequestBody PeriodRequest periodRequest) {
		return new ResponseEntity<>(periodService.createPeriod(modelMapper.map(periodRequest,Period.class)), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{periodId}")
	public ResponseEntity<PeriodDTO> updatePeriod(@RequestBody PeriodRequest periodRequest,@PathVariable("periodId") Integer periodId) {
		return new ResponseEntity<>(periodService.updatePeriod(modelMapper.map(periodRequest,Period.class),periodId),HttpStatus.OK);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PeriodDTO>> getAllPeriods(){
		return new ResponseEntity<>(periodService.getAllPeriods(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{periodId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PeriodDTO> getPeriodById(@PathVariable("id") Integer periodId) {
		return new ResponseEntity<>(periodService.getPeriodByPeriodId(periodId),HttpStatus.OK);
	}
	

}
