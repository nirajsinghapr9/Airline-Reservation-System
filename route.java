import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.event.*;
import javax.swing.table.*;
public class route extends JFrame implements ActionListener,MouseListener,DocumentListener
{
  Connection con;
  ResultSet res,res1,rr;
  Statement st,st1;
  int ctr=0,flgedit=0;
   JLabel Head = new JLabel(" Flight Route Form");
  JComboBox r_combo=new JComboBox();
  JTextField  r_tsearch=new JTextField();
  JLabel stop[]= new JLabel[6];
  JTextField place[]=new JTextField[6];
  JLabel r_search= new JLabel("Search");
  JLabel r_sel = new JLabel("Select");
  JLabel r_fno = new JLabel("Flight No");
  JLabel r_fname = new JLabel("Flight Name");
  JTextField r_tfno = new JTextField("");
  JTextField r_tfname = new JTextField("");
  JLabel r_src = new JLabel("Source");
  JLabel r_dest = new JLabel("Destination");
  JTextField r_tsrc = new JTextField("");
  JTextField r_tdest = new JTextField("");
  JLabel back1 =new JLabel(new ImageIcon(ClassLoader.getSystemResource("icon/S3.jpg")));
  JButton r_bnew=new JButton("New",new ImageIcon(ClassLoader.getSystemResource("icon/new1.png")));
  JButton r_bsave=new JButton("Save",new ImageIcon(ClassLoader.getSystemResource("icon/Save1.png")));
  JButton r_bdel=new JButton("Delete",new ImageIcon(ClassLoader.getSystemResource("icon/Delete1.png")));
  JButton r_bedit=new JButton("Edit",new ImageIcon(ClassLoader.getSystemResource("icon/edit1.png")));
  JButton r_bcancle=new JButton("Cancel",new ImageIcon(ClassLoader.getSystemResource("icon/cancel1.png"))); 
  Font myFont = new Font("Lucida Fax", Font.BOLD,14);
  Font myFont1 = new Font("Lucida Console", Font.BOLD,16);
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
  DefaultTableModel model2 = new DefaultTableModel(new String[]{"Flight_no","Flight_Name","Source","Stopage1","Stopage2","Stopage3","stopage4","stopage5","Destinaltion"},0);
  JScrollPane jsp2;
  String combostr="",inputstr="",tno,tname,tsrc,tdest;

  
  public route()
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
	 display();
	 off();
	 r_bsave.setEnabled(false);
	 flightdetails();
	 setTitle("Route");
	 setLayout(null);
     setSize(1350,680);
     setLocation(10,52);
     setVisible(true);
	 r_fno.setFont(myFont);
	 r_fname.setFont(myFont);
	 r_src.setFont(myFont);
	 r_dest.setFont(myFont);
	 r_sel.setFont(myFont);
	 r_search.setFont(myFont);
	 r_bnew.setFont(myFont1);
     r_bnew.setForeground(Color.RED);
	 r_bsave.setFont(myFont1);
     r_bsave.setForeground(Color.RED);
	 r_bedit.setFont(myFont1);
     r_bedit.setForeground(Color.RED);
	 r_bdel.setFont(myFont1);
     r_bdel.setForeground(Color.RED);
	 r_bcancle.setFont(myFont1);
     r_bcancle.setForeground(Color.RED);
	  add(Head);
	 add(r_fno);
	 add(r_combo);
	 add(r_sel);
	 add(r_search);
	 add(r_tfno);
	 add(r_fname);
	 add(r_tfname);
	 add(r_src);
	 add(r_tsrc);
	 add(r_dest);
	 add(r_tdest);
	 add(r_tsearch);
	 add(r_bnew);
	 add(r_bsave);
	 add(r_bdel);
	 add(r_bedit);
	 add(r_bcancle);
	
