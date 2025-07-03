package practiceDDT;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcel {
	public static void main(String[] args) throws Exception {
		//Step 1: Get Excel path location and java object of physical file
		FileInputStream f = new FileInputStream("D:\\AdvanceSelenium\\SeleniumCRMGUIFramework\\src\\test\\resources\\testScriptData.xlsx");
		
		//Step 2: Open Workbook in Read Mode
		Workbook wb = WorkbookFactory.create(f);
		
		//Step 3: Get control of the sheet
		Sheet sh= wb.getSheet("org");
		
		//Step 4: Get control of the row
		Row row = sh.getRow(1);
		
		//Step 5: Get control of cell & read String data
		Cell cell = row.getCell(2);
		String cellData = cell.getStringCellValue();
		
		System.out.println(cellData);
		
		//Step 6: Close the Workbook
		wb.close();
	}

}
