package practiceDDT;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataBasedOnCondition {
	public static void main(String[] args) throws IOException {
		
		String expectedTestId= "Tc_02";

		
		String data;
		String data1 = "";
		String data2 = "";
		String data3 = "";
		boolean flag = false;
		FileInputStream f = new FileInputStream("D:\\AdvanceSelenium\\SeleniumCRMGUIFramework\\src\\test\\resources\\testScriptData.xlsx");
		
		Workbook wb = WorkbookFactory.create(f);
		
		Sheet sh = wb.getSheet("org");
		
		int lastRowNum = sh.getLastRowNum();
		
		for(int i =0; i<=lastRowNum;i++) {
			try {
				data = sh.getRow(i).getCell(0).toString();
				if(data.equals(expectedTestId)) {
					flag=true;
					data1 = sh.getRow(i).getCell(1).toString();
					data2 = sh.getRow(i).getCell(2).toString();
					data3 = sh.getRow(i).getCell(3).toString();
				}
			}catch(Exception e) {}
		}
		if(flag==true) {
			System.out.println(data1);
			System.out.println(data2);
			System.out.println(data3);
		}
		else
			System.out.println(expectedTestId + " data is not available");
	}

}
