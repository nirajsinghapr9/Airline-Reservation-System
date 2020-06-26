import java.awt.*;
import javax.swing.*;
import java.io.*;
public class flashscreen extends JWindow 
{
   public flashscreen()
  {
    JPanel content = (JPanel)getContentPane();
    content.setBackground(Color.white);
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    setBounds(370,100,700,568);
	setSize(679,525);
    JLabel label = new JLabel(new ImageIcon("icon/First1.png"));
    content.add(label, BorderLayout.CENTER);
    Color oraRed = new Color(156, 20, 20,  255);
    content.setBorder(BorderFactory.createLineBorder(oraRed, 2));
   setVisible(true);
    try 
    {
     System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	 System.out.println("~     Flying Bird Ticket Reservation System     ~");
	 System.out.println("~     Guided By: Prof. Ratna Pandey             ~");
	 System.out.println("~     Devloped By: Niraj Kumar singh               ~");
	 System.out.println("~    Indira Gandhi National Open University     ~");
	 System.out.println("~      BCA Final Year Student           ~");
	 System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	 Thread.sleep(5000);
      new expire();	  
	  new login();
	  this.dispose();
   } 
    catch (Exception e) 
    {
	System.out.println(e);
    }
  }
  public static void main(String[] args) 
  {
    flashscreen splash = new flashscreen();
 
  }
}