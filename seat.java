import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.text.*;
import java.util.Date;
public class seat extends JFrame implements ActionListener, KeyListener
{
	Connection con;
	ResultSet res,res1,rr;
	Statement st,st1;
	int ctr;
	JLabel plane=new JLabel(new ImageIcon(ClassLoader.getSystemResource("icon/plane.png")));
	JLabel info=new JLabel(new ImageIcon(ClassLoader.getSystemResource("icon/info.png")));		
	JButton BA[]=new JButton[6];
	JButton BC[]=new JButton[6];
	JButton BD[]=new JButton[6];
	JButton BG[]=new JButton[6];
	JButton BH[]=new JButton[6];
	JButton BK[]=new JButton[6];
	JButton EA[]=new JButton[30];
	JButton EB[]=new JButton[30];
	JButton EC[]=new JButton[30];
	JButton ED[]=new JButton[30];
	JButton EE[]=new JButton[30];
	JButton EF[]=new JButton[30];
	JLabel info1 = new  JLabel("* Please Enter Name as Mentioned on\n Your Goverment ID Proof ");
	JLabel info2 = new JLabel("* Your Email and Mobile Number will be used For Flight related Communication");
	JLabel sno = new JLabel("Seat No");
	JLabel sfno = new JLabel("Flight No");
	JLabel sfname = new JLabel("Fligh Name");
	JLabel sto = new JLabel("To");
	JLabel sfrom = new JLabel("From");
	JLabel sclass = new JLabel("Class");
	JLabel fname = new JLabel("First Name");
	JLabel lname = new JLabel("Last Name");
	JLabel email = new JLabel("Email");
	JLabel gender = new JLabel("Gender");
	JLabel lmeal = new JLabel("Meal Type");
	JLabel age = new JLabel("Age");
	JLabel fare = new JLabel("Fare");
	JLabel total = new JLabel("Total Fare");
	JTextField ttotal = new JTextField("");
	JTextField tfare = new JTextField("");
	JTextField tage = new JTextField("");
	JTextField tsno = new JTextField("");
	JTextField tfno = new JTextField("");
	JTextField tflight = new JTextField("");
	JTextField tto = new JTextField("");
	JTextField tfrom = new JTextField("");
	JTextField tclass = new JTextField("");
	JLabel dob = new JLabel("Date of Birth");
	JLabel mob = new JLabel("Mob No");
	JTextField tmob = new JTextField();
	JTextField tfname = new JTextField();
	JTextField tlname = new JTextField();
	JTextField temail = new JTextField();
	JRadioButton male = new JRadioButton("Male");
	JRadioButton female = new JRadioButton("Female");
	ButtonGroup ch1=new ButtonGroup();
	Choice meal = new Choice();
	JButton add1=new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/padd.png")));
	JButton remove=new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/premove.png")));
	JButton book=new JButton("Pay",new ImageIcon(ClassLoader.getSystemResource("icon/money.png")));
	
	JButton back = new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/back3.png")));
	
