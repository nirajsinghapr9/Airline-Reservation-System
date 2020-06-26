import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class aboutus extends JWindow implements ActionListener 
{
   public aboutus()
  {
    setLayout(null);
	JPanel content = (JPanel)getContentPane();
    content.setBackground(Color.white);
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation(450,200);
	setSize(412,397);
    JLabel label = new JLabel(new ImageIcon("icon/logo.png"));
	label.setBounds(20,20,100,100);
	
	JLabel date = new JLabel("Build Date : 22-02-2016 ");
	date.setBounds(250,5,200,15);
	date.setForeground(new Color(192,192,192));
	content.add(date);
	JLabel name = new JLabel("Flying Bird Airline Ticket" );
	JLabel name2 = new JLabel("  Reservation System");
	name.setForeground(new Color(0,0,255));
	name.setFont(new Font("Courier", Font.CENTER_BASELINE, 16));
	content.add(name);
	name2.setBounds(125,65,400,35);
	name2.setForeground(new Color(0,0,255));
	name2.setFont(new Font("Courier", Font.CENTER_BASELINE, 16));
	content.add(name2);
	name.setBounds(125,40,400,35);
    content.add(label, BorderLayout.CENTER);
	JTextArea dvlp = new JTextArea("Guided By:- Prof. Ratna pandey\nDevloped By:-\n	1.Pawan Kr. Mahto\n	2. Ankur Gupta\n	3.Rupa Singh\n	4.Rekha Kumari");
    dvlp.setBounds(5,140,300,120);
	content.add(dvlp);
	dvlp.setFont(new Font("Serif", Font.BOLD,14));
	dvlp.setForeground(new Color(0,0,0));
	JTextArea area = new JTextArea("This program is free software,This program is distributed in \nthe hope that it will be useful for learning, but WITHOUT\n ANY WARRANTY");
    area.setBounds(30,270,330,50);
	content.add(area);
	area.setForeground(new Color(0,0,0));
	JButton ok = new JButton("ok");
	ok.setBounds(180,330,70,25);
	content.add(ok);

	Color oraRed = new Color(0,0,0);
    content.setBorder(BorderFactory.createLineBorder(oraRed, 2));
    setVisible(true);
	ok.addActionListener(this);
    try 
     {
           new expire();	  
	 
      } 
    catch (Exception e) 
    {	
    }
  }
	public void actionPerformed(ActionEvent e)
     {
		 this.dispose();
	 } 
 
  public static void main(String[] args) 
  {
    aboutus splash = new aboutus();
 
  }
}