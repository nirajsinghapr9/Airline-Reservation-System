import java.awt.*;
import java.lang.System.*;
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
public class cancle extends JFrame implements ActionListener// ItemListener
{  
   Connection con;
   ResultSet res,res1,rr;
   Statement st,st1;
   double total;
   String printing;
   JLabel Head = new JLabel("Cancelation Form");
   ButtonGroup ch=new ButtonGroup();
   JLabel bid=new JLabel("Booking Id");
   JLabel binfo=new JLabel("Booking Information");
   JTextField btid=new JTextField();
   JLabel c_fno = new JLabel("Flight No");
   JLabel c_fname = new JLabel("Flight Name");
   JLabel c_source = new JLabel("Source");
   JLabel c_dst = new JLabel("Destination");
   JLabel c_payamt = new JLabel("total Fare");
   JLabel c_amttype = new JLabel("Payment Type");
   JLabel reason = new JLabel("Cancletion Reason");
   JLabel refamt = new JLabel("Refounded Amount");
   JLabel doj = new JLabel("Travel Date");
   JLabel back1 =new JLabel(new ImageIcon(ClassLoader.getSystemResource("icon/cancle.jpg")));
   JRadioButton r1 = new JRadioButton("Change Of Mind");
   JRadioButton r2 = new JRadioButton("Unavailablity Travel Document");
   JRadioButton r3 = new JRadioButton("Work Releated Reason");
   JRadioButton r4 = new JRadioButton("Family Reason");
   JRadioButton r5 = new JRadioButton("Other");
   JTextField tfno = new JTextField();
   JTextField tfname = new JTextField();
   JTextField tsource = new JTextField();
   JTextField tdst = new JTextField();
   JTextField tpayamt = new JTextField();
   JTextField tamttype = new JTextField();
   JTextField trefamt = new JTextField();
   JTextField tdoj = new JTextField();
   JLabel fareword = new JLabel("");
   JCheckBox cnf = new JCheckBox("Yes, I have No problem for This Deduction Fee");
   JButton search=new JButton("Search",new ImageIcon(ClassLoader.getSystemResource("icon/search.png")));
   JButton refund=new JButton("Refund",new ImageIcon(ClassLoader.getSystemResource("icon/refund.png")));
   Date date = new Date();
   Date date1 = new Date();
   Date date2 = new Date();
   SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
   DateFormat df = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
   String dateInString,f1="";
  
   Font myFont1 = new Font("Serif", Font.BOLD,18);
   public cancle()
   {
     try
         {
	       con =DriverManager.getConnection("jdbc:odbc:mydsn");
	       st = con.createStatement();
         }
	    catch(Exception e)
	     {
	    	 System.out.println(e);
	     } 
		 new expire();
		setLayout(null);
        setSize(1350,680);
		setLocation(10,52);
        setVisible(true);
	    setTitle("Flight Search");
		fareword.setFont(new Font("Courier",Font.ITALIC,15));
		fareword.setForeground(Color.BLUE);
		ch.add(r1);
		ch.add(r2);
		ch.add(r3);
		ch.add(r4);
		ch.add(r5);
		add(binfo);
		add(c_fno);
		add(c_fname);
		add(tfno);
		Head.setBounds(480,30,400,30);
		Head.setFont(new Font("Serif",Font.BOLD,28));
		refund.setForeground(Color.RED);
		refund.setFont(new Font("Serif",Font.BOLD,15));
		Head.setForeground(Color.RED);
		binfo.setFont(myFont1);
		binfo.setForeground(Color.BLUE);
		reason.setFont(myFont1);
		reason.setForeground(Color.BLUE);
		cnf.setFont(new Font("Courier",Font.BOLD,15));
		tfno.setEditable(false);
		tfname.setEditable(false);
		tsource.setEditable(false);
		tdoj.setEditable(false);
		tpayamt.setEditable(false);
		tdst.setEditable(false);
		tamttype.setEditable(false);
		trefamt.setEditable(false);
		add(tfname);
		add(Head);
		add(c_source);
		add(tsource);
		add(c_dst);
		add(tdst);
		add(doj);
		add(tdoj);
		add(c_payamt);
		add(tpayamt);
		add(c_amttype);
		add(tamttype);
		add(reason);
		add(r1);
		add(r2);
		add(r3);
		add(r4);
		add(r5);
		add(cnf);
		add(refamt);
		add(trefamt);
		add(refund);
		add(fareword);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fareword.setBounds(130,430,500,25);
        binfo.setBounds(130,100,200,30);
		c_fno.setBounds(130,160,100,20);
	    tfno.setBounds(260,160,160,30);
		c_fname.setBounds(130,195,100,20);
		tfname.setBounds(260,195,160,30);
		c_source.setBounds(130,235,100,20);
		tsource.setBounds(260,235,160,30);
		c_dst.setBounds(130,275,100,20);
		tdst.setBounds(260,275,160,30);
		c_payamt.setBounds(130,315,100,20);
		tpayamt.setBounds(260,315,160,30);
		c_amttype.setBounds(130,355,100,20);
	    tamttype.setBounds(260,355,160,30);
		doj.setBounds(130,395,100,20);
		tdoj.setBounds(260,395,160,30);
		reason.setBounds(680,100,300,30);
		r1.setBounds(700,160,200,20);
        r5.setSelected(true);
		r2.setBounds(700,195,200,20);
		r3.setBounds(700,235,200,20);
		r4.setBounds(700,275,200,20);
		r5.setBounds(700,315,200,20);
		refamt.setBounds(680,365,120,20);
		trefamt.setBounds(810,360,90,30);
		cnf.setBounds(680,405,450,20);
		refund.setBounds(720,445,120,30);
		add(back1);
		back1.setBounds(0,0,1350,680);
		search.addActionListener(this);
		refund.addActionListener(this);
        cnf.addItemListener(new ItemListener()
	    {
         public void itemStateChanged(ItemEvent e)
		  {         
             if(e.getStateChange()==1)
				 f1="cnf";
			 else
				 f1="";
          }           
      });
		 clr();
		 show1();
      }
  
