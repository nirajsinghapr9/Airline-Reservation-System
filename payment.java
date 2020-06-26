import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;
import javax.swing.*;
import java.text.*;
import java.util.Date;
import java.util.*;
import java.sql.*;
import javax.swing.table.*;
import javax.swing.event.*;
public class payment extends JFrame implements ActionListener 
{
  Connection con,con1;
  ResultSet res,res1;
  Statement st,st1;
  JLabel Head = new JLabel("Payment Form");
  JLabel bfare = new JLabel("Base Fare");
  JLabel afuel = new JLabel("Airline Fuel Charge");
  JLabel passanger = new JLabel("Passenger Services Fee");
  JLabel userdvp = new JLabel("User Devlopment Fee");
  JLabel airport = new JLabel("Airport Devlopment Fee");
  JLabel services = new JLabel("Service Tax");
  JLabel backup = new JLabel("FARE SUMMARY");
  JLabel totalfare = new JLabel("Total Fare");
  JLabel fareword = new JLabel("");
  JLabel paymentmode = new JLabel("PAYMENT MODE");
  JTextField txbfare=new JTextField("Rs. ");
  JTextField txafuel=new JTextField("Rs. 5,800");
  JTextField txpassanger=new JTextField("Rs. 770");
  JTextField txuserdvp = new JTextField("Rs. 1,292");
  JTextField txairport=new JTextField("Rs. 850");
  JTextField txservices = new JTextField("Rs. 984");
  JTextField txtotalfare = new JTextField("Rs. ");
  JLabel checkno = new JLabel("Check No");
  JLabel cbname = new JLabel("Bank Name");
  JLabel cbranch = new JLabel("Branch Name");
  JLabel cardno = new JLabel("Card No");
  JLabel cardhname = new JLabel("Card Holder Name");
  JLabel cardbank = new JLabel("Bank Name");
  JTextField tcheckno = new JTextField("");
  JTextField tcbname = new JTextField("");
  JTextField tcbranch = new JTextField("");
  JTextField tcardno = new JTextField("");
  JTextField tcardhname = new JTextField("");
  JTextField tcardbank = new JTextField("");
  JLabel back1 =new JLabel(new ImageIcon(ClassLoader.getSystemResource("icon/atm21.jpg")));
  JButton ptpay = new JButton("Procces to Pay",new ImageIcon(ClassLoader.getSystemResource("icon/proccess.png")));
  JButton pay = new JButton("Pay",new ImageIcon(ClassLoader.getSystemResource("icon/pay.png")));
  Choice p_bid=new Choice();
  ButtonGroup ch=new ButtonGroup();
  JRadioButton bycase = new JRadioButton("By Cash",new ImageIcon(ClassLoader.getSystemResource("icon/cash.png")));
  JRadioButton bycheque = new JRadioButton("By Cheque",new ImageIcon(ClassLoader.getSystemResource("icon/cheque.png")));
  JRadioButton bycard = new JRadioButton("By Card",new ImageIcon(ClassLoader.getSystemResource("icon/atm.png")));
  Date date = new Date();
  DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
  String tempbid,tempfare,temp;
  int ctr;
  Double t;
  int t1=0;
  Font myFont = new Font("Lucida Fax", Font.BOLD,14);
  Font myFont1 = new Font("Serif", Font.BOLD,18);
  public payment()
   {
	 new expire();
	 try
      {
	    con =DriverManager.getConnection("jdbc:odbc:mydsn");
	    st = con.createStatement();
		con1 =DriverManager.getConnection("jdbc:odbc:mydsn");
	    st1 = con1.createStatement();
		 
		 res=st.executeQuery("select bid from temp");
		 if(res.next())
          {
           do
            {
             tempbid=res.getString(1);
		    }while(res.next());
	      }
     
		  res1=st1.executeQuery("select SUM(b_fare) from booking where b_id= '"+tempbid+"'"); //and b_status= 'waiting'");
		  if(res1.next())
          {
           do
            {
             tempfare=res1.getString(1);
			   }while(res1.next());
	      }
		  
		  
		txbfare.setText("Rs. "+tempfare);
		 	  t = Double.parseDouble(tempfare);
			  tempfare = ((Double.parseDouble(tempfare)*100)/100)+"";
		    		    t=t+9696.00;
			t=(t*100)/100;

		  txtotalfare.setText("Rs. "+t);
		   
      }
	 catch(Exception e)
	  {
	    JOptionPane.showMessageDialog((Component)null,"Allready Payment Done","Message",JOptionPane.INFORMATION_MESSAGE);
	  }  
	    word();
	 
    	setLayout(null);
        setSize(1350,680);
		setLocation(10,52);
        setVisible(true);
	    setTitle("Payment");
		ch.add(bycard);
	    ch.add(bycase);
		ch.add(bycheque);
		Head.setBounds(480,30,400,30);
		Head.setFont(new Font("Serif",Font.BOLD,28));
		Head.setForeground(Color.RED);
		bycard.setVisible(false);
	    bycase.setVisible(false);
	    bycheque.setVisible(false);
		backup.setFont(myFont1);
		backup.setForeground(Color.BLUE);
		paymentmode.setFont(myFont1);
		paymentmode.setForeground(Color.BLUE);
		fareword.setFont(new Font("Courier",Font.ITALIC,15));
		fareword.setForeground(Color.BLUE);
		ptpay.setFont(myFont1);
		ptpay.setForeground(Color.RED);
		pay.setFont(myFont1);
		pay.setForeground(Color.RED);
		bycard.setFont(myFont);
		bycase.setFont(myFont);
		bycheque.setFont(myFont);
		add(backup);
		add(bfare);
		add(txbfare);
		txbfare.setHorizontalAlignment(JTextField.RIGHT);
		txbfare.setEditable(false);
		add(afuel);
		add(txafuel);
		txafuel.setEditable(false);
		txafuel.setHorizontalAlignment(JTextField.RIGHT);
		add(passanger);
		add(txpassanger);
		txpassanger.setEditable(false);
		txpassanger.setHorizontalAlignment(JTextField.RIGHT);
		add(userdvp);
		add(txuserdvp);
		txuserdvp.setEditable(false);
		txuserdvp.setHorizontalAlignment(JTextField.RIGHT);
		add(airport);
		add(txairport);
		txairport.setEditable(false);
		txairport.setHorizontalAlignment(JTextField.RIGHT);
		add(services);
		add(txservices);
		txservices.setEditable(false);
		txservices.setHorizontalAlignment(JTextField.RIGHT);
		add(totalfare);
		add(txtotalfare);
		txtotalfare.setEditable(false);
		txtotalfare.setHorizontalAlignment(JTextField.RIGHT);
		add(Head);
		add(fareword);
		add(paymentmode);
		add(bycard);
		add(bycase);
		add(bycheque);
		add(cardno);
		add(tcardno);
		add(cardbank);
		add(tcardbank);
		add(cardhname);
		add(tcardhname);
		add(checkno);
		add(tcheckno);
		add(cbname);
		add(tcbname);
		add(cbranch);
		add(tcbranch);
		add(pay);
	    add(ptpay);
		add(back1);
		back1.setBounds(0,0,1350,680);
	    backup.setBounds(130,100,200,30);
		bfare.setBounds(130,160,100,20);
		txbfare.setBounds(290,155,100,30);
		afuel.setBounds(130,195,200,20);
		txafuel.setBounds(290,195,100,30);
		passanger.setBounds(130,235,200,20);
		txpassanger.setBounds(290,235,100,30);
		userdvp.setBounds(130,275,200,20);
		txuserdvp.setBounds(290,275,100,30);
		airport.setBounds(130,315,200,20);
		txairport.setBounds(290,315,100,30);
		services.setBounds(130,355,200,20);
		txservices.setBounds(290,355,100,30);
		totalfare.setBounds(130,395,200,20);
		txtotalfare.setBounds(290,395,100,30);
		fareword.setBounds(130,430,700,25);
		ptpay.setBounds(160,470,200,30);
		bycase.setBounds(740,160,120,20);
		bycard.setBounds(860,160,120,20);
		bycheque.setBounds(980,160,150,20);
		pay.setBounds(860,420,100,30);
		paymentmode.setBounds(740,100,300,30);
		pay.setVisible(false);
		paymentmode.setVisible(false);
		bycase.addActionListener(this);
		bycard.addActionListener(this);
		bycheque.addActionListener(this);
		ptpay.addActionListener(this);
		pay.addActionListener(this);
	 
     }
   public void actionPerformed(ActionEvent e)
     {
       if(e.getSource()==ptpay)
	   {
		  bycard.setVisible(true);
		  bycase.setVisible(true);
		  bycheque.setVisible(true);
		  paymentmode.setVisible(true);
		  ptpay.setEnabled(false);
		 
		  new1();
	   }
	   if(e.getSource()==pay)
	   {
		   save();
		   pay.setEnabled(false);
	   }   
	   if(e.getSource()==bycase)
	   {
		 visibleoff();
		  pay.setVisible(true);
	   }
	   if(e.getSource()==bycard)
	   {
			 pay.setVisible(true);
		    cardon();
	   }
	    if(e.getSource()==bycheque)
		{
		  chequeon();
		   pay.setVisible(true);
		}
     }
	     
