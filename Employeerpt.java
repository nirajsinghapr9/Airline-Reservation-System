import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.util.Date;
import java.text.DateFormat; 
import java.text.SimpleDateFormat;
import java.util.Calendar; 
import java.util.Date;
public class Employeerpt extends JFrame
{
   Connection con;
   ResultSet res,res1,rr;
   Statement st,st1;
   Date date = new Date();
   String s;
   DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
   public Employeerpt()
   {
new expire();	   
	   try
		{
		 BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		 String lineFormInput;
		 int i,c;
	     con=DriverManager.getConnection("jdbc:odbc:mydsn");
	     st=con.createStatement();
	     res=st.executeQuery("SELECT * from login");
	     PrintWriter out=new PrintWriter(new FileWriter("d:\\project\\Reports\\Employee\\Employee.txt"));
	     lineFormInput="\t\t\t\t\t\tFlyingBird AirLine Ticket Reservation \n\t\t\t\t\t\t```````````````````````````````````````"+"\n";
		 out.println(lineFormInput);
		 lineFormInput="\t\t\t\t\t\t\t\tEmployee Details \n\t\t\t\t\t\t\t\t-------------------\n";
	     out.println(lineFormInput);
         lineFormInput="Date         :"+df.format(date)+"\n";
		 out.println(lineFormInput);
		 if(res.next())
          {
			  
		 lineFormInput="Employee Type \t Employee Name\tGender\t  DOb  \t Mobile Number\t Address";
		 out.println(lineFormInput);
		 lineFormInput=" ********************************************************************";
		 out.println(lineFormInput);
          while(res.next())
            {
             lineFormInput="\t"+res.getString(3)+"\t"+res.getString(4)+"\t\t"+res.getString(5)+"\t\t"+res.getString(6)+"\t\t"+res.getString(7)+"\t\t"+res.getString(8)+"\n";
              out.println(lineFormInput);
			 }
	      } 
		  	  
	     out.println(lineFormInput);
	     out.close();

	}
	 		catch(Exception x)
	                {
                             System.out.println(x);
							 //System.out.println("Error during reading/writing");
	                }
      }
	  public static void main(String argv[]) throws IOException
  {
      Employeerpt obj=new Employeerpt();
      
   }
}
	  