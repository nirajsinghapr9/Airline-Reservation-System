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
public class FlightDetails extends JFrame
{
   Connection con;
   ResultSet res,res1,rr;
   Statement st,st1;
   Date date = new Date();
   String s;
   DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
   public FlightDetails()
   {
	    new expire();
		try
		{
		 BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		 String lineFormInput;
		 int i,c;
	     con=DriverManager.getConnection("jdbc:odbc:mydsn");
	     st=con.createStatement();
	     res=st.executeQuery("SELECT flight.f_no, flight.f_name, flight.f_type, flight.f_src, flight.f_dst FROM flight");
	     PrintWriter out=new PrintWriter(new FileWriter("d:\\project\\Reports\\flight\\Flight-Details.txt"));
	     lineFormInput="\t\t\t\t\t\tFlyingBird AirLine Ticket Reservation \n\t\t\t\t\t\t```````````````````````````````````````"+"\n";
		 out.println(lineFormInput);
		 lineFormInput="\t\t\t\t\t\t\t\tFlight Report \n\t\t\t\t\t\t\t\t-------------------\n";
	     out.println(lineFormInput);
         lineFormInput="Date         :"+df.format(date)+"\n";
		 out.println(lineFormInput);
		 if(res.next())
          {
			  
		 lineFormInput=" Flight No\tFlight Name\t\t\tType\t Source\t Destination";
		 out.println(lineFormInput);
		 lineFormInput=" **********************************************************";
		 out.println(lineFormInput);
          do
            {
             lineFormInput="\t"+res.getString(1)+"\t\t"+res.getString(2)+"\t\t"+res.getString(3)+"\t\t"+res.getString(4)+"\t\t"+res.getString(5);
              out.println(lineFormInput);
			 }while(res.next());
	      } 
		  else
		lineFormInput="\n\n\t\t\t\tNo Ticket Booked Today!!!!!";
		  
	     out.println(lineFormInput);
	     out.close();

	}
	 		catch(Exception x)
	                {
                             System.out.println("Error during reading/writing");
	                }
      }
	  public static void main(String argv[]) throws IOException
  {
      FlightDetails obj=new FlightDetails();
      
   }
}
	  