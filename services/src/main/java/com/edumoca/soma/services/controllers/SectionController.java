package com.edumoca.soma.services.controllers;
import java.util.List;
import com.edumoca.soma.entities.dtos.SectionDTO;
import com.edumoca.soma.entities.models.SectionRequest;
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
import com.edumoca.soma.entities.Section;
import com.edumoca.soma.services.services.SectionService;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/section")
@AllArgsConstructor
public class SectionController {
	private final SectionService sectionService;
	private final ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<SectionDTO> createSection(@Valid @RequestBody SectionRequest sectionRequest) {
		return new ResponseEntity<>(sectionService.createSection(modelMapper.map(sectionRequest,Section.class)),HttpStatus.OK);
	}
	
	@PutMapping(value = "/{sectionId}")
	public ResponseEntity<SectionDTO> updateSection(@Valid @RequestBody SectionRequest sectionRequest, @PathVariable("sectionId") Integer sectionId) {
		return new ResponseEntity<>(sectionService.updateSection(modelMapper.map(sectionRequest,Section.class),sectionId), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{institutionId}/all",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SectionDTO>> getAllSectionByInstitution(@PathVariable("institutionId") Integer institutionId){
		return new ResponseEntity<>(sectionService.getAllSectionByInstitution(institutionId),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{institutionId}/{sectionId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SectionDTO> getSectionByInstitutionAndSection(@PathVariable("institutionId") Integer institutionId,@PathVariable("sectionId") Integer sectionId) {
		return new ResponseEntity<>(sectionService.getSectionByInstitutionAndSection(institutionId,sectionId),HttpStatus.OK);
	}
	
}
