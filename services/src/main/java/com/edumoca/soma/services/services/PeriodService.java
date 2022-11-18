package com.edumoca.soma.services.services;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.edumoca.soma.entities.dtos.PeriodDTO;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.edumoca.soma.entities.Period;

public interface PeriodService {
	PeriodDTO createPeriod(Period period);
	PeriodDTO updatePeriod(Period period,Integer periodId);
	List<PeriodDTO> getAllPeriods();
	PeriodDTO getPeriodByPeriodId(Integer periodId);

	Map<String, Set<PeriodDTO>> loadPeriods(XSSFSheet periodSheet, String periodsSheetName);
}
