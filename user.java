import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import net.sourceforge.jdatepicker.*; 
import net.sourceforge.jdatepicker.graphics.*; 
import net.sourceforge.jdatepicker.impl.*; 
import net.sourceforge.jdatepicker.util.*;
import java.text.DateFormat; 
import java.text.SimpleDateFormat;
import java.util.Calendar; 
import java.util.Date;
public class user extends JFrame implements ActionListener,DocumentListener
{
    Connection con;
    ResultSet res,res1,rr;
    Statement st,st1;
	public UtilDateModel model;
    public JDatePanelImpl datePanel;
    public JDatePickerImpl datePicker;
    JLabel Head = new JLabel("User Information Form");
	JLabel lname = new JLabel("Name ");
	JLabel lgen = new JLabel("Gender ");
	JLabel ldob = new JLabel("DOB ");
	JLabel ladd = new JLabel("Address ");
	JLabel lmob = new JLabel("Contact ");
	JLabel luser = new JLabel("User ID ");
	JLabel lpass = new JLabel("Password ");
	JLabel lpass1 = new JLabel("Re Password ");
	JLabel ltype = new JLabel("User Type ");
	JLabel image = new JLabel();
	JTextField txname = new JTextField();
	JTextField txmob = new JTextField();
	JTextField txuser = new JTextField();
	JPasswordField password = new JPasswordField();
	JPasswordField cnfpassword = new JPasswordField();
	JTextArea txadd=new JTextArea();
	 JLabel back1 =new JLabel(new ImageIcon(ClassLoader.getSystemResource("icon/login1.png")));
	JButton save = new JButton("SAVE",new ImageIcon(ClassLoader.getSystemResource("icon/Save2.png")));
	JButton bnew = new JButton("NEW",new ImageIcon(ClassLoader.getSystemResource("icon/adduser.png")));
	JRadioButton b1=new JRadioButton("Male");
	JRadioButton b2=new JRadioButton("Female");
	ButtonGroup g=new ButtonGroup();
    JLabel l1=new JLabel(new ImageIcon("3.jpg"));
	Font myFont = new Font("Lucida Fax", Font.BOLD,14);
    Font myFont1 = new Font("Lucida Console", Font.BOLD,16);
	Choice type;
	
    public user()
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
	
