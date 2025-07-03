package practiceDDT;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromMultipleRows {
	public static void main(String[] args) throws Exception {
		FileInputStream f = new FileInputStream("D:\\AdvanceSelenium\\SeleniumCRMGUIFramework\\src\\test\\resources\\testScriptData.xlsx");
		
		Workbook wb = WorkbookFactory.create(f);
		
		Sheet sh = wb.getSheet("Sheet1");
		int lastRowNum = sh.getLastRowNum();
		
		for(int i=1;i<=lastRowNum;i++) {
			Row row = sh.getRow(i);
		
			String cell1 = row.getCell(0).toString();
			String cell2 = row.getCell(1).toString();
		
			System.out.println(cell1 +"\t"+ cell2);
		}
		
		wb.close();
	}

}
