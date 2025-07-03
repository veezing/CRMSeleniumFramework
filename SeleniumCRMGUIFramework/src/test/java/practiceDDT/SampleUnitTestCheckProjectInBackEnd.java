package practiceDDT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

public class SampleUnitTestCheckProjectInBackEnd {
	
	@Test
	public void projectCheckTest() throws SQLException {
		
		String expectedProjectName="Google";
		boolean flag = false;
		//Step 1: Load /register the db driver
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		
		//Step 2: connect to database
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		System.out.println("==Done==");
		
		//step 3: create sql statement
		Statement statement = connection.createStatement();
		
		//step 4: execute select query & get result
		ResultSet resultSet = statement.executeQuery("select * from project");
		while(resultSet.next()) {
			String actualProjectName = resultSet.getString(4);
			if(expectedProjectName.equals(actualProjectName)) {
				flag = true;
				System.out.println(expectedProjectName+" is available==PASS");
			}
		}
		
		if(flag==false) {
			System.out.println(expectedProjectName+" is NOT available==FAIL");
			Assert.fail();
		}	
		//step 5: close the connection
		connection.close();
		
		
		
	}

}