	 Border border=LineBorder.createGrayLineBorder();
	 setTitle("Registration");
	 setLayout(null);
     setSize(1350,680);
     setLocation(10,52);
     setVisible(true);
	 model = new UtilDateModel();  
        datePanel = new JDatePanelImpl(model);  
        datePicker = new JDatePickerImpl(datePanel);
	 type=new Choice();
		type.add("Select");
		type.add("Administrator");
		type.add("Employee");
		add(datePicker);
		add(Head);
		add(txname);
		ladd.setFont(myFont);
		ldob.setFont(myFont);
		lgen.setFont(myFont);
		lmob.setFont(myFont);
		lname.setFont(myFont);
		txname.setFont(myFont);
		b1.setFont(myFont);
		b2.setFont(myFont);
		txmob.setFont(myFont);
		txadd.setFont(myFont);
		txuser.setFont(myFont);
		lpass1.setFont(myFont);
		lpass.setFont(myFont);
		ltype.setFont(myFont);
		luser.setFont(myFont);
	    bnew.setFont(myFont1);
       bnew.setForeground(Color.RED);
	   save.setFont(myFont1);
        save.setForeground(Color.RED);
		g.add(b1);
		g.add(b2);
		add(b1);
		b1.setSelected(true);
		add(b2);
		add(txmob);
		add(txadd);
		add(save);
		add(bnew);
		add(lname);
		add(lgen);
		add(lmob);
		add(ldob);
		add(ladd);
		add(luser);
		add(lpass);
		add(lpass1);
		add(ltype);
		add(txuser);
		add(password);
		add(cnfpassword);
		add(type);
        add(image);
		add(l1);
		Head.setBounds(300,30,400,30);
		Head.setFont(new Font("Serif",Font.BOLD,28));
		Head.setForeground(Color.RED);
		lname.setBounds(130,150,200,20);
		lgen.setBounds(130,195,200,20);
		ldob.setBounds(130,245,100,20);
		lmob.setBounds(130,300,200,20);
		ladd.setBounds(130,350,100,20);
		luser.setBounds(540,145,100,20);
		lpass.setBounds(540,195,100,20);
		lpass1.setBounds(540,245,100,20);
		ltype.setBounds(540,295,100,20);
		txname.setBounds(240,145,200,30);
		b1.setBounds(240,195,100,30);
		b2.setBounds(350,195,90,30);
		txmob.setBounds(240,295,200,30);
		txadd.setBounds(240,345,200,100);
		txadd.setBorder(border);
		save.setBounds(240,500,120,35);
		bnew.setBounds(390,500,120,35);
		txuser.setBounds(650,145,200,30);
		password.setBounds(650,195,200,30);
		cnfpassword.setBounds(650,245,200,30);
		type.setBounds(650,295,200,20);
		datePicker.setBounds(240,245,200,26);
        l1.setBounds(0,0,1000,700);
		image.setBounds(855,245,32,32);
		bnew.addActionListener(this);
		save.addActionListener(this);
		add(back1);
		back1.setBounds(0,0,1350,680);
		 cnfpassword.getDocument().addDocumentListener(this);
	 }
	 public void actionPerformed(ActionEvent e)
	{		
		if(e.getSource()==bnew)
		{
			this.dispose();
			user obj=new user();
		}
		if(e.getSource()==save)
		{
          save();
		}
	}
	void save()
   {
	 try
		 {
			b1.setActionCommand("Male");
			b2.setActionCommand("Female");
			String gen=g.getSelection().getActionCommand();
			String types=type.getSelectedItem();
			Date selectedDate =(Date) datePicker.getModel().getValue();
            DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
	        String reportDate = df.format(selectedDate);
		  if(txuser.getText().equals("")||txmob.getText().equals("")||txadd.getText().equals("")||String.valueOf(password.getPassword()).equals("")||String.valueOf(cnfpassword.getPassword()).equals("")||types.equals("Select"))
		       JOptionPane.showMessageDialog(null,"Not Enough Details");  
		  else
		  {
			      res=st.executeQuery("select * from login");
                  String str= "insert into login(userid,pass,type,name,gender,dob,contact,address)values(?,?,?,?,?,?,?,?)";
		          PreparedStatement ps = con.prepareStatement(str);
		          ps.setString(1,txuser.getText());
		          ps.setString(2,String.valueOf(cnfpassword.getPassword()));
				  ps.setString(3,types);
		          ps.setString(4,txname.getText());
		          ps.setString(5,gen);
		          ps.setString(6,reportDate);
		          ps.setString(7,txmob.getText());
		          ps.setString(8,txadd.getText());
		          ps.executeUpdate();
		          JOptionPane.showMessageDialog(null,"Saved Successfull");
		         
		         }
		    }
	    catch(Exception x)
	  {
		JOptionPane.showMessageDialog(null," User ID Allready Present","Alart",JOptionPane.WARNING_MESSAGE ); 
	  }	  
	}
	void  compare()
	{
	   String p=String.valueOf(password.getPassword());
	   String p1=String.valueOf(cnfpassword.getPassword());
	   save.setEnabled(false);
	   image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Delete.png")));
	   if(p.equals(p1))
	   {
		   save.setEnabled(true);
		   image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png")));
	   }
		
	}
 public void insertUpdate(DocumentEvent e)
  {
    compare();
  }
public void removeUpdate(DocumentEvent e)
  {
     compare();
  }
public void changedUpdate(DocumentEvent e)
  {
     compare();
  }
	 public static void main(String argv[])throws IOException
  {
      user obj=new user();
  }
}