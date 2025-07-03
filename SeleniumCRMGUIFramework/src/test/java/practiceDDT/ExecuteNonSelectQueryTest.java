package practiceDDT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class ExecuteNonSelectQueryTest {

	public static void main(String[] args) throws Throwable {
		//Step 1: Load /register the db driver
				Driver driverRef = new Driver();
				DriverManager.registerDriver(driverRef);
				
				//Step 2: connect to database
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
				System.out.println("==Done==");
				
				//step 3: create sql statement
				Statement statement = connection.createStatement();
				
				//step 4: execute non select query & get result
				int result = statement.executeUpdate("insert into project values('TY_PROJ_2000','Vaibhav','2025-05-14','Google','Active', '10')");
				System.out.println(result);
				
				//step 5: close the connection
				connection.close();

	}

}
