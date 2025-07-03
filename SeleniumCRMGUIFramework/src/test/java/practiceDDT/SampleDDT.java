package practiceDDT;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SampleDDT {
	public static void main(String[] args) throws IOException {
		//Step 1: Get the java representation object of physical file
		FileInputStream f = new FileInputStream("C:\\Users\\vaibh\\OneDrive\\Desktop\\commondata.properties");
		
		//Step 2: Load all keys using Properties class
		Properties p = new Properties();
		p.load(f);
		
		//Step 3: Get values based on key
		System.out.println(p.getProperty("browser"));
		System.out.println(p.getProperty("url"));
	}

}
