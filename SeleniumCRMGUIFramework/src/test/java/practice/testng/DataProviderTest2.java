package practice.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest2 {
	@Test(dataProvider = "getData")
	public void createContactTest(String firstName, String lastName, long phoneNumber) {
		System.out.println("First Name is: " + firstName + " , Last Name is: " + lastName+" , Phone Number is: "+phoneNumber);
	}

	@DataProvider
	public Object[][] getData() {
		Object[][] objArr = new Object[3][3];
		
		objArr[0][0] = "Sachin";
		objArr[0][1] = "Tendulkar";
		objArr[0][2] = 1234567890L;
		
		objArr[1][0] = "Rohit";
		objArr[1][1] = "Sharma";
		objArr[1][2] = 1234567891L;
		
		objArr[2][0] = "Virat";
		objArr[2][1] = "Kohli";
		objArr[2][2] = 1234567892L;
		return objArr;
	}

}
