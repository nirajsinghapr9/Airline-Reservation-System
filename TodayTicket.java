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
public class TodayTicket extends JFrame
{
   Connection con;
   ResultSet res,res1,rr;
   Statement st,st1;
   Date date = new Date();
   String s;
   DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
   public TodayTicket()
   {
	    new expire();
		try
		{
		 BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		 String lineFormInput;
		 int i,c;
	     con=DriverManager.getConnection("jdbc:odbc:mydsn");
	     st=con.createStatement();
	     res=st.executeQuery("SELECT booking.b_id, booking.b_fno, booking.b_flight, booking.b_class, booking.b_fname, booking.b_lname, booking.b_sno, booking.b_age, booking.b_gen, booking.b_fare FROM booking where b_date='"+df.format(date)+"'");
	     PrintWriter out=new PrintWriter(new FileWriter("d:\\project\\Reports\\today\\"+df.format(date)+".txt"));
	     lineFormInput="\t\t\t\t\t\tFlyingBird AirLine Ticket Reservation \n\t\t\t\t\t\t```````````````````````````````````````"+"\n";
		 out.println(lineFormInput);
		 lineFormInput="\t\t\t\t\t\t\t\tToday Ticket Report \n\t\t\t\t\t\t\t\t-------------------\n";
	     out.println(lineFormInput);
         lineFormInput="Date         :"+df.format(date)+"\n";
		 out.println(lineFormInput);
		 if(res.next())
          {
			  
		 lineFormInput="Booking ID \t Flight No\tFlight Name\t\t\tclass\t First Name\t  Last Name\tSeat No\t   age\t  gender\t    fare";
		 out.println(lineFormInput);
		 lineFormInput=" ********************************************************************";
		 out.println(lineFormInput);
          do
            {
             lineFormInput="\t"+res.getString(1)+"\t\t\t"+res.getString(2)+"\t\t"+res.getString(3)+"\t\t\t"+res.getString(4)+"\t\t"+res.getString(5)+"\t\t"+res.getString(6)+"\t\t"+res.getString(7)+"\t\t"+res.getString(8)+"\t\t"+res.getString(9)+"\t\t"+res.getString(10)+"\n";
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
      TodayTicket obj=new TodayTicket();
      
   }
}
	  