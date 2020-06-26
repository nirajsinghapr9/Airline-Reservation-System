import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.event.*;
import javax.swing.table.*;
public class flight extends JFrame implements ActionListener , ItemListener,MouseListener,DocumentListener
{
  Connection con;
  ResultSet res;
  Statement st;
  int flg=0;
   JLabel Head = new JLabel("Flight Information Form");
  ButtonGroup ch1=new ButtonGroup();
  JComboBox f_combo=new JComboBox();
  JRadioButton f_dm = new JRadioButton("Domestics");
  JRadioButton f_int = new JRadioButton("International");
  JLabel f_search= new JLabel("Search");
  JLabel f_no=new JLabel("Flight No");
  JLabel f_name=new JLabel("Flight Name");
  JLabel f_ty= new JLabel("Flight Type");
  JLabel f_srce=new JLabel("Flight Source");
  JLabel f_dest=new JLabel("Flight Destination");
  JLabel f_exc=new JLabel("Executive Seat");
  JLabel f_eco=new JLabel("Economy Seat");
  JLabel f_excfare=new JLabel("Executive Fare");
  JLabel f_ecofare = new JLabel("Economy Fare");
  JLabel f_sel = new JLabel("Select");
  JTextField  f_tsearch=new JTextField();
  JTextField f_tno=new JTextField();
  JTextField f_tname=new JTextField();
  JTextField f_tsrce=new JTextField();
  JTextField f_tdest=new JTextField(10);
  JTextField f_texc=new JTextField(10);
  JTextField f_teco=new JTextField(10);
  JTextField f_texcfare=new JTextField(10);
  JTextField f_tecofare = new JTextField(10);
  JLabel back1 =new JLabel(new ImageIcon(ClassLoader.getSystemResource("icon/s2.jpg")));
  JButton f_bnew=new JButton("New",new ImageIcon(ClassLoader.getSystemResource("icon/new1.png")));
  JButton f_bsave=new JButton("Save",new ImageIcon(ClassLoader.getSystemResource("icon/save1.png")));
  JButton f_bdel=new JButton("Delete",new ImageIcon(ClassLoader.getSystemResource("icon/delete1.png")));
  JButton f_bedit=new JButton("Edit",new ImageIcon(ClassLoader.getSystemResource("icon/edit1.png")));
  JButton f_bcancle=new JButton("Cancel",new ImageIcon(ClassLoader.getSystemResource("icon/cancel1.png")));
  JButton f_refresh = new JButton("Refresh",new ImageIcon(ClassLoader.getSystemResource("icon/refresh1.png")));
  JTable table = new JTable()
  {
	  public boolean isCellEditable(int rowIndex,int colIndex)
      {
	    return false;
	  }
  };
  DefaultTableModel model = new DefaultTableModel(new String[]{"Flight No", "Flight Name","Type","Source", "Destination", "Exc Seat","Eco Seat","Exc Fare(Rs)","Eco Fare(Rs)"},0);
  JScrollPane jsp;
  String combostr="",inputstr="",tno,tname,texc,teco,tsrc,tdst,texcf,tecof;
  Font myFont = new Font("Lucida Fax", Font.BOLD,14);
  Font myFont1 = new Font("Serif", Font.BOLD,18);
  public flight()
   {
        new expire();
		try
         {
	       con =DriverManager.getConnection("jdbc:odbc:mydsn");
		   st = con.createStatement();
         }
	    catch(Exception e)
	     {}
		newclr();
		display();
		eoff();
	    setLayout(null);
        setSize(1350,680);
		setLocation(10,52);
        setVisible(true);
	    setTitle("Flight Details");
		ch1.add(f_int);
	    ch1.add(f_dm);
		f_bnew.setFont(myFont1);
		f_bnew.setForeground(Color.RED);
		f_bsave.setFont(myFont1);
		f_bsave.setForeground(Color.RED);
		f_bedit.setFont(myFont1);
		f_bedit.setForeground(Color.RED);
		f_bdel.setFont(myFont1);
		f_bdel.setForeground(Color.RED);
		f_bcancle.setFont(myFont1);
		f_bcancle.setForeground(Color.RED);
		f_refresh.setFont(myFont1);
		f_refresh.setForeground(Color.RED);
		f_no.setFont(myFont);
		f_name.setFont(myFont);
		f_ty.setFont(myFont);
		f_srce.setFont(myFont);
		f_dest.setFont(myFont);
		f_exc.setFont(myFont);
		f_eco.setFont(myFont);
		f_excfare.setFont(myFont);
		f_ecofare.setFont(myFont);
		f_sel.setFont(myFont);
		f_search.setFont(myFont);
		f_bnew.setToolTipText("Add New Flight Information");
		f_bsave.setToolTipText("Save  ");
		f_bedit.setToolTipText("Edit Records");
		f_bdel.setToolTipText("Delete Records ");
		f_bcancle.setToolTipText("Close Window");
		f_refresh.setToolTipText("Refresh Fields");
		add(Head);
		add(f_int);
		add(f_dm);
        add(f_no);
	    add(f_tno);
        add(f_name);
	    add(f_tname);
		add(f_ty);
	    add(f_srce);
	    add(f_tsrce);
	    add(f_dest);
	    add(f_tdest);
	    add(f_exc);
	    add(f_texc);
	    add(f_eco);
	    add(f_teco);
	    add(f_excfare);
	    add(f_texcfare);
	    add(f_ecofare);
	    add(f_tecofare);
	    add(f_bnew);
	    add(f_bsave);
	    add(f_bdel);
	    add(f_bedit);
	    add(f_bcancle);
	    add(f_refresh);
	    add(f_combo);
	    add(f_search);
	    add(f_sel);
	    add(f_tsearch);
	    add(f_combo);
	    add(table);
	    add(f_refresh);
		
        Head.setBounds(450,10,400,35);
		Head.setFont(new Font("Serif",Font.BOLD,28));
		Head.setForeground(Color.RED);
		
	    f_no.setBounds(70,70,150,35);
        f_tno.setBounds(210,75,120,30);
		f_name.setBounds(70,110,150,35);
        f_tname.setBounds(210,115,120,30);
        f_ty.setBounds(70,160,150,35);
		f_dm.setBounds(210,152,120,27);
        f_int.setBounds(210,184,120,27);
	    f_srce.setBounds(70,215,150,35);
        f_tsrce.setBounds(210,220,120,30);
	    f_dest.setBounds(70,255,150,30);
        f_tdest.setBounds(210,255,120,30);
	    f_exc.setBounds(70,290,150,35);
        f_texc.setBounds(210,295,120,30);
	    f_eco.setBounds(70,330,150,35);
        f_teco.setBounds(210,335,120,30);
	    f_excfare.setBounds(70,370,150,35);
        f_texcfare.setBounds(210,375,120,30);
	    f_ecofare.setBounds(70,410,150,35);
	    f_tecofare.setBounds(210,415,120,30);
	    f_bnew.setBounds(380,550,120,35);
        f_bsave.setBounds(510,550,120,35);
	    f_bdel.setBounds(640,550,120,35);
        f_bedit.setBounds(780,550,130,35);
	    f_bcancle.setBounds(930,550,130,35);
	    f_search.setBounds(760,65,70,35);
	    f_tsearch.setBounds(820,70,120,30);
		f_sel.setBounds(560,65,70,35);
        f_combo.setBounds(630,70,100,25);
        f_refresh.setBounds(970,70,140,35);
		f_combo.addItem("Flight_No");
        f_combo.addItem("Source");
        f_combo.addItem("Destination");
		f_bnew.addActionListener(this);
        f_bsave.addActionListener(this);
	    f_bdel.addActionListener(this);
	    f_bedit.addActionListener(this);
	    f_bcancle.addActionListener(this);
	    f_refresh.addActionListener(this);
	    table.addMouseListener(this);
		
		f_tsearch.getDocument().addDocumentListener(this);
		jsp = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(81);
		table.getColumnModel().getColumn(4).setPreferredWidth(81);
		table.getColumnModel().getColumn(5).setPreferredWidth(70);
		table.getColumnModel().getColumn(6).setPreferredWidth(70);
		table.getColumnModel().getColumn(7).setPreferredWidth(90);
	    table.getColumnModel().getColumn(8).setPreferredWidth(90);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);    
        table.setFillsViewportHeight(true);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        table.setGridColor(new Color(0,0,0));
        JTableHeader header = table.getTableHeader();
        table.setBackground(new Color(213,255,213));
        table.setEnabled(true);
        header.setFont(new Font("Courier", Font.CENTER_BASELINE, 12));
        header.setBackground(Color.black);
        header.setForeground(new Color(255,255,47));
	    add(jsp);
 		setVisible(true);
		jsp.setBounds(480,150,790,300);
		add(back1);
		
