package com.vtiger.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	public String getDataFromExcel(String sheetName, int rowNum, int cellNum) throws Exception {
		
		FileInputStream fis = new FileInputStream("./testdata/testScriptData.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		
		return data;
	}
	
	public int getRowCount(String sheetName) throws Exception {
		FileInputStream fis = new FileInputStream("./testdata/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int lastRowNum = wb.getSheet(sheetName).getLastRowNum();
		
		return lastRowNum;
		
	}
	
	public void setDataIntoExcel(String sheetName, int rowNum, int cellNum, String data) throws Exception {
		FileInputStream fis = new FileInputStream("./testdata/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum).setCellValue(data);
		
		FileOutputStream fos = new FileOutputStream("./testdata/testScriptData.xlsx");
		wb.write(fos);
		wb.close();
		
	}

}
