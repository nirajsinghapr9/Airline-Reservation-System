import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.event.*;
import javax.swing.table.*;
public class International extends JFrame implements ActionListener,MouseListener//, ItemListener 
{
   Connection con = null;
   ResultSet res,res1,rr,res2;
   Statement st,st1;
   int i=2;
   double t;
   String a,f1,f2,f3,f4,f5,f6,f7,f8,f9,f10;
   JLabel Head = new JLabel("Flight Slection Form");
   JLabel finfo = new JLabel("Information About Trip");
   JLabel from = new JLabel("From");
   JLabel to = new JLabel("To");
   JLabel ddate = new JLabel("Depature Date");
   JLabel cls = new JLabel("Class");
   JLabel airline = new JLabel("Airline Fliter");
   JLabel faci = new JLabel("Facilities");
   JLabel yes = new JLabel("Yes");
   JLabel no = new JLabel("NO");
   JLabel meal = new JLabel("Meal");
   JLabel vod = new JLabel("VOD");
   JLabel alcohol = new JLabel("Alcohol");
   JLabel details = new JLabel("Details");
   JLabel aname = new JLabel("Airline Name");
   JLabel dfrom = new JLabel("From");
   JLabel dto = new JLabel("To");
   JLabel stopno = new JLabel("Stopage");
   JLabel bfare = new JLabel("Base Fare");
   JLabel tax = new JLabel("Taxes");
   JLabel grand = new JLabel("Grand Total");
   JLabel canclefee = new JLabel("Cancle Fee:");
   JLabel bag = new JLabel("Baggeg");
   JTextField b_cls = new JTextField();
   JTextField b_tfrom = new JTextField();
   JTextField b_tto = new JTextField();
   JTextField b_tdate = new JTextField();
   JTextField b_taname = new JTextField();
   JTextField b_tdfrom = new JTextField();
   JTextField b_tdto = new JTextField();
   JTextField b_tstopno = new JTextField();
   JTextField b_tbfare = new JTextField();
   JTextField b_ttax = new JTextField();
   JTextField b_tgrand = new JTextField();
   JTextField Tempclass=new JTextField();
   JTextField b_tcanclefee = new JTextField();
   JTextField b_tbag = new JTextField();
   JCheckBox indigo = new JCheckBox("Indigo");
   JCheckBox jetairways = new JCheckBox("Jet Airways");
   JCheckBox  airlankan = new JCheckBox("Srilankan Airline");
   JCheckBox air = new JCheckBox("Air India");
   JCheckBox malaysia = new JCheckBox("Malaysia");
   JCheckBox singapore = new JCheckBox("Singapore Airlines");
   JCheckBox british = new JCheckBox("British Airline");
   JCheckBox kingfishar = new JCheckBox("kingfishar");
   JCheckBox emirates = new JCheckBox("Emirates");
   JCheckBox france = new JCheckBox("Air France");
   JCheckBox myes = new JCheckBox();
   JCheckBox vyes = new JCheckBox();
   JCheckBox ayes = new JCheckBox();
   JCheckBox mno = new JCheckBox();
   JCheckBox vno = new JCheckBox();
   JCheckBox ano = new JCheckBox();
   Font myFont = new Font("Lucida Fax", Font.BOLD,14);
   JLabel back1 =new JLabel(new ImageIcon(ClassLoader.getSystemResource("icon/old.png")));
   JButton book = new JButton("SEAT BOOK",new ImageIcon(ClassLoader.getSystemResource("icon/chair.png")));
   JButton back = new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/back2.png")));
   JTable table1 = new JTable()
    {
	  public boolean isCellEditable(int rowIndex,int colIndex)
      {
	    return false;
	  }
    };
  DefaultTableModel model1 = new DefaultTableModel(new String[]{"Flight_NO","Flight_Name","From","To","Depature","Arival"},0);
  JScrollPane jsp1;
  public International()
   {    
        
		 try
		 {
	       con =DriverManager.getConnection("jdbc:odbc:mydsn");
	       st = con.createStatement();
		  res=st.executeQuery("Select * from temp");
		   if(res.next())
	        {
		     do
		      {
			      b_tfrom.setText(res.getString(1));
				  b_tto.setText(res.getString(2));
				  b_tdate.setText(res.getString(3));
				  b_cls.setText(res.getString(4));
		      }while(res.next());
			}
		 }
		 catch(Exception e){}
		new expire();
		setLayout(null);
        setSize(1350,680);
	    setLocation(10,52);
        setVisible(true);
        getContentPane().setBackground(new Color(250,250,250));
		add(Head);
		setTitle("Flight Selection");
		add(kingfishar);
		add(indigo);
		add(british);
		add(malaysia);
		add(air);
		add(jetairways);
		add(airlankan);
		add(singapore);
        add(emirates); 
        add(france);
		ddate.setFont(myFont);
		from.setFont(myFont);
		to.setFont(myFont);
		cls.setFont(myFont);
		airline.setFont(myFont);
		airline.setForeground(Color.BLUE);
		faci.setFont(myFont);
		faci.setForeground(Color.BLUE);
		details.setFont(myFont);
		details.setForeground(Color.BLUE);
		book.setFont(myFont);
		book.setForeground(Color.RED);
		add(ddate);
		add(from);
		add(to);
		add(cls);
		add(b_cls);
		b_tdate.setEditable(false);
		b_tfrom.setEditable(false);
		b_tto.setEditable(false);
		b_cls.setEditable(false);
		add(b_tdate);
		add(b_tfrom);
		add(b_tto);
		add(airline);
		add(table1);
		add(details);
		add(aname);
		b_taname.setEditable(false);
		b_tdfrom.setEditable(false);
		b_tdfrom.setEditable(false);
		b_tdto.setEditable(false);
		b_tbfare.setEditable(false);
		b_ttax.setEditable(false);
		b_tgrand.setEditable(false);
		add(b_taname);
		add(dfrom);
		add(b_tdfrom);
		add(dto);
		add(b_tdto);
		add(bfare);
		add(b_tbfare);
		add(tax);
		add(b_ttax);
		add(grand);
		add(b_tgrand);
		add(bag);
		add(b_tbag);
		add(myes);
		add(vyes);
		add(ayes);
		add(mno);
		add(vno);
		add(ano);
		add(faci);
		add(yes);
		add(no);
		add(vod);
		add(meal);
		add(alcohol);
		add(book);
		add(Tempclass);
		
		back1.setBounds(0,0,1350,680);
		add(back);
		Head.setBounds(440,10,400,35);
		Head.setFont(new Font("Serif",Font.BOLD,28));
		Head.setForeground(Color.RED);
		vyes.setEnabled(false);
		myes.setEnabled(false);
		ayes.setEnabled(false);
		vno.setEnabled(false);
		mno.setEnabled(false);
		ano.setEnabled(false);
		back.setBounds(6,5,40,32);
		finfo.setBounds(60,40,200,40);
		from.setBounds(150,80,70,30);
		b_tfrom.setBounds(210,80,120,30);
		to.setBounds(350,80,30,30);
		b_tto.setBounds(380,80,120,30);
		ddate.setBounds(520,80,150,30);
		b_tdate.setBounds(640,80,130,30);
		cls.setBounds(800,80,70,30);
		b_cls.setBounds(850,80,100,30);
		details.setBounds(380,380,70,40);
		aname.setBounds(380,420,100,40);
		b_taname.setBounds(470,428,150,30);
		dfrom.setBounds(380,460,70,40);
		b_tdfrom.setBounds(470,463,150,30);
		dto.setBounds(380,500,70,40);
		b_tdto.setBounds(470,503,150,30);
		stopno.setBounds(380,540,70,40);
		b_tstopno.setBounds(470,543,50,30);
		bfare.setBounds(680,420,70,40);
		b_tbfare.setBounds(750,423,70,30);
		tax.setBounds(680,460,70,40);
		b_ttax.setBounds(750,463,70,30);
		grand.setBounds(680,500,70,40);
		b_tgrand.setBounds(750,503,70,30);
		airline.setBounds(70,150,150,30);
		jetairways.setBounds(50,180,100,20);
		airlankan.setBounds(50,210,120,20);
		air.setBounds(50,240,80,20);
		malaysia.setBounds(50,270,80,20);
		singapore.setBounds(50,300,135,20);
		british.setBounds(50,330,90,20);
		kingfishar.setBounds(50,360,95,20);
		emirates.setBounds(50,390,85,20);
		france.setBounds(50,420,90,20);
		indigo.setBounds(50,450,70,20);
		faci.setBounds(860,380,80,30);
		yes.setBounds(950,380,40,30);
		no.setBounds(990,380,40,30);
	    vod.setBounds(880,420,70,40);
		vyes.setBounds(950,428,20,20);
		vno.setBounds(990,428,20,20);
		meal.setBounds(880,460,70,40);
		myes.setBounds(950,465,20,20);
		mno.setBounds(990,465,20,20);
		alcohol.setBounds(880,500,70,40);
		ayes.setBounds(950,505,20,20);
		ano.setBounds(990,505,20,20);
        book.setBounds(750,550,170,30);
		Tempclass.setBounds(5,1,1,1);
	     book.setToolTipText("Pick Seat");
		 back.setToolTipText("Back On Search Form");
		book.addActionListener(this);
		back.addActionListener(this);
		table1.addMouseListener(this);
		jsp1 = new JScrollPane(table1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
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
	    jsp1.setBounds(380,150,650,200);
		//add(back1);
		jetairways.addItemListener(new ItemListener()
	      {
            public void itemStateChanged(ItemEvent e)
		     {         
             if(e.getStateChange()==1)
				 f1="Jet Airways";
			 else
				 f1="";
			 display();
            }           
         });
	  air.addItemListener(new ItemListener()
	      {
         public void itemStateChanged(ItemEvent e)
		  {         
             if(e.getStateChange()==1)
				 f2="Air India";
			 else
				 f2="";
			 display();
         }           
      });
	  airlankan.addItemListener(new ItemListener()
	      {
         public void itemStateChanged(ItemEvent e)
		  {         
             if(e.getStateChange()==1)
				 f3="Srilankan Airline";
			 else
				 f3="";
			 display();
         }           
      });
	 singapore.addItemListener(new ItemListener()
	      {
         public void itemStateChanged(ItemEvent e)
		  {         
             if(e.getStateChange()==1)
				 f4="Singapore Airlines";
			 else
				 f4="";
			 display();
         }           
      });
	  malaysia.addItemListener(new ItemListener()
	      {
         public void itemStateChanged(ItemEvent e)
		  {         
             if(e.getStateChange()==1)
				 f5="Malaysia Airlines";
			 else
				 f5="";
			 display();
         }           
      });
	  british .addItemListener(new ItemListener()
	      {
         public void itemStateChanged(ItemEvent e)
		  {         
             if(e.getStateChange()==1)
				 f6="British Airways";
			 else
				 f6="";
			 display();
         }           
      });
	  kingfishar.addItemListener(new ItemListener()
	      {
         public void itemStateChanged(ItemEvent e)
		  {         
             if(e.getStateChange()==1)
				 f7="Kingfisher";
			 else
				 f7="";
			 display();
         }           
      });
	  emirates .addItemListener(new ItemListener()
	      {
         public void itemStateChanged(ItemEvent e)
		  {         
             if(e.getStateChange()==1)
				 f8="Emairates";
			 else
				 f8="";
			 display();
         }           
      });
	  france.addItemListener(new ItemListener()
	      {
         public void itemStateChanged(ItemEvent e)
		  {         
             if(e.getStateChange()==1)
				 f9="Air France";
			 else
				 f9="";
			 display();
         }           
      });
	  indigo.addItemListener(new ItemListener()
	      {
         public void itemStateChanged(ItemEvent e)
		  {         
             if(e.getStateChange()==1)
				 f10="IndiGO";
			 else
				 f10="";
			 display();
         }           
      });
	  f1="";f2="";f3="";f4="";f5="";f6="";f7="";f8="";f9="";f10="";
	 
		 
         display();
	  }
   
   public void actionPerformed(ActionEvent e)
    {
	  if(e.getSource()==book)
	   {
         save();
		 new seat();
         this.dispose();
	   }
  if(e.getSource()==back)
	   {
         new search();
		 this.dispose();
	   }
	   
    }
  void display()
   {
	 jsp1.setBounds(380,150,650,200);
	 model1.setRowCount(0);
	 String d=b_tdate.getText().substring(0,2);
	 String m=b_tdate.getText().substring(3,6);
	 String y=b_tdate.getText().substring(7,11);
     int t1=0,t2=0;
	 try
	  {    
  		if(f1.equals("")&&f2.equals("")&&f3.equals("")&&f4.equals("")&&f5.equals("")&&f6.equals("")&&f7.equals("")&&f8.equals("")&&f9.equals("")&&f10.equals(""))
		{
			 res=st.executeQuery("SELECT DISTINCT flight.f_no, flight.f_type, flight.f_name, flight.f_src, flight.f_dst, schedule.*, scheduledip.* FROM ((flight INNER JOIN schedule ON (flight.f_name = schedule.s_fname) AND (flight.f_no = schedule.s_fno)) INNER JOIN scheduledip ON (schedule.s_fname = scheduledip.s_dfname) AND (schedule.s_fno = scheduledip.s_dfno))where schedule.month='"+m+"' and schedule.year='"+y+"'AND f_src = '"+b_tfrom.getText()+"'and f_dst='"+b_tto.getText()+"'");
		 }
		else
     	 res=st.executeQuery("SELECT DISTINCT flight.f_no, flight.f_type, flight.f_name, flight.f_src, flight.f_dst, schedule.*, scheduledip.* FROM ((flight INNER JOIN schedule ON (flight.f_name = schedule.s_fname) AND (flight.f_no = schedule.s_fno)) INNER JOIN scheduledip ON (schedule.s_fname = scheduledip.s_dfname) AND (schedule.s_fno = scheduledip.s_dfno)) where (f_name='"+f1+"' OR f_name='"+f2+"' OR f_name='"+f3+"' OR f_name='"+f4+"' OR f_name='"+f5+"' OR f_name='"+f6+"' OR f_name='"+f7+"' OR f_name='"+f8+"' OR f_name='"+f9+"' OR f_name='"+f10+"')AND schedule.month='"+m+"' and schedule.year='"+y+"' AND f_src = '"+b_tfrom.getText()+"'and f_dst='"+b_tto.getText()+"' " );
		  if(res.next())
	       {
	        do
		      {    
				 t1=Integer.parseInt(d);
				  if(res.getString(2).equals("Domestics"))
				    t2=Integer.parseInt(d);
				  else
					 t2=Integer.parseInt(d)+1; 
				 model1.addRow(new Object[]{res.getString(1),res.getString(3),res.getString(4),res.getString(5),res.getString(t1+9),res.getString(t2+44)}); 
	          }while(res.next());
			  table1.setModel(model1);
	       }
		   else
			 JOptionPane.showMessageDialog(null,"No Fligh Avilable");
	   }
      catch(Exception e)
	   {}
   }
   void save()
	{
	  try
	   {
		 String tfno= a;
		 String tfname = b_taname.getText();
		 String tfare = b_tgrand.getText();
		 st.executeUpdate("update temp set fno='"+a+"',fname='"+tfname+"',fare='"+tfare+"'");
	   }
	   catch(Exception ex)
	    {}
    }
   public void mouseClicked(MouseEvent e)
	{
		int row=table1.getSelectedRow();
		int i=0;
		a = model1.getValueAt(row,0).toString();
		b_taname.setText(model1.getValueAt(row,1).toString());
		b_tdfrom.setText(model1.getValueAt(row,2).toString());
		b_tdto.setText(model1.getValueAt(row,3).toString());
		try
		  {
		   if(b_cls.getText().equals("Economy"))
			{
			    t = 2;
				rr=st.executeQuery("Select f_ecof from flight where  f_no = '"+a+"'");
			 }
		  if(b_cls.getText().equals("Executive"))
			{
				t=5;
				rr=st.executeQuery("Select f_excf from flight where f_no = '"+a+"'");
			}
			if(rr.next())
	       {
			  
 	        do
		      {
				 b_tbfare.setText(rr.getString(1)+"");
			  }while(rr.next());
            }
			b_ttax.setText(((Double.parseDouble(b_tbfare.getText())*t)/100)+"");
		    b_tgrand.setText((Integer.parseInt(b_tbfare.getText())+Double.parseDouble(b_ttax.getText()))+"");
   }
   catch(Exception g)
   {}
   if(b_taname.getText().equals("Air France")||b_taname.getText().equals("Srilankan Airline")||b_taname.getText().equals("Emairates")||b_taname.getText().equals("Malaysia Airlines")||b_taname.getText().equals("British Airways"))
	{
		vyes.setSelected(true);
		myes.setSelected(true);
		ayes.setSelected(true);
		vno.setSelected(false);
		mno.setSelected(false);
		ano.setSelected(false);
	}
   else if(b_taname.getText().equals("IndiGO")||b_taname.getText().equals("Air India")||b_taname.getText().equals("Jet Airways"))
	{
		vyes.setSelected(true);
		myes.setSelected(false);
		ayes.setSelected(false);
		vno.setSelected(false);
		mno.setSelected(true);
		ano.setSelected(true);
	}
	else
	{
	    vyes.setSelected(false);
		myes.setSelected(false);
		ayes.setSelected(false);
		vno.setSelected(true);
		mno.setSelected(true);
		ano.setSelected(true);	
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
 public static void main(String argv[])throws IOException
  {
      International objj=new International();
  }
}