import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.event.*;
import javax.swing.table.*;
public class login extends JFrame implements ActionListener
{
    Connection con;
    ResultSet res,res1,rr;
    Statement st,st1;
	JTextField userText = new JTextField(20);
	JPasswordField passwordText = new JPasswordField(20);
	JButton loginButton = new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/sign1.png")));
	JButton closeButton = new JButton(new ImageIcon(ClassLoader.getSystemResource("icon/123.png")));
	Choice loginType;
	int flg=0;
	String str1,str2,str3,str4,str5,str6;
	JLabel back=new JLabel(new ImageIcon(ClassLoader.getSystemResource("icon/login.png")));
    public login()
     {
	    new expire();
		try
         {
	       con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "SYSMAN", "Welcome123");
	       st = con.createStatement();
         }
	   catch(Exception e)
	     {System.out.println("exception in connecting db : " + e);} 
		setLayout(null);
        setSize(394,233);
		setUndecorated(true);
		setLocation(486,268);
        setVisible(true);
	    setTitle("Flight Details"); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    loginType=new Choice();
	    loginType.add("Administrator");
		loginType.add("Employee");
		closeButton.setContentAreaFilled(false);
		add(userText);
		userText.requestFocus();
	    add(passwordText);
	    add(loginButton);
	    add(closeButton);
	    add(back);
	    back.setBounds(0,0,394,233);
	    loginType.setBounds(130,60,120,30);
        userText.setBounds(210, 90, 130, 30); 
        passwordText.setBounds(210, 135, 130, 30);
        loginButton.setBounds(225, 175, 115, 38);
	    closeButton.setBounds(356, 5, 32, 32);
        loginButton.addActionListener(this);
        closeButton.addActionListener(this);
   }
   public void actionPerformed(ActionEvent e)
    {
		if(e.getSource()==closeButton)
		{
			System.exit(0);
		}
    if(e.getSource()==loginButton)
	  {
		  String check=loginType.getSelectedItem();
               
		 try
		 {
		 res=st.executeQuery("SELECT login.* FROM login");
		  
		 if(res.next())
         {
			
           do
		      {
                      str1=userText.getText();
					  str2=res.getString(1);
					  str4=res.getString(2);
					  
					  char pass[] = passwordText.getPassword();
                      String str3 = new String(pass);
					 
				      if((str1.equals(str2))&&(str3.equals(str4)))
				      {
					    flg=1;
					    str5=res.getString(3);
						  str6=res.getString(4);
					    save();
					   String[] arguments = new String[] {};
						ProgressBar.main(arguments);
						this.dispose();
					
				}	
	 		  	  }while(res.next());
		   }
		 }
	  catch(Exception ex)
	  {
		 
	  }
	  if(flg==0)
		JOptionPane.showMessageDialog(null,"Invalid User ID or Password","Alert",JOptionPane.WARNING_MESSAGE);	
	}
	}
void save()
	{
	  try
	   {
		   st.executeUpdate("update temp set lname='"+str6+"',logintype='"+str5+"'");
		 }
	   catch(Exception ex)
	    {			
		}
    }
 public static void main(String args[])throws IOException
 {
   login obj = new login(); 
  }
 
}