import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.*;
import net.sourceforge.jdatepicker.*; 
import net.sourceforge.jdatepicker.graphics.*; 
import net.sourceforge.jdatepicker.impl.*; 
import net.sourceforge.jdatepicker.util.*;
import java.text.DateFormat; 
import java.text.SimpleDateFormat;
import java.util.Calendar; 
import java.util.Date;
public class search extends JFrame implements ActionListener//, ItemListener 
{  
   Connection con;
   ResultSet res,res1,rr;
   Statement st,st1;
   public UtilDateModel model;
   public JDatePanelImpl datePanel;
   public JDatePickerImpl datePicker;
   Choice src,dst;
   ButtonGroup ch1=new ButtonGroup();
   JRadioButton s_eco = new JRadioButton("Economy");
   JRadioButton s_exc = new JRadioButton("Business");
   JLabel s_from=new JLabel("From");
   JLabel s_to=new JLabel("To");
   JLabel s_ddate=new JLabel("Depature Date");
   JLabel s_clss=new JLabel("Class");
   JButton s_bsearch=new JButton("FIND FLIGHT",new ImageIcon(ClassLoader.getSystemResource("icon/search.png")));
   JLabel plane=new JLabel(new ImageIcon(ClassLoader.getSystemResource("icon/air.png")));
   JLabel back=new JLabel(new ImageIcon(ClassLoader.getSystemResource("icon/back1.png")));
   public search()
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
		        setLayout(null);
        setSize(1350,680);
     setLocation(10,52);
        setVisible(true);
	    setTitle("Flight Search");
		model = new UtilDateModel();  
        datePanel = new JDatePanelImpl(model);  
        datePicker = new JDatePickerImpl(datePanel);
		show1();
		add(s_from);
		ch1.add(s_eco);
	    ch1.add(s_exc);
		add(datePicker);
		add(src);
    	add(dst);
		add(s_clss);
		add(s_to);
		add(s_ddate);
		add(s_eco);
		add(s_exc);
		add(s_bsearch);
		s_bsearch.setToolTipText("Search Flights");
		add(back);
		add(plane);
		s_eco.setSelected(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true); 
		plane.setBounds(0,0,1366,756);
		back.setBounds(20,20,500,400);
		datePicker.setBounds(200,170,200,26);
	    s_from.setBounds(85,85,50,28);
		src.setBounds(85,115,150,50);
		s_to.setBounds(310,85,50,28);
		dst.setBounds(310,115,150,50);
        s_ddate.setBounds(85,167,150,20);
		s_clss.setBounds(85,217,50,20);
		s_eco.setBounds(85,240,80,20);
		s_exc.setBounds(250,240,80,20);
    	s_bsearch.setBounds(220,330,130,30);
		s_bsearch.addActionListener(this);
      }
   void show1()
   {
     src= new Choice();
	 dst = new Choice();
	 src.add("Please select...");
	 dst.add("Please Select...");
	 try
	 {
		 res=st.executeQuery("select DISTINCT f_src from flight");
		 if(res.next())
          {
           do
            {
             src.add(res.getString(1));
		    }while(res.next());
	      }
		 res=st.executeQuery("SELECT DISTINCT f_dst from flight");
		 if(res.next())
         {
		   do
             {
              dst.add(res.getString(1));
        	 }while(res.next());
		 }
	 } 
	 catch(Exception e)
	 {
		System.out.println(e);
     }	 
   }
 void save()
	{
	  try
	   {
		s_eco.setActionCommand("Economy");
		s_exc.setActionCommand("Executive");
		String tfrom=src.getSelectedItem();
		String tto=dst.getSelectedItem();
		String tcls=ch1.getSelection().getActionCommand();
		Date selectedDate =(Date) datePicker.getModel().getValue();
        DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
	    String reportDate = df.format(selectedDate);
		if(tfrom.equals("Please select...")||(tto.equals("Please select..."))||(reportDate.equals("")))
		{
			JOptionPane.showMessageDialog(null,"Not Enought Data", "Alert",JOptionPane.ERROR_MESSAGE);
		}
		
       else
		{
			st.executeUpdate("update temp set frm='"+tfrom+"',to1='"+tto+"',doj='"+reportDate+"',class='"+tcls+"'");
		    this.dispose();
		    new booking();
	    }
	   }
	   catch(Exception ex)
	    {
			
			System.out.println(ex);
			//JOptionPane.showMessageDialog(null,"Not Enought Data", "Alert",JOptionPane.ERROR_MESSAGE);
		}
    }
  public void actionPerformed(ActionEvent e)
    {
     if(e.getSource()==s_bsearch)
	  {
         save();
	     
	  }
	  
  }
public static void main(String argv[])throws IOException
 {
      search obj=new search();
 }
}
 