package com.vtiger.crm.generic.fileutility;

import java.io.FileInputStream;
import java.util.Properties;

public class FileUtility {
	public String getDataFromPropertiesFile(String key) throws Exception {
		
		FileInputStream fis = new FileInputStream("./configAppData/commondata.properties");
		Properties p = new Properties();
		p.load(fis);
		String data = p.getProperty(key);
	
	return data;
	}
	

}
