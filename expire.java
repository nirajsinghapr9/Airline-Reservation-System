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
public class expire
{  
   Connection con;
   ResultSet res,res1,rr;
   Statement st,st1;
   Date date = new Date();
   Date date1 = new Date();
   String date3;
   SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
   DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
   String dateInString;
   int ctr=0,j=1;
   public expire()
   {
     try{
		                 date3="01/06/2020 00:00:00";
						 date1 = formatter.parse(date3);
						int diffInDays = (int) ((date1.getTime() - date.getTime()) / (1000 * 60 * 60 * 24));
						if(diffInDays<=0)
						{
							 JOptionPane.showMessageDialog(null,"                    This Version Of Software is Expaired\nPlease Contact to Devloper \"Pawan Kr Mahto\" For Activation \n                          Mob(+917870743855)"); 
							 System.exit(0); 
						}
	 }
  catch(Exception e)
  {}  
			 
	           
   }
 
public static void main(String argv[])throws IOException
 {
     expire obj =new expire();
 }
}
 