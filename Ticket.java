import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
public class Ticket extends JFrame
{
   Connection con;
   ResultSet res,res1,rr;
   Statement st,st1,st2;
   String tempfare;
    String printing,word;
   public Ticket()
   {
     new expire();	   
	   try
		{
		 BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		 String lineFormInput;
		 int i,c;
	     con=DriverManager.getConnection("jdbc:odbc:mydsn");
	     st=con.createStatement();
		 st1=con.createStatement();
	     //and b_status= 'waiting'");
		 printing = JOptionPane.showInputDialog (this, "Enter Booking ID. to Print Airline Ticket.\n" +
				"(Tip: First Letter is Capital)", "FlyingBird Ticket - PrintTicket", JOptionPane.PLAIN_MESSAGE);
				if (printing == null) { }
				if (printing.equals ("")) {
					JOptionPane.showMessageDialog (this, "Provide Booking ID to Print.",
						 "FlyingBird - EmptyField", JOptionPane.PLAIN_MESSAGE);
					
				}
                 
		 else
		 {
	    res1=st1.executeQuery("select SUM(b_fare) from booking where b_id='"+printing+"'");
		 if(res1.next())
          {
           do
            {
             tempfare=res1.getString(1);
			 }while(res1.next());
	      }
		  
		 res1=st1.executeQuery("select p_rsword from payment where p_bid='"+printing+"'");
		 
		 if(res1.next())
          {
           do
            {
				
             word=res1.getString(1);
			 }while(res1.next());
			
	      }
		 res=st.executeQuery("SELECT booking.b_id, flight.f_src, flight.f_dst,booking.b_doj ,booking.b_flight, booking.b_fno, booking.b_class, booking.b_fname, booking.b_lname, booking.b_age, booking.b_sno  FROM ( booking INNER JOIN flight ON (booking.b_flight = flight.f_name) AND (booking.b_fno = flight.f_no))where booking.b_id='"+printing+"'");
	     
		 if(res.next())
          {
		 PrintWriter out=new PrintWriter(new FileWriter("d:\\project\\Ticket\\Ticket"+printing+".txt"));
	     lineFormInput="Ticket                         Booking Id "+res.getString(1)+"             FlyingBird Airline";
		 out.println(lineFormInput);
		 lineFormInput="----------------------------------------------------------------------------------------";
		 out.println(lineFormInput);
		 lineFormInput=""+res.getString(2)+" to "+res.getString(3)+"                 "+res.getString(4);
		 out.println(lineFormInput);
	     lineFormInput="----------------------------------------------------------------------------------------";
		 out.println(lineFormInput);
		 lineFormInput=""+res.getString(5)+"("+res.getString(6)+")        class - "+res.getString(7);
		 out.println(lineFormInput);
		 lineFormInput="----------------------------------------------------------------------------------------";
		 out.println(lineFormInput);
		 lineFormInput="TRAVELLERS - Age - Seat No";
		 out.println(lineFormInput);
		 lineFormInput="________________________________________________________________________________________";
		 out.println(lineFormInput);
          do
            {
             lineFormInput=""+res.getString(8)+" "+res.getString(9)+"-"+res.getString(10)+"-"+res.getString(11);
              out.println(lineFormInput);
			}while(res.next());
		 lineFormInput="________________________________________________________________________________________";
		 out.println(lineFormInput);
		 lineFormInput="FARE BREAKUP";
		 out.println(lineFormInput);
		 lineFormInput="----------------------------------------------------------------------------------------";
		 out.println(lineFormInput);
		 lineFormInput="Base fare                  Rs. "+ tempfare;
		 out.println(lineFormInput);
		 lineFormInput="Airline Fuel Charge        Rs. 5,800.0";
		 out.println(lineFormInput);
		 lineFormInput="Passanger Services Charge  Rs. 750.0";
		 out.println(lineFormInput);
		 lineFormInput="User Devlopment Fee        Rs. 1292.0";
		 out.println(lineFormInput);
		 lineFormInput="Airport Devlopment Fee     Rs. 850.0";
		 out.println(lineFormInput);
		 lineFormInput="Services Tax               Rs. 984.0";
		 out.println(lineFormInput);
		 lineFormInput="Total fare                 Rs. "+(9696+Double.parseDouble(tempfare));
		 out.println(lineFormInput);
		 lineFormInput="In Word("+word+")";
		 out.println(lineFormInput);
		 lineFormInput="----------------------------------------------------------------------------------------";
         out.println(lineFormInput);
		 lineFormInput="About This Trip";
         out.println(lineFormInput);
		 lineFormInput="* Use Your Booking Id for all Communication with FlyingBird about this trip";
         out.println(lineFormInput);
		 lineFormInput="* Check-in counter at all Airport open 2 hour before depature";
         out.println(lineFormInput);
		 lineFormInput="* Your carry-on baggage shouldn't weight more than 7 kgs";
         out.println(lineFormInput);
		 lineFormInput="* carry photo identication, you will need it as proof of identify while checking-in";
         out.println(lineFormInput);
		 lineFormInput="----------------------------------------------------------------------------------------";
         out.println(lineFormInput);
		 lineFormInput="                          Customer Care Support(+917870743855)";
		 out.println(lineFormInput);
		 lineFormInput="----------------------------------------------------------------------------------------";
         out.println(lineFormInput);
	     out.close();
		 print();
      }
		 }
		 JOptionPane.showMessageDialog((Component)null,"Ticket Genrate Successfull\n"+"(Locate on d:/project/ticket) ","Message",JOptionPane.INFORMATION_MESSAGE);
	    }
	 		catch(Exception x)
	                {
                             System.out.println(x);
	                }
      }
	  void print()
	  {
		 try
		 {
			 res=st.executeQuery("SELECT booking.b_id, flight.f_src, flight.f_dst,booking.b_doj ,booking.b_flight, booking.b_fno, booking.b_class, booking.b_fname, booking.b_lname, booking.b_age, booking.b_sno FROM ( booking INNER JOIN flight ON (booking.b_flight = flight.f_name) AND (booking.b_fno = flight.f_no))where booking.b_id='"+printing+"'");
	     if(res.next())
          {
		 String RecordToPrint = "";
	     clsPublicMethods PrintingClass = new clsPublicMethods();
		 RecordToPrint +="Ticket                          Booking ID :- "+res.getString(1)+"             FlyingBird Airline\n";
		 RecordToPrint +="----------------------------------------------------------------------------------------\n";
		 RecordToPrint +=""+res.getString(2)+" to "+res.getString(3)+"                 "+res.getString(4)+"\n";
		 RecordToPrint +="----------------------------------------------------------------------------------------\n";
		 RecordToPrint +=""+res.getString(5)+"("+res.getString(6)+")        class - "+res.getString(7)+"\n";
		 RecordToPrint +="----------------------------------------------------------------------------------------\n";
		 RecordToPrint +="TRAVELLERS - Age - Seat No\n";
		 RecordToPrint +="________________________________________________________________________________________\n";
		    do
            {
             RecordToPrint +=""+res.getString(8)+" "+res.getString(9)+"-"+res.getString(10)+"-"+res.getString(11)+"\n";
           	  }while(res.next());
		 RecordToPrint +="________________________________________________________________________________________\n";
		 RecordToPrint +="FARE BREAKUP\n";
		 RecordToPrint +="----------------------------------------------------------------------------------------\n";
		 RecordToPrint +="Base fare                  Rs. "+ tempfare+"\n";
		 RecordToPrint +="Airline Fuel Charge        Rs. 5,800.0\n";
		 RecordToPrint +="Passanger Services Charge  Rs. 750.0\n";
		 RecordToPrint +="User Devlopment Fee        Rs. 1292.0\n";
		 RecordToPrint +="Airport Devlopment Fee     Rs. 850.0\n";
		 RecordToPrint +="Services Tax               Rs. 984.0\n";
		 RecordToPrint +="Total fare                 Rs. "+(9696+Double.parseDouble(tempfare))+"\n";
		 RecordToPrint +="In Word("+word+")\n";
	     RecordToPrint +="----------------------------------------------------------------------------------------\n";
         RecordToPrint +="About This Trip\n";
       	 RecordToPrint +="* Use Your Booking Id for all Communication with FlyingBird about this trip\n";
       	 RecordToPrint +="* Check-in counter at all Airport open 2 hour before depature\n";
         RecordToPrint +="* Your carry-on baggage shouldn't weight more than 7 kgs\n";
       	 RecordToPrint +="* carry photo identication, you will need it as proof of identify while checking-in\n";
      	 RecordToPrint +="----------------------------------------------------------------------------------------\n";
     	 RecordToPrint +="                          Customer Care Support(+917870743855)\n";
		 RecordToPrint +="----------------------------------------------------------------------------------------\n";
         PrintingClass.printRecord(RecordToPrint,this);
		 RecordToPrint=null;
	  }
	  }
	  catch(Exception e)
	  {}
	  }
	  public static void main(String argv[]) throws IOException
  {
      Ticket obj=new Ticket();
      
   }
}