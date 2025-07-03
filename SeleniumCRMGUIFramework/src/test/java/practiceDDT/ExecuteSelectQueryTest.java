package practiceDDT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class ExecuteSelectQueryTest {

	public static void main(String[] args) throws Throwable {
		Connection connection = null;
		try {
				//Step 1: Load /register the db driver
				Driver driverRef = new Driver();
				DriverManager.registerDriver(driverRef);
				
				//Step 2: connect to database
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
				System.out.println("==Done==");
				
				//step 3: create sql statement
				Statement statement = connection.createStatement();
				
				//step 4: execute select query & get result
				ResultSet resultSet = statement.executeQuery("select * from project");
				while(resultSet.next()) {
					System.out.println(resultSet.getString(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getString(3)+"\t"+resultSet.getString(4)+"\t"+resultSet.getString(5)+"\t"+resultSet.getInt(6));
				}
		}catch(Exception e) {
			System.out.println("Exception is handled");
		}finally {
				
				//step 5: close the connection
				connection.close();
				System.out.println("Connection is closed");
		}
	}

}