	JTable table = new JTable()
	{
	    public boolean isCellEditable(int rowIndex,int colIndex)
	     {
	       return false;
	      }
	};
  DefaultTableModel model = new DefaultTableModel(new String[]{"Seat No", "First Name","Last Name", "Gender","age", "Meal","fare"},0);
  JScrollPane jsp;
  Date date = new Date();
   Date date1 = new Date();
   String tempdate;
  DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
  	int s=1;
  public seat()
   {
	    new expire();
	
		excseat();
		ecoseat();
		 try
         {
	       con =DriverManager.getConnection("jdbc:odbc:mydsn");
	       st = con.createStatement();
		   st1 = con.createStatement();

		   res=st.executeQuery("SELECT temp.fno, temp.fname, temp.frm, temp.to1, temp.class, temp.fare,temp.doj FROM temp");
		  if(res.next())
	       {
 	         do
		       {
			      tfno.setText(res.getString(1));
				  tflight.setText(res.getString(2));
				  tfrom.setText(res.getString(3));
				  tto.setText(res.getString(4));
				  tclass.setText(res.getString(5));
				  tfare.setText(res.getString(6));
				  tempdate=res.getString(7);
				}while(res.next());
          }
         if(tclass.getText().equals("Economy"))
		 {
		    ecovisible();
		 }
	     if(tclass.getText().equals("Executive"))
		 {
		   System.out.println(tclass.getText());
		   excuvisible();
		 }
	      check();
        }
	    catch(Exception e)
	     {
	    	 System.out.println(e);
	     }
		setLayout(null);
		new expire();
		model.setRowCount(0);
		setSize(1350,680);
        setLocation(10,52);
		getContentPane().setBackground(new Color(252,252,252));
        setVisible(true);
	    setTitle("Seat Booking");
		male.setSelected(true);
       	tsno.setEditable(false);
		ttotal.setEditable(false);
     	meal.add("Vegetrain Jain Meal");
		meal.add("Koshar Meal");
		meal.add("Baby meal");
		meal.add("Hindu veg Meal");
		meal.add("hindu Non veg meal");
		ch1.add(male);
		ch1.add(female);
		add(info);
		add(back);
		add(info1);
		add(plane);
		add(male);
		add(female);
		add1.setToolTipText("Add Passanger");
		remove.setToolTipText("Remove Passanger");
		book.setToolTipText("Go For Payment");
		add(add1);
		add(remove);
		add(lmeal);
		add(meal);
		add(gender);
		add(fname);
		add(lname);
		add(email);
		add(tfname);
		add(tlname);
		add(temail);
		add(sno);
		add(tsno);
		add(table);
		
		tflight.setEditable(false);
		tfno.setEditable(false);
		tfrom.setEditable(false);
		tto.setEditable(false);
		tclass.setEditable(false);
		tfare.setEditable(false);
		add(tflight);
		add(tfno);
		add(sto);
		add(tto);
		add(sfname);
		add(tfrom);
		add(sfrom);
		add(sclass);
		add(tclass);
		add(sfno);
		add(tfno);
		add(book);
		add(tfare);
		
		add(age);
		add(tage);
		add(fare);
		add(tfare);
		add(total);
		add(ttotal);
		add(mob);
		add(tmob);
		add(info2);
		info2.setBounds(670,625,700,20);
		sfno.setBounds(300,320,50,30);
		tfno.setBounds(380,320,70,30);
		sfname.setBounds(300,360,80,30);
	    tflight.setBounds(380,360,130,30);
		sto.setBounds(300,400,50,30);
		tto.setBounds(380,400,130,30);
		sfrom.setBounds(300,440,70,30);
		tfrom.setBounds(380,440,130,30);
		sclass.setBounds(300,480,70,30);
		tclass.setBounds(380,480,100,30);
		fare.setBounds(300,520,50,30);
		tfare.setBounds(380,520,100,30);
        back.setBounds(4,4,32,32);
		
		info1.setBounds(650,300,600,20);
		plane.setBounds(10,10,1336,300);
		info.setBounds(21,315,260,169);
		sno.setBounds(650,320,70,30);
		tsno.setBounds(730,320,70,30);
		fname.setBounds(650,360,70,30);
		tfname.setBounds(730,360,100,30);
		lname.setBounds(650,400,70,30);
		tlname.setBounds(730,400,100,30);
		gender.setBounds(885,320,70,30);
		male.setBounds(935,320,60,30);
		female.setBounds(1000,320,70,30);
		email.setBounds(670,595,50,30);
		temail.setBounds(720,595,200,30);
		age.setBounds(885,400,70,30);
		tage.setBounds(935,405,50,30);
		lmeal.setBounds(885,360,70,30);
		meal.setBounds(965,365,140,35);
		mob.setBounds(940,595,50,30);
		tmob.setBounds(995,595,120,30);
		total.setBounds(1020,400,70,30);
		ttotal.setBounds(1100,405,100,30);
		add1.setBounds(1220,500,28,28);
		remove.setBounds(1288,500,28,28);
		book.setBounds(1220,550,100,30);
		
		jsp = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       
       table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);    
     table.setFillsViewportHeight(true);
     table.setShowGrid(true);
     table.setShowVerticalLines(true);
     table.setGridColor(new Color(0,0,0));
     JTableHeader header1 = table.getTableHeader();
     table.setBackground(new Color(253,255,149));
     table.setEnabled(true);
     header1.setFont(new Font("Courier", Font.CENTER_BASELINE, 12));
     header1.setBackground(Color.black);
     header1.setForeground(new Color(0,128,255));
	    add(jsp);
		setVisible(true);
		jsp.setVisible(true);
		jsp.setBounds(550,440,650,150);
		
