package com.edumoca.soma.services.services.impl;

import com.edumoca.soma.services.services.LoaderService;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class LoaderServiceImpl implements LoaderService{

	@Override
	public XSSFSheet getWorksheet(XSSFWorkbook workbook, String sheetName) {
		return workbook.getSheet(sheetName);
	}

	
}
