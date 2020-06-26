import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.*;
import java.text.*;
import java.util.Date;
import java.text.DateFormat; 
import java.text.SimpleDateFormat;
import java.util.Calendar; 
import java.util.Date;
public class check
{  
   Connection con;
   ResultSet res,res1,rr;
   Statement st,st1;
   Date date = new Date();
   Date date1 = new Date();
   SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
   DateFormat df = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
   String dateInString;
   int ctr=0,j=1;
   public check()
   {
     try
         {
	       con =DriverManager.getConnection("jdbc:odbc:airlinedsn");
	       st = con.createStatement();
		   res=st.executeQuery("SELECT booking.b_date  FROM booking where b_status='Waiting'");
	       if(res.next())
		   {
			 do
			 {			 
			  ctr++; 
		   }while(res.next());
		   }
		  String z[]=new String[ctr+3];
		   res=st.executeQuery("SELECT booking.b_date  FROM booking where b_status='Waiting'");
		   if(res.next())
		   {
			 do
			 {			 
			  z[j++]=res.getString(1)+" 00:00:00";
		   }while(res.next());
		   }
		   
		   for(int i=1;i<=ctr;i++)
		   {    String y=z[i].substring(0,11);
				
				date1 = formatter.parse(z[i]);
                int diffInDays = (int) ((date.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24));
		       
			   if(diffInDays>3)
				 st.executeUpdate("DELETE from booking where b_date='"+y+"'");
		   }
		   	   
         }
	    catch(Exception e)
	     {
	    	 System.out.println(e);
	     }		 
        
   }
 
public static void main(String argv[])throws IOException
 {
     check obj =new check();
 }
}
 