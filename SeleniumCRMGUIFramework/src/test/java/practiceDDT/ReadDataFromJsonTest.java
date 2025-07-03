package practiceDDT;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadDataFromJsonTest {

	public static void main(String[] args) throws Exception, IOException, ParseException {
		
		//Step 1: Parse JSON Physical file into Java Object using JSONParser Class
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("./src/test/resources/appCommonData.json"));
		
		//Step 2: Convert Java Object to JsonObject using downcasting
		JSONObject map = (JSONObject)obj;
		
		//Step 3:Get value from Json file using key
		System.out.println(map.get("url"));
		System.out.println(map.get("browser"));
		System.out.println(map.get("username"));
		System.out.println(map.get("password"));
		
		


	}

}
