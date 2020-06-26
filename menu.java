import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.io.*;
import java.sql.*;
import javax.swing.event.*;
import java.awt.event.KeyEvent;
public class menu implements  ActionListener,MenuListener
{
  Connection con;
  ResultSet res,res1,rr,res2;
  Statement st,st1;
 JFrame frame=new JFrame();
 JMenuBar menuBar;
 JMenu master1,transection,exit,name,signout,report,aboutus;
 JMenuItem route,flight,schedule,registration,booking,cancle,ticket,flightrpt,canclerpt,todayticketrpt,fixdate,emp;
 int i=0;
 String str1,str2;
 JLabel screen=new JLabel(new ImageIcon(ClassLoader.getSystemResource("icon/13.jpg")));
 public menu()
   {
	    try
		{ 
 
           Thread.sleep(100);
   		   con =DriverManager.getConnection("jdbc:odbc:mydsn");
	       st = con.createStatement();
		   res=st.executeQuery("SELECT temp.lname, temp.logintype FROM temp");
		   if(res.next())
	       {
             do
		      {
				  str1=res.getString(1);
				   str2=res.getString(2);
			}while(res.next());
   }
   }
   catch(Exception e)
   {
	   System.out.println(e);
   }
   new expire();
   frame = new JFrame("FlyingBird AirLine Ticket Reservation System");
  frame.add(screen);
  frame.setLayout(null);
  screen.setBounds(0,0,1366,730);
  new check();
  menuBar = new JMenuBar();
  master1 = new JMenu("Master");
  if(str2.equals("Employee"))
  master1.setEnabled(false);
  flight = new JMenuItem("flight",new ImageIcon(ClassLoader.getSystemResource("icon/flight1.png")));
  schedule = new JMenuItem("Schedule",new ImageIcon(ClassLoader.getSystemResource("icon/schedule.png")));
  route = new JMenuItem("Route",new ImageIcon(ClassLoader.getSystemResource("icon/route.png")));
  registration = new JMenuItem("Registration",new ImageIcon(ClassLoader.getSystemResource("icon/padd.png")));
  master1.add(flight);
  master1.add(schedule);
  master1.add(route);
  master1.add(registration);
  transection = new JMenu("Transaction");
  booking=new JMenuItem("Booking",new ImageIcon(ClassLoader.getSystemResource("icon/f1.png")));
  cancle=new JMenuItem("Canclation",new ImageIcon(ClassLoader.getSystemResource("icon/tcancel.png")));
  ticket=new JMenuItem("Get Ticket",new ImageIcon(ClassLoader.getSystemResource("icon/ticket.png")));
  transection.add(booking);
  transection.add(cancle);
  transection.add(ticket);
  report = new JMenu("Reports");
  flightrpt=new JMenuItem("Flight Report",new ImageIcon(ClassLoader.getSystemResource("icon/r1.png")));
  todayticketrpt=new JMenuItem("Today Ticket Report",new ImageIcon(ClassLoader.getSystemResource("icon/r2.png")));
  fixdate = new JMenuItem("Ticket Report",new ImageIcon(ClassLoader.getSystemResource("icon/r5.png")));
  canclerpt=new JMenuItem("Cancelation Report",new ImageIcon(ClassLoader.getSystemResource("icon/r4.png")));
  emp=new JMenuItem("Employee Report",new ImageIcon(ClassLoader.getSystemResource("icon/person.png")));
  
  aboutus=new JMenu("About Us");
  exit = new JMenu("Exit");
  report.add(flightrpt);
  report.add(todayticketrpt);
  report.add(fixdate);
  report.add(canclerpt);
  report.add(emp);
  name = new JMenu("Welcome : ' "+str1+" '");
  signout = new JMenu("Sign Out");
  menuBar.add(master1);
  menuBar.add(transection);
  menuBar.add(report);
    menuBar.add(aboutus);
  menuBar.add(Box.createGlue());
  menuBar.add(name);
  menuBar.add(signout);
   menuBar.add(exit);
   aboutus.addMenuListener(this);
   exit.addMenuListener(this);
  signout.addMenuListener(this);
  booking.addActionListener(this);
  route.addActionListener(this);
  registration.addActionListener(this);
  schedule.addActionListener(this);
  flight.addActionListener(this);
  cancle.addActionListener(this);
    flightrpt.addActionListener(this);
   todayticketrpt.addActionListener(this);
   fixdate.addActionListener(this);
   canclerpt.addActionListener(this);
    emp.addActionListener(this);
   ticket.addActionListener(this);
  frame.setJMenuBar(menuBar);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setPreferredSize(new Dimension(1366,730));
  frame.pack();
  frame.setLocationRelativeTo(null);
  frame.setVisible(true);
  frame.setIconImage(new ImageIcon("images/f1.png").getImage());
   }
    
 public static void main(String[] args)
 {
  
menu obj= new menu();
   
   
 }
 
public void actionPerformed(ActionEvent ev)
 {
  if(ev.getSource()==booking)
	 new search();
  if(ev.getSource()==route)
	  new route();
    if(ev.getSource()==schedule)
	  new schedule();
  if(ev.getSource()==registration)
	  new user();
  if(ev.getSource()==flight)
	  new flight();
  if(ev.getSource()==cancle)
	  new cancle();
   if(ev.getSource()==ticket)
   {  
	  new Ticket();
	  
   }
  if(ev.getSource()==exit)
	 System.exit(0);
 if(ev.getSource()==canclerpt)
 {
	  new canclereport();
	  JOptionPane.showMessageDialog((Component)null,"Cancelation Report Genrate Successfull\n"+"(Locate on d:/project/Reports/cancel) ","Message",JOptionPane.INFORMATION_MESSAGE);
 }
  if(ev.getSource()==fixdate)
  {
	  new FixDateTicket();
	  
  }
   if(ev.getSource()==flightrpt)
   {
	  new FlightDetails();
	  JOptionPane.showMessageDialog((Component)null,"Flight Reports Genrate Successfull\n"+"(Locate on 'd:/project/Reports/flight) ","Message",JOptionPane.INFORMATION_MESSAGE);
   }
  if(ev.getSource()==todayticketrpt)
  {
	 new TodayTicket();
	 JOptionPane.showMessageDialog((Component)null,"Ticket Reports Genrate Successfull\n"+"(Locate on d:/project/Reports/Today) ","Message",JOptionPane.INFORMATION_MESSAGE);
  }
 if(ev.getSource()==emp)
  {
	 new Employeerpt();
	 JOptionPane.showMessageDialog((Component)null,"Employee Reports Genrate Successfull\n"+"(Locate on d:/project/Reports/Employee) ","Message",JOptionPane.INFORMATION_MESSAGE);
  }
 
 
 
  }
  public void menuSelected(MenuEvent e)
  {
   if(e.getSource()==exit)
   {
	        int cnfb = JOptionPane.YES_NO_OPTION;
			int cnf = JOptionPane.showConfirmDialog(null,"Are You Want to Exit ?","Warning" ,cnfb );
			if(cnf==0)
			  {
               	 System.exit(0); 
			  }
	  
   }
	
  if(e.getSource()==signout)
  {
	        int cnfb = JOptionPane.YES_NO_OPTION;
			int cnf = JOptionPane.showConfirmDialog(null,"Are You Want to Sign Out ?","Warning" ,cnfb );
			if(cnf==0)
			  {
               	 new login();
	             frame.dispose(); 
			  }
	
	
  }
   if(e.getSource()==aboutus)
  {
	        new aboutus();
	
  }
  }
public void menuDeselected(MenuEvent e) {}
public void menuCanceled(MenuEvent e) {}
}
