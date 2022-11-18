package com.edumoca.soma.services.services.impl;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.edumoca.soma.entities.dtos.PeriodDTO;
import com.edumoca.soma.entities.models.PeriodResponse;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.edumoca.soma.entities.Period;
import com.edumoca.soma.entities.exception.DataNotFoundException;
import com.edumoca.soma.entities.repositories.CourseSessionRepository;
import com.edumoca.soma.entities.repositories.PeriodRepository;
import com.edumoca.soma.services.services.PeriodService;

@Service
@AllArgsConstructor
public class PeriodServiceImpl implements PeriodService{

	private final PeriodRepository periodRepository;
	
	private final CourseSessionRepository courseSessionRepository;
	private final ModelMapper modelMapper;


	@Override
	public PeriodDTO createPeriod(Period period) {
		return modelMapper.map(periodRepository.save(period),PeriodDTO.class);
	}

	@Override
	public PeriodDTO updatePeriod(Period period,Integer periodId) {
        Optional<PeriodResponse> periodResponse = Optional.ofNullable(periodRepository.getPeriodByPeriodId(periodId));
		if(periodResponse.isPresent())
			period.setPeriodId(periodId);
		return modelMapper.map(periodRepository.save(period),PeriodDTO.class);
	}

	@Override
	public List<PeriodDTO> getAllPeriods() {
		return periodRepository.getAllPeriods().stream().map(e->{
			return modelMapper.map(e,PeriodDTO.class);
		}).collect(Collectors.toList());
	}

	@Override
	public PeriodDTO getPeriodByPeriodId(Integer periodId) {
		Optional<PeriodResponse> periodResponse = Optional.ofNullable(periodRepository.getPeriodByPeriodId(periodId));
		if(periodResponse.isPresent())
			return modelMapper.map(periodResponse.get(),PeriodDTO.class);
		else
			throw new DataNotFoundException("Period with id not found.");
	}


	@Override
	public Map<String, Set<PeriodDTO>> loadPeriods(XSSFSheet periodSheet, String periodsSheetName) {
		Map<String, Set<PeriodDTO>> periodsMap = new HashMap<>();
		Set<PeriodDTO> periodsSet = new HashSet<>();
		periodSheet.rowIterator().forEachRemaining(row->{
			if(row.getRowNum()>0){
				Period period = new Period();
				period.setPeriodName("Period ".concat(String.valueOf(Math.round(row.getCell(0).getNumericCellValue()))));
				period.setPeriodNo(Integer.parseInt(String.valueOf(Math.round(row.getCell(1).getNumericCellValue()))));
				periodRepository.save(period);
				PeriodDTO periodDTO = new PeriodDTO();
				periodDTO.setPeriodId(period.getPeriodId());
				periodDTO.setPeriodName(period.getPeriodName());
				periodDTO.setPeriodNo(period.getPeriodNo());
				periodsSet.add(periodDTO);
			}
		});
		periodsMap.put(periodsSheetName,periodsSet);
		return periodsMap;
	}
}
