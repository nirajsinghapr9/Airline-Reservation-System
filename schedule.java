import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.event.*;
import javax.swing.table.*;
public class schedule extends JFrame implements ActionListener , ItemListener,MouseListener,DocumentListener
{
  Connection con;
  ResultSet res,res1,rr;
  Statement st,st1;
  int flgedit=0;
  int temp[]=new int[31];
   JLabel Head = new JLabel("Flight Schedule Form");
  JLabel search = new JLabel("Search");
  JLabel sel = new JLabel("Select");
  JComboBox s_combo=new JComboBox();
  JTextField  s_tsearch=new JTextField();
  JLabel day1[]= new JLabel[32];
  JTextField time[]=new JTextField[32];
  JTextField timed[]=new JTextField[32];
  JTextField duration[]=new JTextField[32];
  JLabel lday[] = new JLabel[4];
  JLabel ltime[]=new JLabel[8];
  JLabel lmonth = new JLabel("Month");
  JLabel lyear = new JLabel(" Year");
  JLabel s_fno = new JLabel("Flight No");
  JLabel s_fname = new JLabel("Flight Name");
  JTextField s_tfno = new JTextField("");
  JTextField s_tfname = new JTextField("");
  JLabel back1 =new JLabel(new ImageIcon(ClassLoader.getSystemResource("icon/s1.jpg")));
  JButton s_bnew=new JButton("New",new ImageIcon(ClassLoader.getSystemResource("icon/new1.png")));
  JButton s_bsave=new JButton("Save",new ImageIcon(ClassLoader.getSystemResource("icon/save1.png")));
  JButton s_bdel=new JButton("Delete",new ImageIcon(ClassLoader.getSystemResource("icon/Delete1.png")));
  JButton s_bedit=new JButton("Edit",new ImageIcon(ClassLoader.getSystemResource("icon/edit1.png")));
  JButton s_bcancle=new JButton("Cancel",new ImageIcon(ClassLoader.getSystemResource("icon/cancel1.png")));
  Choice date,year,month;
  Font myFont = new Font("Lucida Fax", Font.BOLD,12);
  Font myFont1 = new Font("Serif", Font.BOLD,18);
  JTable table1 = new JTable()
  {
	  public boolean isCellEditable(int rowIndex,int colIndex)
      {
	    return false;
	  }
  };
  JTable table2 = new JTable()
  {
	  public boolean isCellEditable(int rowIndex,int colIndex)
      {
	    return false;
	  }
  };
  DefaultTableModel model1 = new DefaultTableModel(new String[]{"Flight_NO", "Flight_Name","Type","Source", "Destination"},0);
  JScrollPane jsp1;
  DefaultTableModel model2 = new DefaultTableModel(new String[]{"Flight_NO", "Flight_Name","Month","Year", "1","1","2","2","3","3","4","4","5","5","6","6","7","7","8","8","9","9","10","10","11","11","12","12","13","13","14","14","15","15","16","16","17","17","18","18","19","19","20","20","21","21","22","22","23","23","24","24","25","25","26","26","27","27","28","28","29","29","30","30","31","31"},0);
  JScrollPane jsp2;
  String combostr="",inputstr="",tno,tname;
  int flag;
  public schedule()
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
	 show1();
	 off();
	 display();
	 flightdetails();
	 setTitle("Flight Schedule");
	 setLayout(null);
     setSize(1350,680);
	 setLocation(10,52);
     setVisible(true);
	 month=new Choice();
	 String[] monitem={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	 for(int i=0;i<monitem.length;i++)
	 month.add(monitem[i]);
	 int m=2016;
	 year=new Choice();
	 for(int i=0;i<=2;i++)
	 {
		String yearitem=Integer.toString(m++);
		year.add(yearitem);
	 }
	lday[1] = new JLabel("DAY");
	lday[2] = new JLabel("DAY");
	lday[3] = new JLabel("DAY");
	ltime[1] = new JLabel("Arr.");
	ltime[2] = new JLabel("Dipa.");
	ltime[3] = new JLabel("Arr.");
	ltime[4] = new JLabel("Dipa.");
	ltime[5] = new JLabel("Arr.");
	ltime[6] = new JLabel("Dipa.");
	s_fno.setFont(myFont);
	add(Head);
	add(s_fno);
	sel.setFont(myFont);
	add(sel);
	search.setFont(myFont);
	add(search);
	add(s_combo);
	add(s_tfno);
	s_tfname.setFont(myFont);
	s_tfname.setEditable(false);
	s_tfno.setFont(myFont);
	s_tfno.setEditable(false);
	s_fname.setFont(myFont);
	add(s_fname);
	add(s_tfname);
	lmonth.setFont(myFont);
	add(lmonth);
	lyear.setFont(myFont);
	add(lyear);
	s_bnew.setFont(myFont1);
    s_bnew.setForeground(Color.RED);
	s_bsave.setFont(myFont1);
    s_bsave.setForeground(Color.RED);
	s_bedit.setFont(myFont1);
    s_bedit.setForeground(Color.RED);
	s_bdel.setFont(myFont1);
    s_bdel.setForeground(Color.RED);
	s_bcancle.setFont(myFont1);
    s_bcancle.setForeground(Color.RED);
	s_bnew.setToolTipText("Add New Flight Information");
		s_bsave.setToolTipText("Save  ");
		s_bedit.setToolTipText("Edit Records");
		s_bdel.setToolTipText("Delete Records ");
		s_bcancle.setToolTipText("Close Window");
    add(s_bnew);
	add(s_bsave);
	add(s_bdel);
	add(s_bedit);
	add(s_bcancle);
	add(month);
	s_tsearch.setFont(myFont);
	add(s_tsearch);
	add(year);
	add(ltime[1]);
	add(ltime[2]);
	add(ltime[3]);
	add(ltime[4]);
	add(ltime[5]);
	add(ltime[6]);
	add(lday[1]);
	add(lday[2]);
	add(lday[3]);
	add(table1);
	add(table2);
	
   back1.setBounds(0,0,1350,380);
 Head.setBounds(450,10,400,35);
		Head.setFont(new Font("Serif",Font.BOLD,28));
		Head.setForeground(Color.RED);
	s_combo.addItem("Flight_No");
    s_combo.addItem("Source");
    s_combo.addItem("Destination");
	sel.setBounds(600,50,70,30);
	s_combo.setBounds(670,55,100,20);
	lday[1].setBounds(65,110,30,40);
	lday[2].setBounds(235,110,30,40);
	lday[3].setBounds(410,110,30,40);
	ltime[1].setBounds(95,110,35,40);
	ltime[2].setBounds(150,110,35,40);
	ltime[3].setBounds(265,110,30,40);
	ltime[4].setBounds(320,110,30,40);
	ltime[5].setBounds(440,110,30,40);
	ltime[6].setBounds(495,110,30,40);
	lmonth.setBounds(65,85,45,20);
	lyear.setBounds(200,85,45,20);
	s_fno.setBounds(65,50,70,20);
	s_tfno.setBounds(125,50,70,28);
	s_fname.setBounds(200,50,75,20);
	s_tfname.setBounds(280,50,120,28);
    s_bnew.setBounds(600,550,100,35);
	search.setBounds(800,50,70,30);
	s_tsearch.setBounds(860,55,100,28);
    s_bsave.setBounds(710,550,120,35);
	s_bdel.setBounds(840,550,120,35);
    s_bedit.setBounds(970,550,120,35);
	s_bcancle.setBounds(1100,550,120,35);
	month.setBounds(125,85,60,20);
	year.setBounds(280,85,60,20);
	year.addItemListener(new ItemListener(){
    public void itemStateChanged(ItemEvent ie)
     {
		String m1=month.getSelectedItem();
	    int y1=Integer.parseInt(year.getSelectedItem());
		if(m1.equals("Feb")&&((y1%4)==0))
		  {
		    day1[31].setEnabled(false);
		    time[31].setEnabled(false); 
			timed[31].setEnabled(false);
			day1[30].setEnabled(false);
		    time[30].setEnabled(false);
			timed[30].setEnabled(false);
			time[29].setEnabled(false);
			timed[29].setEnabled(false);
			time[29].setText("");
			time[31].setText("");
		    timed[31].setText("");
		    time[30].setText("");
		    timed[30].setText("");
		  }}});
     month.addItemListener(new ItemListener(){
     public void itemStateChanged(ItemEvent ie)
      {
         String m1=month.getSelectedItem();
	     int y1=Integer.parseInt(year.getSelectedItem());
		 if(m1.equals("Apr")||m1.equals("Jun")||m1.equals("Sep")||m1.equals("Nov"))
		  {
		   day1[31].setEnabled(false);
		   time[31].setEnabled(false);
		   timed[31].setEnabled(false);
		   time[31].setText("");
		   timed[31].setText("");
		   day1[30].setEnabled(true);
		   time[30].setEnabled(true);
		   timed[30].setEnabled(true);
		  }
		 if(m1.equals("Jan")||m1.equals("Mar")||m1.equals("Jul")||m1.equals("Aug")||m1.equals("Oct")||m1.equals("Dec"))
		  {
			day1[31].setEnabled(true);
		    time[31].setEnabled(true);
			timed[31].setEnabled(true);
		  }
		 if(m1.equals("Feb"))
		  {
		    day1[31].setEnabled(false);
		    time[31].setEnabled(false); 
			timed[31].setEnabled(false);
			day1[30].setEnabled(false);
		    time[30].setEnabled(false);
			timed[30].setEnabled(false);
		    time[31].setText("");
		    timed[31].setText("");
		    time[30].setText("");
		    timed[30].setText("");
		  }}});
	   s_tsearch.getDocument().addDocumentListener(this);
	   s_bnew.addActionListener(this);
       s_bsave.addActionListener(this);
	   s_bdel.addActionListener(this);
	   s_bedit.addActionListener(this);
	   s_bcancle.addActionListener(this);
	   table1.addMouseListener(this);
	   flag=1;
	   table2.addMouseListener(this);
	   jsp1 = new JScrollPane(table1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	   table1.getColumnModel().getColumn(0).setPreferredWidth(70);
	   table1.getColumnModel().getColumn(1).setPreferredWidth(120);
	   table1.getColumnModel().getColumn(2).setPreferredWidth(100);
	   table1.getColumnModel().getColumn(3).setPreferredWidth(81);
	   table1.getColumnModel().getColumn(4).setPreferredWidth(81);
       table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);    
       table1.setFillsViewportHeight(true);
       table1.setShowGrid(true);
       table1.setShowVerticalLines(true);
       table1.setGridColor(new Color(0,0,0));
       JTableHeader header = table1.getTableHeader();
       table1.setBackground(new Color(213,255,213));
       table1.setEnabled(true);
       header.setFont(new Font("Courier", Font.CENTER_BASELINE, 12));
       header.setBackground(Color.black);
       header.setForeground(new Color(255,255,47));
	   add(jsp1);
	   setVisible(true);
	   jsp1.setBounds(650,120,470,150);
	   jsp2 = new JScrollPane(table2,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	   table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	   table2.getColumnModel().getColumn(0).setPreferredWidth(70);
	   table2.getColumnModel().getColumn(1).setPreferredWidth(120);
	   for(int i=2;i<=65;i++)
	    {
		  table2.getColumnModel().getColumn(i).setPreferredWidth(40);
	    }
	   table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);    
       table2.setFillsViewportHeight(true);
       table2.setShowGrid(true);
       table2.setShowVerticalLines(true);
       table2.setGridColor(new Color(0,0,0));
       JTableHeader header1 = table2.getTableHeader();
       table2.setBackground(new Color(253,255,149));
       table2.setEnabled(true);
       header1.setFont(new Font("Courier", Font.CENTER_BASELINE, 12));
       header1.setBackground(Color.black);
       header1.setForeground(new Color(0,128,255));
	   add(jsp2);
	   jsp2.setBounds(600,280,700,250);
	   add(back1);
		back1.setBounds(0,0,1350,680);
    }
 public void actionPerformed(ActionEvent e)
   {
     if(e.getSource()==s_bnew)
	  {
         clr();
		 on();
		 s_bnew.setEnabled(false);
		 s_bsave.setEnabled(true);
	  }
    if(e.getSource()==s_bsave)
	     save();
	if(e.getSource()==s_bdel)
	   	del();
    if(e.getSource()==s_bedit)
	  {
		s_bedit.setEnabled(false);
		s_bsave.setEnabled(true);
		s_bnew.setEnabled(false);
		on();
		flgedit=1;
	  }
   if(e.getSource()==s_bcancle)
        this.dispose();
  }
 void clr()
   {
	 for(int i=1;i<=31;i++)
	  {
       timed[i].setText("");
	   time[i].setText("");
	  }
	 s_tfno.setText("");
	 s_tfname.setText("");
   }
void show1()
  {         
    int y=150;
	int y1=150;
	int y3=150;
	for(int i=1;i<=31;i++) 
	{
      if(i>0&&i<=10)
	    {
		   day1[i]=new JLabel(i+""); 
		   day1[i].setFont(myFont);
           day1[i].setBounds(70,y,16,30);
		   time[i] = new JTextField("");
		   time[i].setFont(myFont);
		   time[i].setBounds(90,y,50,30);
		   timed[i] = new JTextField("");
		   timed[i].setFont(myFont);
		   timed[i].setBounds(145,y,50,30);
		   y=y+40;
           add(day1[i]);
		   add(time[i]);
		   add(timed[i]);
		}
	 if(i>10&&i<=20)
		{
		  day1[i]=new JLabel(i+""); 
		  day1[i].setFont(myFont);
          day1[i].setBounds(245,y1,16,30);
    	  time[i] = new JTextField("");
		  time[i].setFont(myFont);
		  time[i].setBounds(265,y1,50,30);
		  timed[i] = new JTextField("");
		  timed[i].setFont(myFont);
		  timed[i].setBounds(320,y1,50,30);
		  y1=y1+40;
          add(day1[i]);
		  add(time[i]);
		  add(timed[i]);
	    }
	 if(i>20&&i<=30)
       {
          day1[i]=new JLabel(i+""); 
		  day1[i].setFont(myFont);
		  day1[i].setBounds(420,y3,16,30);
	      time[i] = new JTextField("");
		  time[i].setFont(myFont);
		  time[i].setBounds(440,y3,50,30);
		  timed[i] = new JTextField("");
		  timed[i].setFont(myFont);
		  timed[i].setBounds(495,y3,50,30);
		  y3=y3+40;
          add(day1[i]); 
		  add(time[i]);
		  add(timed[i]);
	   }
	 if(i==31)
	   {
		  day1[i]=new JLabel(i+""); 
		  day1[i].setFont(myFont);
          day1[i].setBounds(245,550,16,30);
		  time[i] = new JTextField("");
		  time[i].setFont(myFont);
		  time[i].setBounds(265,550,50,30); 
		  timed[i] = new JTextField("");
		  timed[i].setFont(myFont);
		  timed[i].setBounds(320,550,50,30);
		  add(day1[i]); 
		  add(time[i]);
		  add(timed[i]);
	   }
    }
  }
void flightdetails()
 {
   model1.setRowCount(0);
   try
	{
	  inputstr=s_tsearch.getText();
      combostr=(String)s_combo.getSelectedItem();
	  if(inputstr.equals(""))
	     res=st.executeQuery("Select * from flight ");
	  else
	   {
		 if(combostr.equals("Flight_No"))
			res=st.executeQuery("Select * from flight where f_no like '"+inputstr+"%'");
		 if(combostr.equals("Source"))
			res=st.executeQuery("Select * from flight where f_src like '"+inputstr+"%'");
		 if(combostr.equals("Destination"))
			res=st.executeQuery("Select * from flight where f_dst like '"+inputstr+"%'");
   	   }
	  inputstr="";
	  combostr="";
      if(res.next())
       {
        do
         {
	      model1.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5)}); 
	     }while(res.next());
	     table1.setModel(model1);
	  }
    }
   catch(Exception e)
	{} 
 }
 void display()
 {
   	model2.setRowCount(0);	   
   try
	  {
	    res=st.executeQuery("Select * from schedule s,scheduledip sd where s.year=sd.dyear and s.month=sd.dmonth and s.s_fname=sd.s_dfname and s.s_fno=sd.s_dfno ");
		if(res.next())
         {
           do
		    {
	 		   model2.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(40),res.getString(6),res.getString(41),res.getString(7),res.getString(42),res.getString(8),res.getString(43),res.getString(9),res.getString(44),res.getString(10),res.getString(45),res.getString(11),res.getString(46),res.getString(12),res.getString(47),res.getString(13),res.getString(48),res.getString(14),res.getString(49),res.getString(15),res.getString(50),res.getString(16),res.getString(51),res.getString(17),res.getString(52),res.getString(18),res.getString(53),res.getString(19),res.getString(54),res.getString(20),res.getString(55),res.getString(21),res.getString(56),res.getString(22),res.getString(57),res.getString(23),res.getString(58),res.getString(24),res.getString(59),res.getString(25),res.getString(60),res.getString(26),res.getString(61),res.getString(27),res.getString(62),res.getString(28),res.getString(63),res.getString(29),res.getString(64),res.getString(30),res.getString(65),res.getString(31),res.getString(66),res.getString(32),res.getString(67),res.getString(33),res.getString(68),res.getString(34),res.getString(69),res.getString(35),res.getString(70)}); 
	        }while(res.next());
		    table2.setModel(model2);
		 }
     }
      catch(Exception e)
	  {} 
 }
 void save()
 {
	String m=month.getSelectedItem();
	String y=year.getSelectedItem();
	int ctr=0;
	try
	 {
	  for(int i=1;i<=31;i++)
	  {
	    if(time[i].getText().equals(""))
		   time[i].setText("*");
		if(timed[i].getText().equals(""))
		   timed[i].setText("*");
	  }
	 if(s_tfno.getText().equals("")||s_tfname.getText().equals("") )
	  {
		JOptionPane.showMessageDialog(null,"Please Fill Flight No ");
      }
	  if(flgedit==1)
	  {
		  edit();
	  }
	 else
	  {
	    rr=st.executeQuery("select * from schedule where s_fno='"+s_tfno.getText()+"' and s_fname='"+s_tfname.getText()+"' and month='"+m+"' and year='"+y+"'");
		while(rr.next())
		  {
		   ctr++;
		  }
		if(ctr==0)
		  {
		    res=st.executeQuery("select * from schedule");
			res1=st.executeQuery("select * from scheduledip");
            String str= "insert into schedule(s_fno,s_fname,month,year,a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,a20,a21,a22,a23,a24,a25,a26,a27,a28,a29,a30,a31)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			String str1= "insert into scheduledip(s_dfno,s_dfname,dmonth,dyear,d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,d11,d12,d13,d14,d15,d16,d17,d18,d19,d20,d21,d22,d23,d24,d25,d26,d27,d28,d29,d30,d31)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		    PreparedStatement ps = con.prepareStatement(str);
			PreparedStatement ps1 = con.prepareStatement(str1);
            ps.setString(1,s_tfno.getText());
		    ps.setString(2,s_tfname.getText());
			ps.setString(3,m);
		    ps.setString(4,y);
            ps1.setString(1,s_tfno.getText());
			ps1.setString(2,s_tfname.getText());
			ps1.setString(3,m);
		    ps1.setString(4,y);
            for(int i=1;i<=31;i++)
             {
	           ps.setString(i+4,time[i].getText());
		       ps1.setString(i+4,timed[i].getText());
             }
		    ps.executeUpdate();
			ps1.executeUpdate();
            JOptionPane.showMessageDialog(null,"Saved Successfull");
	        display();
		  }
		else
		  JOptionPane.showMessageDialog(null,"already exists"); 
	  }
     } 
	catch(Exception x)
	  {}	  
	  s_bnew.setEnabled(true);
	  s_bsave.setEnabled(false);
 }
 void del()
 {
   if(s_tfno.getText().equals("")||s_tfname.getText().equals(""))
	  JOptionPane.showMessageDialog(null,"Not Enough Details");
   else
	{
	  int cnfb = JOptionPane.YES_NO_OPTION;
	  int cnf = JOptionPane.showConfirmDialog(null,"Would You Like To Delete ?","warning" ,cnfb );
	  if(cnf==0)
	   {
        try
		 {
		  st.executeUpdate("delete from schedule where s_fno='"+s_tfno.getText()+"'");
		  st.executeUpdate("delete from scheduledip where s_dfno='"+s_tfno.getText()+"'");
		  JOptionPane.showMessageDialog(null,"Deleted successfully!!!");
		  display();
		 }
		catch(Exception ex)
		 {}
	   }
    }
 }
