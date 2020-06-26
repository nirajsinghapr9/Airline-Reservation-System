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
import net.sourceforge.jdatepicker.*; 
import net.sourceforge.jdatepicker.graphics.*; 
import net.sourceforge.jdatepicker.impl.*; 
import net.sourceforge.jdatepicker.util.*;
public class FixDateTicket extends JFrame implements ActionListener
{
   Connection con;
   ResultSet res,res1,rr;
   Statement st,st1;
   Date date = new Date();
   String s;
   public UtilDateModel model;
   public JDatePanelImpl datePanel;
   public JDatePickerImpl datePicker;
   JButton save = new JButton("save");
   BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		 String lineFormInput;
		 String reportDate;
   public FixDateTicket()
   {
	    new expire();
		try
         {
	       con =DriverManager.getConnection("jdbc:odbc:mydsn");
	       st = con.createStatement();
         }
	    catch(Exception e)
	     {
	    	 System.out.println(e);
	     }
		 setSize(280,55);
		 setLocation(545,250);
		  setLayout(null);
        setVisible(true);
	    setTitle("Fix Date Ticket");
		 model = new UtilDateModel();  
        datePanel = new JDatePanelImpl(model);  
        datePicker = new JDatePickerImpl(datePanel);
		 add(datePicker);
		 //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true); 
		datePicker.setBounds(0,0,200,26);
		 add(save);
		 save.setBounds(203,0,70,26);
		 save.addActionListener(this);
		 BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		 String lineFormInput;
		 Date selectedDate =(Date) datePicker.getModel().getValue();
         }
   void save1()
   {
	   		try
		{
		 Date selectedDate =(Date) datePicker.getModel().getValue();
         DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
	     reportDate = df.format(selectedDate);
         st=con.createStatement();
		 res=st.executeQuery("SELECT booking.b_id, booking.b_fno, booking.b_flight, booking.b_class, booking.b_fname, booking.b_lname, booking.b_sno, booking.b_age, booking.b_gen, booking.b_fare FROM booking where b_date='"+reportDate+"'");
	     PrintWriter out=new PrintWriter(new FileWriter("d:\\project\\Reports\\Fixdate\\"+df.format(date)+".txt"));
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
		 this.dispose();
		 JOptionPane.showMessageDialog((Component)null,"Report Genrate Successfull\n"+"(Locate on d:/project/Reports/Fixdate) ","Message",JOptionPane.INFORMATION_MESSAGE);

	    }
	 		catch(Exception x)
	                {
                             System.out.println("Error during reading/writing");
	                }
      }
   
	public void actionPerformed(ActionEvent e)
    {
     if(e.getSource()==save)
	  {
         save1();
	     
	  }
	  
  }
	  public static void main(String argv[]) throws IOException
  {
      FixDateTicket obj=new FixDateTicket();
      
   }
}