	void word()
	 {
     int n,r,k=0,i,ch;
 	 String str="",tmp="";
 	 int arr[]=new int[20];
  	 String ones[]={"","One","Two","Three","Four","Five","Six","Seven","Eight","Nine"};
     String tens[]={"","Ten","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};
 	 String teens[]={"Ten","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"}; 
 	 temp= txtotalfare.getText().substring(4,txtotalfare.getText().length() - 2);
	 n=Integer.parseInt(temp);
	 n=(n*100)/100;
	 while(n>0)
   	 {
   		      r=n%10;
    		  arr[k]=r;
    		  k++;
    		  n=n/10;
   	  }
   		for(i=0;i<k;i++)
   		 {
   		   ch=arr[i];
   		   switch(i)
     		    {
      			  case 0:    
                          str=ones[ch];
                          break;
                          case 1:
                         	 int ch1=arr[0];
                          	 if((ch==1)&&(ch1==0))
                          	 str="Ten";
                         	 else if ((ch==1)&&(ch1!=0))
                         	 {
                              		 tmp="";
                               		str=teens[ch1];
                          	 }
                     		else
                        	 str=tens[ch];
                        	 break;
                         case 2:
                          	if(ch==0)
                         	str="";
                        	 else
                         	 str=ones[ch]+" Hundred";
                        	 break;
        		case 3:
                        	if(ch==0)
                       		str="";
                       		else
                      	        str=ones[ch]+" Thousand";
                       		break;
         		case 4:
                       		 if(ch==0)
                       		 str="";
                        	 else if((arr[3]==0)&&(ch!=0))
                       		 str=tens[ch]+" Thousand";
                       		 else
                        	 str=tens[ch];
                       		 break;
                       case 5:
                        	if(ch==0)
                        	 str="";
                       		 else
                        	 str=ones[ch]+" Lakhs";
                        	 break;
                        case 6:
                          	if((arr[5]==0)&&(ch!=1))
                         	 str=tens[ch]+" Lakhs";
                         	 else if((arr[5]==0)&&(ch==1))
                         	str=tens[ch]+" Lakhs";
                         	else
                                str=tens[ch];
                          	break;
             	  }
                 		 tmp=str+" "+tmp;
       	      }
          			fareword.setText("Rupees "+tmp+" Only");
	}	 
	   void new1()
  {
	 try
      {
	   
		res=st.executeQuery("Select pid from id");
		if(res.next())
         {
           do
		     {
               ctr=Integer.parseInt(res.getString(1));
	          }while(res.next());
			  
    	 }
	  }
      catch(Exception e)
	  {} 
  }
	 void save()
	 {
		     bycheque.setActionCommand("Cheque");
			 bycard.setActionCommand("Card");
			 bycase.setActionCommand("Cash");
             String value=ch.getSelection().getActionCommand();
			 try
			 {
			 if(value.equals("Cash"))
			 {
		   
			 res=st.executeQuery("select * from payment");
		     String str= "insert into payment(p_id,p_bid,p_tfare,p_type,p_cno,p_bank,p_branch,p_date,p_rsword)values(?,?,?,?,?,?,?,?,?)";
		     PreparedStatement ps = con.prepareStatement(str);
		     ps.setString(1,"P"+ctr);
		     ps.setString(2,tempbid);
             ps.setString(3,txtotalfare.getText().substring(4));
		     ps.setString(4,"case");
		     ps.setString(5,"*");
             ps.setString(6,"*");
		     ps.setString(7,"*");
			 ps.setString(8,df.format(date));
			 ps.setString(9,fareword.getText());
		     ps.executeUpdate();
			  ctr=ctr+1;
		     st.executeUpdate("update id set pid='"+ctr+"'");
			 st.executeUpdate("update booking set b_status='CNF'");
			 JOptionPane.showMessageDialog(null,"Payment Successfull","Payment Status",JOptionPane.INFORMATION_MESSAGE);
			 JOptionPane.showMessageDialog(null,"Your Booking Id : "+tempbid+"","Booking Id",JOptionPane.INFORMATION_MESSAGE);
			 }
			 if(value.equals("Cheque"))
			 {
		      if(tcheckno.getText().equals("")||tcbranch.getText().equals("")||tcbname.getText().equals(""))
			  {
				   JOptionPane.showMessageDialog(null,"Not Enough Details!!!");
			  }
		      else
			  {
			 res=st.executeQuery("select * from payment");
		     String str= "insert into payment(p_id,p_bid,p_tfare,p_type,p_cno,p_bank,p_branch,p_date,p_rsword)values(?,?,?,?,?,?,?,?,?)";
		     PreparedStatement ps = con.prepareStatement(str);
		     ps.setString(1,"P"+ctr);
		     ps.setString(2,tempbid);
             ps.setString(3,txtotalfare.getText().substring(4));
		     ps.setString(4,"cheque");
		     ps.setString(5,tcheckno.getText());
             ps.setString(6,tcbname.getText());
		     ps.setString(7,tcbranch.getText());
			  ps.setString(8,df.format(date));
			  ps.setString(9,fareword.getText());
		     ps.executeUpdate();
			  ctr=ctr+1;
		     st.executeUpdate("update id set pid='"+ctr+"'");
			 st.executeUpdate("update booking set b_status='CNF'");
			 JOptionPane.showMessageDialog(null,"Payment Successfull","Payment Status",JOptionPane.INFORMATION_MESSAGE);
			 JOptionPane.showMessageDialog(null,"Your Booking Id : "+tempbid+"","Booking Id",JOptionPane.INFORMATION_MESSAGE);
			 }
			 }
			 if(value.equals("Card"))
			 {
             if(tcardbank.getText().equals("")||tcardno.getText().equals("")||tcardhname.getText().equals(""))
			 {
				  JOptionPane.showMessageDialog(null,"Not Enough Details!!!");
			 }
			 else
			 {
			 res=st.executeQuery("select * from payment");
		     String str= "insert into payment(p_id,p_bid,p_tfare,p_type,p_cno,p_bank,p_branch,p_date,p_rsword)values(?,?,?,?,?,?,?,?,?)";
		     PreparedStatement ps = con.prepareStatement(str);
		     ps.setString(1,"P"+ctr);
		     ps.setString(2,tempbid);
             ps.setString(3,txtotalfare.getText().substring(4));
		     ps.setString(4,"card");
		     ps.setString(5,tcardno.getText());
             ps.setString(6,tcardhname.getText());
		     ps.setString(7,tcardbank.getText());
			  ps.setString(8,df.format(date));
			  ps.setString(9,fareword.getText());
		     ps.executeUpdate();
			  ctr=ctr+1;
		     st.executeUpdate("update id set pid='"+ctr+"'");
			 st.executeUpdate("update booking set b_status='CNF'");
			 JOptionPane.showMessageDialog(null,"Payment Successfull","Payment Status",JOptionPane.INFORMATION_MESSAGE);
			 JOptionPane.showMessageDialog(null,"Your Booking Id : "+tempbid+"","Booking Id",JOptionPane.INFORMATION_MESSAGE);
			 }
			 }
			
			 
		 }
	
	 catch(Exception e)
	 {
		 System.out.println(e);
	 }
}

  void cardon()
  {
	  cardno.setBounds(760,235,150,20);
	  tcardno.setBounds(900,235,160,30);
	  cardhname.setBounds(760,275,150,20);
	  tcardhname.setBounds(900,275,160,30);
	 cardbank.setBounds(760,315,150,20);
	  tcardbank.setBounds(900,315,160,30);
	   checkno.setVisible(false);
	  tcheckno.setVisible(false);
	  cbname.setVisible(false);
	  tcbname.setVisible(false);
	  cbranch.setVisible(false);
	  tcbranch.setVisible(false);
	   cardno.setVisible(true);
	  tcardno.setVisible(true);
	  cardhname.setVisible(true);
	  tcardhname.setVisible(true);
	  cardbank.setVisible(true);
	  tcardbank.setVisible(true);
  }  
	 
  void chequeon()
  {
	  checkno.setBounds(760,235,150,20);
	  tcheckno.setBounds(900,235,160,30);
	  cbname.setBounds(760,275,150,20);
	  tcbname.setBounds(900,275,160,30);
	  cbranch.setBounds(760,315,150,20);
	  tcbranch.setBounds(900,315,160,30);
      checkno.setVisible(true);
	  tcheckno.setVisible(true);
	  cbname.setVisible(true);
	  tcbname.setVisible(true);
	  cbranch.setVisible(true);
	  tcbranch.setVisible(true);
	  cardno.setVisible(false);
	  tcardno.setVisible(false);
	  cardhname.setVisible(false);
	  tcardhname.setVisible(false);
	  cardbank.setVisible(false);
	  tcardbank.setVisible(false);
  }  
  void visibleoff()
  {
	  
	  checkno.setVisible(false);
	  tcheckno.setVisible(false);
	  cbname.setVisible(false);
	  tcbname.setVisible(false);
	  cbranch.setVisible(false);
	  tcbranch.setVisible(false);
	  cardno.setVisible(false);
	  tcardno.setVisible(false);
	  cardhname.setVisible(false);
	  tcardhname.setVisible(false);
	  cardbank.setVisible(false);
	  tcardbank.setVisible(false);
  }  
  
  public static void main(String argv[])throws IOException
     {
        payment obj=new payment();
     }
}