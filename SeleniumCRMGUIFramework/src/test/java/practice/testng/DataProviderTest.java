package practice.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {
	@Test(dataProvider = "getData")
	public void createContactTest(String firstName, String lastName) {
		System.out.println("First Name is: " + firstName + " , Last Name is: " + lastName);
	}

	@DataProvider
	public Object[][] getData() {
		Object[][] objArr = new Object[3][2];
		
		objArr[0][0] = "Sachin";
		objArr[0][1] = "Tendulkar";
		
		objArr[1][0] = "Rohit";
		objArr[1][1] = "Sharma";
		
		objArr[2][0] = "Virat";
		objArr[2][1] = "Kohli";
		return objArr;
	}

}
