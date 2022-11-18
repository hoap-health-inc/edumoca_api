package com.edumoca.soma.services.services;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface LoaderService {

	XSSFSheet getWorksheet(XSSFWorkbook workbook, String sheetName);
}
