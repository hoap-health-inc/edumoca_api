package com.edumoca.soma.services.services;

import com.edumoca.soma.entities.Period;
import com.edumoca.soma.entities.dtos.PeriodDto;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PeriodService {
	PeriodDto createPeriod(Period period);
	PeriodDto updatePeriod(Period period, Integer periodId);
	List<PeriodDto> getAllPeriods();
	PeriodDto getPeriodByPeriodId(Integer periodId);

	Map<String, Set<PeriodDto>> loadPeriods(XSSFSheet periodSheet, String periodsSheetName);
}