void edit()
 {
   String m=month.getSelectedItem();
   String y=year.getSelectedItem();
   try
    {
	  if(s_tfno.getText().equals("")||s_tfname.getText().equals(""))
		JOptionPane.showMessageDialog(null,"Not Enough Details!!!");
	  else
	   {
        st.executeUpdate("update schedule set month='"+m+"',year='"+y+"',1='"+time[1].getText()+"',2='"+time[2].getText()+"',3='"+time[3].getText()+"',4='"+time[4].getText()+"',5='"+time[5].getText()+"',6='"+time[6].getText()+"' ,7='"+time[7].getText()+"',8='"+time[8].getText()+"',9='"+time[9].getText()+"',10='"+time[10].getText()+"',11='"+time[11].getText()+"',12='"+time[12].getText()+"',13='"+time[13].getText()+"',14='"+time[14].getText()+"',15='"+time[15].getText()+"',16='"+time[16].getText()+"',17='"+time[17].getText()+"',18='"+time[18].getText()+"',19='"+time[19].getText()+"',20='"+time[20].getText()+"',21='"+time[21].getText()+"',22='"+time[22].getText()+"',23='"+time[23].getText()+"',24='"+time[24].getText()+"',25='"+time[25].getText()+"',26='"+time[26].getText()+"' ,27='"+time[27].getText()+"',28='"+time[28].getText()+"',29='"+time[29].getText()+"',30='"+time[30].getText()+"',31='"+time[31].getText()+"'where s_fno='"+s_tfno.getText()+"'");
		st.executeUpdate("update scheduledip set dmonth='"+m+"',dyear='"+y+"',d1='"+timed[1].getText()+"',d2='"+timed[2].getText()+"',d3='"+timed[3].getText()+"',d4='"+timed[4].getText()+"',d5='"+timed[5].getText()+"',d6='"+timed[6].getText()+"' ,d7='"+timed[7].getText()+"',d8='"+timed[8].getText()+"',d9='"+timed[9].getText()+"',d10='"+timed[10].getText()+"',d11='"+timed[11].getText()+"',d12='"+timed[12].getText()+"',d13='"+timed[13].getText()+"',d14='"+timed[14].getText()+"',d15='"+timed[15].getText()+"',d16='"+timed[16].getText()+"',d17='"+timed[17].getText()+"',d18='"+timed[18].getText()+"',d19='"+timed[19].getText()+"',d20='"+timed[20].getText()+"',d21='"+timed[21].getText()+"',d22='"+timed[22].getText()+"',d23='"+timed[23].getText()+"',d24='"+timed[24].getText()+"',d25='"+timed[25].getText()+"',d26='"+timed[26].getText()+"' ,d27='"+timed[27].getText()+"',d28='"+timed[28].getText()+"',d29='"+timed[29].getText()+"',d30='"+timed[30].getText()+"',d31='"+timed[31].getText()+"'where s_dfno='"+s_tfno.getText()+"'");
	    JOptionPane.showMessageDialog(null,"Updated successfully!!!");
		display();
	   }
	}
   catch(Exception ex)
   {}
	flgedit=0;
	s_bedit.setEnabled(true);
	s_bnew.setEnabled(true);
	s_bsave.setEnabled(false);
 }
 void off()
 {
	 s_bsave.setEnabled(false);
	 for(int i=1;i<=31;i++)
	 {
		 time[i].setEditable(false);
		 timed[i].setEditable(false);
	 }
 }
 void on()
 {
	 for(int i=1;i<=31;i++)
	 {
		 time[i].setEditable(true);
		 timed[i].setEditable(true);
	 }
 }
