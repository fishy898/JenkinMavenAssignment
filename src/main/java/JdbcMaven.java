
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class JdbcMaven {


	
	public static void main(String arg[])
	{
		String dbUrl = "jdbc:mysql://localhost:3306/demo";
		String user = "student";
		String pass = "student";
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?serverTimezone=UTC", "student" , "student");
			myStmt = myConn.createStatement();			
			
			//query members
			System.out.println("Jenkin did this!");
			System.out.println("\n --Query members-- \n ");
			myRs = myStmt.executeQuery("select * from employees");
			while (myRs.next()) 
			{
				System.out.println(myRs.getString("last_name")+", "+ myRs.getString("first_name"));
			}
			System.out.println("\n --Creating members-- \n ");
			
			
			
			//adding a new element
			int rowsAffected = myStmt.executeUpdate(
					"insert into employees"+
					"(last_name, first_name, email, department, salary)" +
					"values "+
					"('Lee', 'Robert', 'robert@mail.com', 'HR', 40000)"
			);
			
			myRs = myStmt.executeQuery("select * from employees order by last_name");
			while (myRs.next()) 
			{
				System.out.println(myRs.getString("last_name")+", "+ myRs.getString("first_name"));
			}
			
			
			//updating table
			System.out.println("\n --Updating members-- \n ");
			rowsAffected = myStmt.executeUpdate(
					"update employees "+
					"set last_name= 'Smith' " +
					"where last_name='Lee' and first_name = 'Robert' "
			);

			//a new resultSet must be created every time print all is use
			myRs = myStmt.executeQuery("select * from employees order by last_name");
			while (myRs.next()) 
			{
				System.out.println(myRs.getString("last_name")+", "+ myRs.getString("first_name"));
			}
			
			//delete from table
			System.out.println("\n --Deleting members-- \n ");
			rowsAffected = myStmt.executeUpdate(
					"delete from employees "+
					"where last_name='Smith' and first_name = 'Robert' "
			);
			myRs = myStmt.executeQuery("select * from employees order by last_name");
			while (myRs.next()) 
			{
				System.out.println(myRs.getString("last_name")+", "+ myRs.getString("first_name"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				myRs.close();
				myStmt.close();
				myConn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}
