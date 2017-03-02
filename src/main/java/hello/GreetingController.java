package hello;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/shopping/requestcellpack")
    
    public TelePack buypack(@RequestParam(value="provider", defaultValue="Airtel") String provider,@RequestParam(value="type", defaultValue="data") String type,@RequestParam(value="packname", defaultValue="basic") String packname) {
    	try{
    		 Connection con= ShopingDBCon.dbCon();
    		 
    		 Statement stmt = con.createStatement();
    		 System.out.println("Got statement)");
    	     String sql;
    	     sql = "select count from telecom.telestore where packname =('"+packname+"') and provider ='"+provider+"'";
    	     //sql="Insert into usermgmtms.users (UserId,UserName,Password) values('sgp','suresh','password')";
    	     ResultSet rs=stmt.executeQuery(sql);
    	     while(rs.next())
    	     {
    	    	      	     
    	     if(((Integer)rs.getInt(1)).intValue()>0)
    	     {
    	    	 System.out.println("Got package to sell)");
    	    	 sql = "update  telecom.telestore set count=count-1 where packname =('"+packname+"') and provider ='"+provider+"'";
        	     //sql="Insert into usermgmtms.users (UserId,UserName,Password) values('sgp','suresh','password')";
        	     stmt.executeUpdate(sql);
    	         return new TelePack(provider+" ,"+packname+" will be enabled on your phone","Success"+" Balance pack= "+returnCount(packname, provider));
    	     }
    	     
    	     }
    	     
    	     con.close();
    			}
    			catch(Exception ex)
    			{
    				
    				System.out.println("Query error"+ex);
    			}
    			
    	
        return new TelePack(provider+" ,"+packname+"Not available "," Failed"+" Balance pack= "+returnCount(packname, provider)	);
    }
    
@RequestMapping("/shopping/donatecellpack")
    
    public TelePack sellpack(@RequestParam(value="provider", defaultValue="Airtel") String provider,@RequestParam(value="type", defaultValue="data") String type,@RequestParam(value="packname", defaultValue="basic") String packname) {
    	
	
	try{
		 Connection con= ShopingDBCon.dbCon();
		 int mycount=0;
		 Statement stmt = con.createStatement();
		 System.out.println("Got statement)");
	     String sql;
	     sql = "select count from telecom.telestore where packname =('"+packname+"') and provider ='"+provider+"'";
	     //sql="Insert into usermgmtms.users (UserId,UserName,Password) values('sgp','suresh','password')";
	     ResultSet rs=stmt.executeQuery(sql);
	     System.out.println("After query)");
	     
	    	 sql = "Insert into telecom.telestore  (provider,type,packname,count) values('"+provider+"','"+type+"','"+packname+"',"+1+")";
	     
	     
	     while(rs!= null && rs.next())
	     {
	    	 mycount=((Integer)rs.getInt(1)).intValue(); 
	    	 System.out.println("Got count)"+ mycount);
	     if(mycount>0)
	     {
	    
	     sql = "update  telecom.telestore set count=count+1 where packname =('"+packname+"') and provider ='"+provider+"'";
	     }
	     else
	     { sql = "Insert into telecom.telestore  (provider,type,packname,count) values('"+provider+"','"+type+"','"+packname+"'"+1+")";}
	     //sql="Insert into usermgmtms.users (UserId,UserName,Password) values('sgp','suresh','password')";
	     }
	     //sql="Insert into usermgmtms.users (UserId,UserName,Password) values('sgp','suresh','password')";
	     stmt.executeUpdate(sql);
	     System.out.println("Got insert)");
	     
	     System.out.println("Jenkins Test+1)");
	     con.close();
	     return new TelePack(provider+" "+packname,"Donation Success "+" Balance pack= "+returnCount(packname, provider)
	         		
	        		
	        		);
	     
			}
			catch(Exception ex)
			{
				
				System.out.println("Query error"+ex);
			}


	
    	
        return new TelePack(provider+packname,"Donation Failed"
        		
        		
        		);
    } 
    
private int returnCount(String packname,String provider)    

{
	
	System.out.println("In return count+2");
	
	System.out.println("Testing second time");
	try{
	Connection con= ShopingDBCon.dbCon();
	 int mycount=0;
	 Statement stmt = con.createStatement();
	 System.out.println("Got statement)");
    String sql;
    sql = "select count from telecom.telestore where packname =('"+packname+"') and provider ='"+provider+"'";
    
    ResultSet rs=stmt.executeQuery(sql);
    System.out.println("After query)");
    
   	  
    
    
    while(rs!= null && rs.next())
    {
   	 mycount=((Integer)rs.getInt(1)).intValue(); 
    }
    return mycount;
	}
	catch(Exception e)
	
	{
		System.out.println("Exception in returning count "+ e);
	}
	return -1;
}

}