public void mouseClicked(MouseEvent e)
 {
   
   
   if(e.getSource()==table1)
	{
	  int row=table1.getSelectedRow();
	  s_tfno.setText(model1.getValueAt(row,0).toString());
	  s_tfname.setText(model1.getValueAt(row,1).toString());
	  tno=s_tfno.getText();
	  tname =s_tfname.getText();
	}
   else
	{
      int row1=table2.getSelectedRow();	  
	  int j=4,k=5;
	  s_tfno.setText(model2.getValueAt(row1,0).toString());
	  s_tfname.setText(model2.getValueAt(row1,1).toString());
	   for(int i=1;i<32;i++)
	   {
		  time[i].setText(model2.getValueAt(row1,j).toString());
		  timed[i].setText(model2.getValueAt(row1,k).toString());
		  j=j+2;
		  k=k+2;
	   }
	}
  }	
 public void mouseExited(MouseEvent e)
  {}
 public void mouseEntered(MouseEvent e)
  {}
 public void mouseReleased(MouseEvent e)
  {}
public void mousePressed(MouseEvent e)
  {}
public void itemStateChanged(ItemEvent ie)
  {}
public void insertUpdate(DocumentEvent e)
  {
    flightdetails();
  }
public void removeUpdate(DocumentEvent e)
  {
    flightdetails();
  }
public void changedUpdate(DocumentEvent e)
  {
    flightdetails();
  }	
public static void main(String argv[])throws IOException
  {
      schedule obj=new schedule();
  }
}
  