		back1.setBounds(0,0,1350,680);
    }
  
	
public void actionPerformed(ActionEvent e)
  {
    if(e.getSource()==f_bnew)
     {
	   newclr();
	   display();
	   eon();
	   f_bsave.setEnabled(true);
	   f_bnew.setEnabled(false);
     }
	if(e.getSource()==f_refresh)
     {
	   f_tsearch.setText("");
	   newclr();
	   display();
     }
    if(e.getSource()==f_bsave)
        save();    
    if(e.getSource()==f_bdel)
        del();
    if(e.getSource()==f_bedit)
	 {
      flg=1;
	  f_bedit.setEnabled(false);
	  f_bsave.setEnabled(true);
	 }	
   if(e.getSource()==f_bcancle)
        this.dispose();
  }
 void eoff()
  {
	f_tname.setEnabled(false);
	f_tno.setEnabled(false);
	f_tsrce.setEnabled(false);
	f_tdest.setEnabled(false);
	f_teco.setEnabled(false);
	f_texc.setEnabled(false);
	f_tecofare.setEnabled(false);
	f_texcfare.setEnabled(false);
	f_bsave.setEnabled(false);
  }
 void eon()
  {
	f_tname.setEnabled(true);
	f_tno.setEnabled(true);
	f_tsrce.setEnabled(true);
	f_tdest.setEnabled(true);
	f_teco.setEnabled(true);
	f_texc.setEnabled(true);
	f_tecofare.setEnabled(true);
	f_texcfare.setEnabled(true);
  }
 void display()
   {
	 newclr();
     try
	  {
		   inputstr=f_tsearch.getText();
           combostr=(String)f_combo.getSelectedItem();
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
	 		     model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8),res.getString(9)}); 
	          }while(res.next());
		      table.setModel(model);
		   }
	    }
     catch(Exception e)
	   {}
   }
 void newclr()
  {
	f_tno.setText("");
	f_tname.setText("");
	f_tsrce.setText("");
	f_tdest.setText("");
	f_texc.setText("");
	f_teco.setText("");
	f_texcfare.setText("");
	f_tecofare.setText("");
	model.setRowCount(0);
  }
 void save()
   {
	 try
		 {
		  if(f_tno.getText().equals("")||f_tname.getText().equals("")||f_tsrce.getText().equals("")||f_tdest.getText().equals("")||f_texc.getText().equals("")||f_teco.getText().equals("")||f_texcfare.getText().equals("")||f_tecofare.getText().equals(""))
		       JOptionPane.showMessageDialog(null,"Not Enough Details!!!");  
		  if(flg==1)
		  {
			  edit();
		  }
		  else
		  {
			   char ch;
			   for(char i='A';i<='Z';i++)
			     {
				   f_texc.setText(f_texc.getText().replace(Character.toString(i),""));
				   f_texc.setText(f_texc.getText().replace(Character.toString((char)(i+32)),""));
				   f_teco.setText(f_teco.getText().replace(Character.toString(i),""));
				   f_teco.setText(f_teco.getText().replace(Character.toString((char)(i+32)),""));
				   f_texcfare.setText(f_texcfare.getText().replace(Character.toString(i),""));
				   f_texcfare.setText(f_texcfare.getText().replace(Character.toString((char)(i+32)),""));
				   f_tecofare.setText(f_tecofare.getText().replace(Character.toString(i),""));
				   f_tecofare.setText(f_tecofare.getText().replace(Character.toString((char)(i+32)),""));
			     }
			   if(f_texc.getText().equals("")||f_teco.getText().equals("")||f_texcfare.getText().equals("")||f_tecofare.getText().equals(""))
			     {
				   JOptionPane.showMessageDialog(null,"Invalid Fill"); 
			     }				
		       else
		         {
		          f_dm.setActionCommand("Domestics");
                  f_int.setActionCommand("International");
			      String type=ch1.getSelection().getActionCommand();
				  res=st.executeQuery("select * from flight");
                  String str= "insert into flight(f_no,f_name,f_type,f_src,f_dst,f_excs,f_ecos,f_excf,f_ecof)values(?,?,?,?,?,?,?,?,?)";
		          PreparedStatement ps = con.prepareStatement(str);
		          ps.setString(1,f_tno.getText());
		          ps.setString(2,f_tname.getText());
				  ps.setString(3,type);
		          ps.setString(4,f_tsrce.getText());
		          ps.setString(5,f_tdest.getText());
		          ps.setString(6,f_texc.getText());
		          ps.setString(7,f_teco.getText());
		          ps.setString(8,f_texcfare.getText());
		          ps.setString(9,f_tecofare.getText());
		          ps.executeUpdate();
		          JOptionPane.showMessageDialog(null,"Saved Successfull");
		          display();
				  eoff();
				  f_bsave.setEnabled(false);
				  f_bnew.setEnabled(true);
		         }
		    }
		 }
	catch(Exception x)
	  {
		System.out.println(x);
		//JOptionPane.showMessageDialog(null," ID Allready Present","Alart",JOptionPane.WARNING_MESSAGE ); 
	  }	  
	}
 void del()
   {
	  if(f_tno.getText().equals("")||f_tname.getText().equals(""))
			JOptionPane.showMessageDialog(null,"Not Enough Details!!!");
	  else
		  {
			int cnfb = JOptionPane.YES_NO_OPTION;
			int cnf = JOptionPane.showConfirmDialog(null,"Would You Like To Delete ?","Warning" ,cnfb );
			if(cnf==0)
			  {
               	try
			      {
			        st.executeUpdate("delete from flight where f_no='"+tno+"'");
					JOptionPane.showMessageDialog(null,"Deleted Successfully");
					display();
			       }
			    catch(Exception ex)
			     {
					 System.out.println(ex);
				 }
			  }
		 }
  }
 void edit()
   {
	 try
	   {
		  if(f_texc.getText().equals("")||f_teco.getText().equals("")||f_texcfare.getText().equals("")||f_tecofare.getText().equals(""))
                JOptionPane.showMessageDialog(null,"Not Enough Details!!!");  
		  else
			 {
        	   char ch;
			   for(char i='A';i<='Z';i++)
			    {
				  f_texc.setText(f_texc.getText().replace(Character.toString(i),""));
				  f_texc.setText(f_texc.getText().replace(Character.toString((char)(i+32)),""));
				  f_teco.setText(f_teco.getText().replace(Character.toString(i),""));
				  f_teco.setText(f_teco.getText().replace(Character.toString((char)(i+32)),""));
				  f_texcfare.setText(f_texcfare.getText().replace(Character.toString(i),""));
				  f_texcfare.setText(f_texcfare.getText().replace(Character.toString((char)(i+32)),""));
				  f_tecofare.setText(f_tecofare.getText().replace(Character.toString(i),""));
				  f_tecofare.setText(f_tecofare.getText().replace(Character.toString((char)(i+32)),""));
			    }
			   	if(f_texc.getText().equals("")||f_teco.getText().equals("")||f_texcfare.getText().equals("")||f_tecofare.getText().equals(""))
			       JOptionPane.showMessageDialog(null,"Invalid Fill"); 
			    else
			     {
                    f_dm.setActionCommand("Domestics");
                    f_int.setActionCommand("International");
			        String type=ch1.getSelection().getActionCommand();
			        st.executeUpdate("update flight set f_name='"+f_tname.getText()+"',f_type='"+type+"',f_src='"+f_tsrce.getText()+"',f_dst='"+f_tdest.getText()+"',f_excs='"+f_texc.getText()+"',f_ecos='"+f_teco.getText()+"',f_excf='"+f_texcfare.getText()+"',f_ecof='"+f_tecofare.getText()+"' where f_no='"+tno+"'");
		            JOptionPane.showMessageDialog(null,"Updated successfully!!!");
			        display();
					f_bedit.setEnabled(true);
					f_bsave.setEnabled(false);
					f_bnew.setEnabled(true);
					flg=0;
			     }
			 }
       } 
	 catch(Exception ex)
	    {}
   }
  public void mouseClicked(MouseEvent e)
	{
		eon();
		int row=table.getSelectedRow();
		f_tno.setText(model.getValueAt(row,0).toString());
		f_tname.setText(model.getValueAt(row,1).toString());
		if(model.getValueAt(row,2).equals("Domestics"))
			f_dm.setSelected(true);
		else
			f_int.setSelected(true);
        f_tsrce.setText(model.getValueAt(row,3).toString());   
		f_tdest.setText(model.getValueAt(row,4).toString());
		f_texc.setText(model.getValueAt(row,5).toString());
		f_teco.setText(model.getValueAt(row,6).toString());
		f_texcfare.setText(model.getValueAt(row,7).toString());
		f_tecofare.setText(model.getValueAt(row,8).toString());
		tno=f_tno.getText();
		tname =f_tname.getText();
		tsrc=f_tsrce.getText();
		tdst =f_tdest.getText();
		texc=f_texc.getText();
		teco =f_teco.getText();
		texcf=f_texcfare.getText();
		tecof =f_tecofare.getText();
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
		  display();
	   }
    public void removeUpdate(DocumentEvent e)
	   {
		 display();
	   }
	public void changedUpdate(DocumentEvent e)
	   {
		 display();
	   }	
public static void main(String argv[])throws IOException
  {
      flight obj=new flight();
  }
}
  