				for(int i=1;i<5;i++)
		{
			BA[i].addActionListener(this);
			BC[i].addActionListener(this);
			BD[i].addActionListener(this);
			BG[i].addActionListener(this);
			BH[i].addActionListener(this);
			BK[i].addActionListener(this);
		}
		
		for(int i=1;i<23;i++)
		{
			EA[i].addActionListener(this);
			EB[i].addActionListener(this);
			EC[i].addActionListener(this);
			ED[i].addActionListener(this);
			EE[i].addActionListener(this);
			EF[i].addActionListener(this);
		}
		add1.addActionListener(this);
        remove.addActionListener(this);	
        book.addActionListener(this);			
 		back.addActionListener(this); 
		tfname.addKeyListener(this);
		tlname.addKeyListener(this);
		tmob.addKeyListener(this);
		tage.addKeyListener(this);
		tmob.addKeyListener(this);
		
   }
   
   void excuvisible()
   {
	   for(int i=1;i<=4;i++)
	   {
		   BA[i].setEnabled(true);
		   BC[i].setEnabled(true);
		   BD[i].setEnabled(true);
		   BG[i].setEnabled(true);
		   BH[i].setEnabled(true);
		   BK[i].setEnabled(true);
	  }
	  for(int i=1;i<=22;i++)
	  {
		  EA[i].setEnabled(false);
		  EB[i].setEnabled(false);
		  EC[i].setEnabled(false);
		  ED[i].setEnabled(false);
		  EE[i].setEnabled(false);
		  EF[i].setEnabled(false);
		  
	  } 
   }
