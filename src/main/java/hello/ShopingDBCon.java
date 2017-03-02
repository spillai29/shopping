package hello;

import java.sql.Connection;
import java.sql.DriverManager;

public   class ShopingDBCon {
	 
	 
	 static Connection   dbCon()
	 
	 {
		 Connection con=null;
		try
		{ Class.forName("com.mysql.jdbc.Driver");
		 final String DB_URL = "jdbc:mysql://localhost:3306/usermgmtms";

		   //  Database credentials
		    final String USER = "root";
		    final String PASS = "root";
		  con=DriverManager.getConnection(DB_URL,USER,PASS);
		 
		 //return con;
		 
		}
		catch(Exception ex)
		{
			System.out.println("Connection Failed:ShoppingDBCon "+ex);
		}
		 
		 
		 return con;
	 }

}