  public void actionPerformed(ActionEvent e)
    {
      if(e.getSource()==refund)
	  {
             r1.setActionCommand("Change Of Mind");
			 r2.setActionCommand("Unavailablity Travel Document");
			 r3.setActionCommand("Work Releated Reason");
			 r4.setActionCommand("Family Reason");
			 r5.setActionCommand("other");
             String value=ch.getSelection().getActionCommand(); 
             try
			 {
			 if(f1.equals(""))
               JOptionPane.showMessageDialog(null,"Please select Conform","Alert",JOptionPane.WARNING_MESSAGE);
			 else if(f1.equals("cnf"))
             {
               res=st.executeQuery("select * from cancle");
              String str= "insert into cancle(bid,refund,reason)values(?,?,?)";
		     PreparedStatement ps = con.prepareStatement(str);
		     ps.setString(1,btid.getText());
		     ps.setString(2,printing);
			 ps.setString(3,value);	
             ps.executeUpdate();
             st.executeUpdate("DELETE from booking where b_id='"+printing+"'");
			 JOptionPane.showMessageDialog (this, "Cancelation SuccessFull","Cancelation", JOptionPane.PLAIN_MESSAGE);
	       this.dispose();
         	}
           
		    }

			catch(Exception f)
			{
             
            }
	  }
	  
  }
void clr()
{
  tfno.setText("");
  tfname.setText("");
  tsource.setText("");
  tdst.setText("");
  tpayamt.setText("");
  tamttype.setText("");
  trefamt.setText("");
}
  void show1()
  {
	  try
	  {
	     	printing = JOptionPane.showInputDialog (this, "Enter Booking ID For Cancelation.\n" +
				"(Tip: First Letter is Capital)", "FlyingBird - Cancelation Ticket", JOptionPane.PLAIN_MESSAGE);
				if (printing == null) 
				{ 
			this.dispose();
				}
				if (printing.equals ("")) {
					JOptionPane.showMessageDialog (this, "Provide Booking ID for Canceletion.",
						 "FlyingBird - EmptyField", JOptionPane.PLAIN_MESSAGE);
					
				}
	  else
	  {
		  	  
	   res=st.executeQuery("SELECT  booking.b_fno, booking.b_flight, flight.f_src, flight.f_dst, payment.p_tfare, payment.p_type,booking.b_doj FROM (booking INNER JOIN payment ON booking.b_id = payment.p_bid) INNER JOIN flight ON (booking.b_flight = flight.f_name) AND (booking.b_fno = flight.f_no) where booking.b_id='"+printing+"'");
	   if(res.next())
          {
           do
            {
       tfno.setText(res.getString(1));
	  tfname.setText(res.getString(2));
	  tsource.setText(res.getString(3));
	  tdst.setText(res.getString(4));
	  tpayamt.setText(res.getString(5));
	  tamttype.setText(res.getString(6));
	  tdoj.setText(res.getString(7));
	   dateInString = tdoj.getText()+" 00:00:00";
	  date1 = formatter.parse(dateInString);
	  int diffInDays = (int) ((date1.getTime() - date.getTime()) / (1000 * 60 * 60 * 24));
	  if(diffInDays>=0)
	  	 trefamt.setText("0.0");
      if(diffInDays<=7&&diffInDays>=1) 
	  	 trefamt.setText(""+((Double.parseDouble(tpayamt.getText())*40)/100));	
	  if(diffInDays>=8&&diffInDays>7)
	  	  trefamt.setText(((Double.parseDouble(tpayamt.getText())*50)/100)+"");  
      if(diffInDays>=15)
	  	trefamt.setText(((Double.parseDouble(tpayamt.getText())*60)/100)+""); 
      }while(res.next());
	}
	else
		JOptionPane.showMessageDialog (this, "Incorrect Booking ID ,Please Check and Try Again","FlyingBird - EmptyField", JOptionPane.PLAIN_MESSAGE);
	  
	  }
	  }
	  catch(Exception e)
	  {
		 System.out.println(e); 
	  }

  }
public static void main(String argv[])throws IOException
 {
      cancle obj =new cancle();
 }
}
 