void ecovisible()
{
	
	 for(int i=1;i<=4;i++)
	   {
		   BA[i].setEnabled(false);
		   BC[i].setEnabled(false);
		   BD[i].setEnabled(false);
		   BG[i].setEnabled(false);
		   BH[i].setEnabled(false);
		   BK[i].setEnabled(false);
	  }
	 
	  for(int i=1;i<=22;i++)
	  {
		  EA[i].setEnabled(true);
		  EB[i].setEnabled(true);
		  EC[i].setEnabled(true);
		  ED[i].setEnabled(true);
		  EE[i].setEnabled(true);
		  EF[i].setEnabled(true);
		  
	  } 
	   
}   
     void ecoseat()
   {
	 int x1=950;
	 //1 to 6 ke liy
     for(int j=1;j<=6;j++)
	 {
     EA[j]=new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/view.png")));
     EA[j].setBounds(x1,86,20,20);
	  EA[j].setToolTipText("EA"+j);
	 add(EA[j]);	 
	 EB[j]=new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/view.png")));
     EB[j].setBounds(x1,108,20,20);
     EB[j].setToolTipText("EB"+j);
	 add(EB[j]);
	      EC[j]=new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/avlseat.png")));
     EC[j].setBounds(x1+10,143,20,20);
     EC[j].setToolTipText("EC"+j);
	 add(EC[j]);
	      ED[j]=new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/avlseat.png")));
     ED[j].setBounds(x1+10,165,20,20);
     ED[j].setToolTipText("ED"+j);
	 add(ED[j]);
	      EE[j]=new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/view.png")));
     EE[j].setBounds(x1,200,20,20);
     EE[j].setToolTipText("EE"+j);
	 add(EE[j]);
	      EF[j]=new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/view.png")));
     EF[j].setBounds(x1,222,20,20);
     EF[j].setToolTipText("EF"+j);
	 add(EF[j]);
     x1=x1-30;
	 }
	 int x2=760;
	 int c=0;
	 //for 7 to 15
	 for(int j=7;j<=15;j++)
	 {
     EA[j]=new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/avlseat.png")));
     EA[j].setBounds(x2,86,20,20);
     EA[j].setToolTipText("EA"+j);
	 add(EA[j]);	 
	 EB[j]=new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/avlseat.png")));
     EB[j].setBounds(x2,108,20,20);
     EB[j].setToolTipText("EB"+j);
	 add(EB[j]);
	 
	 if(j==9)
	    c=8;
	 if(j==12)
		 c=15;
	 if(j==15)
		 c=24;
      
	      EC[j]=new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/avlseat.png")));
     EC[j].setBounds(x2+8-c,143,20,20);
     EC[j].setToolTipText("EC"+j);
	 add(EC[j]);
	      ED[j]=new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/avlseat.png")));
     ED[j].setBounds(x2+8-c,165,20,20);
     ED[j].setToolTipText("ED"+j);
	 add(ED[j]);

	      EE[j]=new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/avlseat.png")));
     EE[j].setBounds(x2,200,20,20);
     EE[j].setToolTipText("EE"+j);
	 add(EE[j]);
	      EF[j]=new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/avlseat.png")));
     EF[j].setBounds(x2,222,20,20);
     EF[j].setToolTipText("EF"+j);
	 add(EF[j]);
     x2=x2-30;
	 }
	 int x3=x2;
	 c=0;
	 int d=0;
	  for(int j=16;j<=22;j++)
	 {
     if(j>=20)
		d=d+4 ;
     EA[j]=new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/two.png")));
     EA[j].setBounds(x3,86+d,20,20);
     EA[j].setToolTipText("EA"+j);
	 add(EA[j]);	 
	 EB[j]=new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/two.png")));
     EB[j].setBounds(x3,108+d,20,20);
     EB[j].setToolTipText("EB"+j);
	 add(EB[j]);
	 
	 if(j==18)
	    c=10;
    
	      EC[j]=new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/avlseat.png")));
     EC[j].setBounds(x3-c-20,143,20,20);
     EC[j].setToolTipText("EC"+j);
	 add(EC[j]);
	      ED[j]=new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/avlseat.png")));
     ED[j].setBounds(x3-c-20,165,20,20);
     ED[j].setToolTipText("ED"+j);
	 add(ED[j]);

	      EE[j]=new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/two.png")));
     EE[j].setBounds(x3,200-d,20,20);
     EE[j].setToolTipText("EE"+j);
	 add(EE[j]);
	      EF[j]=new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/two.png")));
     EF[j].setBounds(x3,222-d,20,20);
     EF[j].setToolTipText("EF"+j);
	 add(EF[j]);
     x3=x3-30;
	 }
	 	 
   }
   void excseat()
   {
	int x=1100;
	for(int i=1;i<=4;i++) 
	{
	  BA[i]=new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/excuseat.png")));
	  BA[i].setBounds(x,86,20,20);
	  BA[i].setToolTipText("BA"+i);
	  add(BA[i]);
	  BC[i]=new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/excuseat.png")));
	  BC[i].setBounds(x,108,20,20);
	  BC[i].setToolTipText("BC"+i);
	  add(BC[i]);
	  BD[i]=new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/excuseat.png")));
	  BD[i].setBounds(x-7,143,20,20);
	  BD[i].setToolTipText("BD"+i);
	  add(BD[i]);
	  BG[i]=new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/excuseat.png")));
	  BG[i].setBounds(x-7,165,20,20);
	  BG[i].setToolTipText("BG"+i);
	  add(BG[i]);
	  BH[i]=new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/excuseat.png")));
	  BH[i].setBounds(x,200,20,20);
	 BH[i].setToolTipText("BH"+i);
	  add(BH[i]);
	  BK[i]=new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/excuseat.png")));
	  BK[i].setBounds(x,222,20,20);
       BK[i].setToolTipText("BK"+i);
	  add(BK[i]);
	  	  x=x-30;
	}
   }
   void check()
   {
	   	 try
      {
	   
		res=st.executeQuery("Select b_sno from booking where b_fno = '"+tfno.getText()+"'");
		if(res.next())
         {
           do
		     {
               String ct=(res.getString(1));
			   String c= ct.substring(0,2);
    		   String temp= ct.substring(2);
               int x = Integer.parseInt(temp);
			  // System.out.println(c);
			       if(c.equals("BA"))
				      	 BA[x].setEnabled(false);
				   if(c.equals("BC"))
				    	  BC[x].setEnabled(false);
				   if(c.equals("BD"))
				   		  BD[x].setEnabled(false);
				   if(c.equals("BG"))
				   		  BG[x].setEnabled(false);
				   if(c.equals("BH"))
				   		  BH[x].setEnabled(false);
				   if(c.equals("BK"))
				  		  BK[x].setEnabled(false);
				  if(c.equals("EA"))
				   		  EA[x].setEnabled(false);
				  if(c.equals("EB"))
				  		  EB[x].setEnabled(false);
				  if(c.equals("EC"))
				  		  EC[x].setEnabled(false);
				  if(c.equals("ED"))
				  		  ED[x].setEnabled(false);
				  if(c.equals("EE"))
				   		  EE[x].setEnabled(false);
				  if(c.equals("EF"))
				    EF[x].setEnabled(false);
	     }while(res.next());
			  
    	 }
	  }
      catch(Exception e)
	  {} 
   }
   void clr()
   {
	   tsno.setText("");
	   tfname.setText("");
	   tlname.setText("");
	   tage.setText("");
	     
   }
   void new1()
  {
	 try
      {
	   	res=st.executeQuery("Select * from id");
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
	  try
	   {
		    if(temail.getText().equals("")||tmob.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Not Enought Data"); 
			}
			else
			 {
		     male.setActionCommand("Male");
             female.setActionCommand("Female");
			 String gen=ch1.getSelection().getActionCommand();
		     res=st.executeQuery("select * from booking");
		     String str= "insert into booking(b_id,b_fno,b_flight,b_class,b_sno,b_fname,b_lname,b_email,b_gen,b_age,b_meal,b_fare,b_status,b_date,b_mob,b_doj)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		     PreparedStatement ps = con.prepareStatement(str);
			 
			for(int c=0;c<model.getRowCount();c++)
			 {
		     System.out.println("111");
		     ps.setString(1,"B"+ctr);
		     ps.setString(2,tfno.getText());
             ps.setString(3,tflight.getText());
		     ps.setString(4,tclass.getText());
		     ps.setString(5,model.getValueAt(c,0).toString());
             ps.setString(6,model.getValueAt(c,1).toString());
		     ps.setString(7,model.getValueAt(c,2).toString());
			 ps.setString(8,temail.getText());
             ps.setString(9,model.getValueAt(c,3).toString());
		     ps.setString(10,model.getValueAt(c,4).toString());
			 ps.setString(11,model.getValueAt(c,5).toString());
		     ps.setString(12,model.getValueAt(c,6).toString()); 
			 ps.setString(13,"Waiting");
			  ps.setString(14,df.format(date));
			  ps.setString(15,tmob.getText());
			  ps.setString(16,tempdate);
			 ps.executeUpdate();
			 }
			 st.executeUpdate("update temp set bid='"+"B"+ctr+"'");
			 ctr=ctr+1;
			  st1.executeUpdate("update ID set BID='"+ctr+"'");
			   clr();
			 new payment();
	    
	       }
	   }
	   catch(Exception ex)
	    {
			System.out.println(ex);
		}
		
    }
	void totalfare()
	{
		
		ttotal.setText(0.0+"");
		for(int c=0;c<model.getRowCount();c++)
			 {
		       		double temp=Double.parseDouble(ttotal.getText())+Double.parseDouble((model.getValueAt(c,6).toString()));
					temp=(temp*100)/100;
					ttotal.setText(temp+"");
		    }
	}
	public void keyReleased(KeyEvent es){}
	public void keyTyped(KeyEvent et){}
	public void keyPressed(KeyEvent a)
	{
		if(a.getSource()==tmob)
		{
			if (a.getKeyChar() >= '0' && a.getKeyChar() <= '9' || a.getKeyChar()=='\b' || a.getKeyChar()=='-'||tmob.getText().equals("") )               
                		{
                			
                		}
                		else 
                		{
                    			JOptionPane.showMessageDialog((Component)null,"Enter Only Numbers","Message",JOptionPane.INFORMATION_MESSAGE);
                  			    tmob.setText("");
             			}
		}
		if(a.getSource()==tage)
		{
			if (a.getKeyChar() >= '0' && a.getKeyChar() <= '9' ||a.getKeyChar()=='\b' ||tage.getText().equals(""))               
                		{}
                		else 
                		{
                    			JOptionPane.showMessageDialog((Component)null,"Enter Only Numbers","Message",JOptionPane.INFORMATION_MESSAGE);
			    			     tage.setText("");
             			}
		}
		if(a.getSource()==tfname)
		{
			if (a.getKeyChar() >= 'a' && a.getKeyChar() <= 'z' || a.getKeyChar() >= 'A' && a.getKeyChar() <= 'Z'|| a.getKeyChar()=='\b'|| a.getKeyChar()==' '|| a.isShiftDown()==true )               
                		{
                			
                		}
                		else 
                		{
                    			JOptionPane.showMessageDialog((Component)null,"Enter Only Character","Message",JOptionPane.INFORMATION_MESSAGE);
                  			 tfname.setText("");
             			}
		}
		if(a.getSource()==tlname)
		{
			if (a.getKeyChar() >= 'a' && a.getKeyChar() <= 'z' || a.getKeyChar() >= 'A' && a.getKeyChar() <= 'Z'|| a.getKeyChar()=='\b'|| a.getKeyChar()==' '|| a.isShiftDown()==true )               
                		{
                			
                		}
                		else 
                		{
                    		 JOptionPane.showMessageDialog((Component)null,"Enter Only Character","Message",JOptionPane.INFORMATION_MESSAGE);
                  			 tlname.setText("");
             			}
		}
	}
   public void actionPerformed(ActionEvent e)
  {
      if(e.getSource()==add1)
	  {
		    if(tsno.getText().equals("")||tfname.getText().equals("")||tlname.getText().equals("")||tage.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Invalid Fill","Alert",JOptionPane.WARNING_MESSAGE);
			}
			else 
			{
			 jsp.setVisible(true);
			 String m=meal.getSelectedItem();
		     male.setActionCommand("Male");
             female.setActionCommand("Female");
			 String gen=ch1.getSelection().getActionCommand();
			 int a = Integer.parseInt(tage.getText());
			 double f=1;
			 if(a>=12)
				 f=Double.parseDouble(tfare.getText());
			 
			 if(a<12 && a>2)
			 {
				 f=((Double.parseDouble(tfare.getText())*80)/100);
			 }
			 if(a<=2)
			 {
				 f=((Double.parseDouble(tfare.getText())*50)/100);
			 }
			  if(s<=9)
		      {
	 		     model.addRow(new Object[]{tsno.getText(),tfname.getText(),tlname.getText(),gen,tage.getText(),m,f});
				 s=s+1;
			     clr();
			   }
			  
		else
           JOptionPane.showMessageDialog(null,"Only 9 Passanger  book Ticket At a Time","Alert",JOptionPane.WARNING_MESSAGE);			
		   table.setModel(model);
		 totalfare();
	  }

	  }	
    if(e.getSource()==remove)
    {
     int selectedRow = table.getSelectedRow();
		model.removeRow(selectedRow);
		s=s-1;
      totalfare();		
    }	
	 if(e.getSource()==book)
     {
		new1();
        save();
	    this.dispose();
     }
	 
	    if(e.getSource()==back)
	   {
         new search();
		 this.dispose();
	   }
	 for(int i=1;i<=5;i++)
      {
		  if(e.getSource()==BA[i])
		  {
			  tsno.setText("BA"+i);
			  BA[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/select.png")));
		  }
		  		  if(e.getSource()==BC[i])
				  {
			  tsno.setText("BC"+i);
			  BC[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/select.png")));
				  }
		  		  if(e.getSource()==BD[i])
				  {
			  tsno.setText("BD"+i);
			  BD[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/select.png")));
				  }
		  		  if(e.getSource()==BG[i])
				  {
			  tsno.setText("BG"+i);
			  BG[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/select.png")));
				  }
		  		  if(e.getSource()==BH[i])
				  {
			  tsno.setText("BH"+i);
			  BH[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/select.png")));
				  }
		  		  if(e.getSource()==BK[i])
				  {
			  tsno.setText("BK"+i);
			  BK[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/select.png")));
				  }
	  }	
	  for(int i=1;i<=24;i++)
      {
		  if(e.getSource()==EA[i])
		  {
			  tsno.setText("EA"+i);
			  EA[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/select.png")));
		  }
		  		  if(e.getSource()==EB[i])
				  {
			  tsno.setText("EB"+i);
			  EB[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/select.png")));
				  }
		  		  if(e.getSource()==EC[i])
				  {
			  tsno.setText("EC"+i);
			  EC[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/select.png")));
				  }
		  		  if(e.getSource()==ED[i])
				  {
			  tsno.setText("ED"+i);
			  ED[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/select.png")));
				  }
		  		  if(e.getSource()==EF[i])
				  {
			  tsno.setText("EF"+i);
			  EF[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/select.png")));
				  }
		  		  if(e.getSource()==EE[i])
				  {
			  tsno.setText("EE"+i);
			  EE[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/select.png")));
				  }
	  }	
	  
    
  }
  

 public static void main(String argv[])throws IOException
{
seat obj = new seat();


}   
	
}