 Head.setBounds(520,10,400,35);
		Head.setFont(new Font("Serif",Font.BOLD,28));
		Head.setForeground(Color.RED);
	 r_combo.addItem("Flight_No");
     r_combo.addItem("Source");
     r_combo.addItem("Destination");
	 r_tsrc.setBounds(160,90,120,28);
     r_src.setBounds(70,85,90,25);
     r_dest.setBounds(70,120,90,25);
     r_tdest.setBounds(160,125,120,28);
	 r_fno.setBounds(70,50,80,25);
	 r_sel.setBounds(650,50,100,25);
	 r_combo.setBounds(730,55,100,20);
	 r_search.setBounds(860,50,100,28);
	 r_tsearch.setBounds(930,55,100,28);
	 r_tfno.setBounds(160,50,70,28);
	 r_fname.setBounds(235,50,120,25);
	 r_tfname.setBounds(340,50,120,28);
	 r_bnew.setBounds(400,530,120,35);
	 r_bsave.setBounds(530,530,120,35);
	 r_bdel.setBounds(660,530,140,35);
     r_bedit.setBounds(810,530,120,35);
	 r_bcancle.setBounds(940,530,130,35);
	 r_tsearch.getDocument().addDocumentListener(this);
	 r_bnew.addActionListener(this);
     r_bsave.addActionListener(this);
	 r_bdel.addActionListener(this);
	 r_bedit.addActionListener(this);
	 r_bcancle.addActionListener(this);
	 table1.addMouseListener(this);
	 table2.addMouseListener(this);
	 add(table2);
	 add(table1);
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
	 jsp1.setBounds(650,120,470,150);
	 jsp2 = new JScrollPane(table2,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	 table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	 table2.getColumnModel().getColumn(0).setPreferredWidth(70);
	 table2.getColumnModel().getColumn(1).setPreferredWidth(120);
	 for(int i=2;i<=7;i++)
	  	table2.getColumnModel().getColumn(i).setPreferredWidth(110);
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
	 jsp2.setBounds(470,280,808,220);
	 add(back1);
	 back1.setBounds(0,0,1350,680);
   }
 public void actionPerformed(ActionEvent e)
  {
    if(e.getSource()==r_bnew)
	{
	  on();
      r_bnew.setEnabled(false);
      r_bsave.setEnabled(true);	  
	}
   if(e.getSource()==r_bsave)
	{
		 save();
	} 
   if(e.getSource()==r_bdel)
         del();
    if(e.getSource()==r_bedit)
	{
	    r_bedit.setEnabled(false);
		r_bsave.setEnabled(true);
		r_bnew.setEnabled(false);
		on();
		flgedit=1;
	}		 
  if(e.getSource()==r_bcancle)
        this.dispose();
  }
void show1()
 {         
    int y=170;
	for(int i=1;i<=5;i++) 
	{
      	  stop[i]=new JLabel("Stopage "+i); 
		  stop[i].setFont(myFont);
		  stop[i].setBounds(80,y,80,30);
		  place[i] = new JTextField("");
		   
		  place[i].setBounds(160,y,100,30);
		  y=y+40;
          add(stop[i]);
		  add(place[i]);
    }
 }
 void off()
 {
	for(int i=1;i<=5;i++) 
	{
		 place[i].setEditable(false);
	}		
 }
 void on()
 {
	 for(int i=1;i<=5;i++) 
	{
		 place[i].setEditable(true);
	}
 }
void flightdetails()
 {
   model1.setRowCount(0);
   try
 	{
	  inputstr=r_tsearch.getText();
      combostr=(String)r_combo.getSelectedItem();
	  if(inputstr.equals(""))
	     res=st.executeQuery("Select * from flight where f_type='International'");
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
void clr()
 {
	 r_tfname.setText("");
	 r_tfno.setText("");
	 r_tsrc.setText("");
	 r_tdest.setText("");
	 for(int i=1;i<=5;i++)
		 place[i].setText("");
 }
void display()
 {
  clr();   
  model2.setRowCount(0);	    
  try
  {
	    res=st.executeQuery("Select * from route ");
		if(res.next())
         {
           do
		      {
	 		      model2.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(5),res.getString(6),res.getString(7),res.getString(8),res.getString(9),res.getString(4)}); 
	          }while(res.next());
		      table2.setModel(model2);
		 }
	  }
      catch(Exception e)
	  {} 
 }
void save()
 {
	try
	 {
	  if(r_tfno.getText().equals("")||r_tfname.getText().equals("")||r_tsrc.getText().equals("")||r_tdest.getText().equals("") )
	  	JOptionPane.showMessageDialog(null,"please fill data ");
      if(flgedit==1)
	  {
		  edit();
	  }
	  else
	   {
          rr=st.executeQuery("select * from route where r_fno='"+r_tfno.getText()+"' and r_fname='"+r_tfname.getText()+"' and r_src='"+r_tsrc.getText()+"' and r_dst='"+r_tdest.getText()+"'");
		  ctr=0;
		  while(rr.next())
		  {
		    ctr++;
		  }
		 if(ctr==0)
		  {
   		     res=st.executeQuery("select * from route");
             String str= "insert into route(r_fno,r_fname,r_src,r_dst,r_stp1,r_stp2,r_stp3,r_stp4,r_stp5)values(?,?,?,?,?,?,?,?,?)";
		     PreparedStatement ps = con.prepareStatement(str);
		     ps.setString(1,r_tfno.getText());
		     ps.setString(2,r_tfname.getText());
			 ps.setString(3,r_tsrc.getText());
		     ps.setString(4,r_tdest.getText());
		     ps.setString(5,place[1].getText());
		     ps.setString(6,place[2].getText());
		     ps.setString(7,place[3].getText());
		     ps.setString(8,place[4].getText());
	         ps.setString(9,place[5].getText());
		     ps.executeUpdate();
             JOptionPane.showMessageDialog(null,"Saved Successfull");
		   	 r_tfno.setEnabled(true);
			 r_tfname.setEnabled(true);
			 r_tsrc.setEnabled(true);
			 r_tdest.setEnabled(true);
	         display();
		  }
		 else
			  JOptionPane.showMessageDialog(null,"Already present");
	   }
	} 
	catch(Exception x)
	  {
		  System.out.println(x);
	  }
    r_bsave.setEnabled(false);
    r_bnew.setEnabled(true);	  
 }
void edit()
 {
   try
	 {
	   if(r_tfno.getText().equals("")||r_tfname.getText().equals("")||r_tsrc.getText().equals("")||r_tdest.getText().equals("") )
	      JOptionPane.showMessageDialog(null,"please fill data ");
       else
		 {
		   st.executeUpdate("update route set r_stp1='"+place[1].getText()+"',r_stp2='"+place[2].getText()+"',r_stp3='"+place[3].getText()+"',r_stp4='"+place[4].getText()+"',r_stp5='"+place[5].getText()+"' where r_fno='"+tno+"' and r_fname='"+tname+"' and r_src ='"+tsrc+"' and r_dst='"+tdest+"'");
		   JOptionPane.showMessageDialog(null,"Updated successfully!!!");
		   r_tfno.setEnabled(true);
		   r_tfname.setEnabled(true);
		   r_tsrc.setEnabled(true);
		   r_tdest.setEnabled(true);
		   display();
		 }
	  }
   catch(Exception ex)
	 {}
	 r_bedit.setEnabled(true);
	 r_bsave.setEnabled(false);
	 r_bnew.setEnabled(true);
	 flgedit=0;
 }
 void del()
   {
	  if(r_tfno.getText().equals("")||r_tfname.getText().equals("")||r_tsrc.getText().equals("")||r_tdest.getText().equals(""))
			JOptionPane.showMessageDialog(null,"Not Enough Details!!!");
	  else
		  {
			int cnfb = JOptionPane.YES_NO_OPTION;
			int cnf = JOptionPane.showConfirmDialog(null,"Would You Like To Delete ?","warning" ,cnfb );
			if(cnf==0)
			  {
                try
			      {
			        st.executeUpdate("delete from route where r_fno='"+tno+"' and r_fname='"+tname+"' and r_src='"+tsrc+"' and r_dst='"+tdest+"'");
					JOptionPane.showMessageDialog(null,"Deleted successfully!!!");
					r_tfno.setEditable(true);
					r_tfname.setEditable(true);
					r_tsrc.setEditable(true);
					r_tdest.setEditable(true);
					display();
			      }
			    catch(Exception ex)
			     {}
			  }
		   }
   }
 public void mouseClicked(MouseEvent e)
 {
   if(e.getSource()==table1)
	{
	  int row=table1.getSelectedRow();
	  r_tfno.setText(model1.getValueAt(row,0).toString());
	  r_tfname.setText(model1.getValueAt(row,1).toString());
	  r_tsrc.setText(model1.getValueAt(row,3).toString());
	  r_tdest.setText(model1.getValueAt(row,4).toString());
	  tno = r_tfno.getText();
	  tname = r_tfname.getText();
	  tsrc = r_tsrc.getText();
	  tdest = r_tdest.getText();
	  r_tfno.setEditable(false);
      r_tfname.setEditable(false);
	  r_tsrc.setEditable(false);
	  r_tdest.setEditable(false);
	}
  else
	{
      int row1=table2.getSelectedRow();	 
      int k=1;	
      r_tfno.setText(model2.getValueAt(row1,0).toString());
	  r_tfname.setText(model2.getValueAt(row1,1).toString());
	  r_tsrc.setText(model2.getValueAt(row1,2).toString());
	  r_tdest.setText(model2.getValueAt(row1,8).toString());
      tno = r_tfno.getText();
	  tname = r_tfname.getText();
	  tsrc = r_tsrc.getText();
	  tdest = r_tdest.getText();	  
      for(int i=3;i<=7;i++)
	   {
		  place[k].setText(model2.getValueAt(row1,i).toString());
		  k++;
       }
	 
	  
	  r_tfno.setEditable(false);
	  r_tfname.setEditable(false);
	  r_tsrc.setEditable(false);
	  r_tdest.setEditable(false);
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
      route obj=new route();
  }
}
