package practiceDDT;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataToExcel {
	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream("D:\\AdvanceSelenium\\SeleniumCRMGUIFramework\\src\\test\\resources\\testScriptData.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis);
		Cell cell = wb.getSheet("org").getRow(1).getCell(2);
		cell.setCellType(CellType.STRING);
		cell.setCellValue("Google123");
		
		FileOutputStream fos = new FileOutputStream("D:\\AdvanceSelenium\\SeleniumCRMGUIFramework\\src\\test\\resources\\testScriptData.xlsx");
		wb.write(fos);
		wb.close();
		System.out.println("===Executed===");
		
		
		
	}

}
