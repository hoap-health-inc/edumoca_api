package com.edumoca.soma.services.controllers;

import com.edumoca.soma.entities.Period;
import com.edumoca.soma.entities.dtos.PeriodDto;
import com.edumoca.soma.entities.models.PeriodRequest;
import com.edumoca.soma.services.services.PeriodService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/period")
@AllArgsConstructor
public class PeriodController {

	private final PeriodService periodService;
	private final ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<PeriodDto> createPeriod(@Valid @RequestBody PeriodRequest periodRequest) {
		return new ResponseEntity<>(periodService.createPeriod(modelMapper.map(periodRequest,Period.class)), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{periodId}")
	public ResponseEntity<PeriodDto> updatePeriod(@RequestBody PeriodRequest periodRequest, @PathVariable("periodId") Integer periodId) {
		return new ResponseEntity<>(periodService.updatePeriod(modelMapper.map(periodRequest,Period.class),periodId),HttpStatus.OK);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PeriodDto>> getAllPeriods(){
		return new ResponseEntity<>(periodService.getAllPeriods(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{periodId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PeriodDto> getPeriodById(@PathVariable("id") Integer periodId) {
		return new ResponseEntity<>(periodService.getPeriodByPeriodId(periodId),HttpStatus.OK);
	}
	

}
