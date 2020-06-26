import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.beans.*;
import java.util.Random;
 
public class ProgressBar extends JPanel implements ActionListener, PropertyChangeListener
 {
 
    private JProgressBar progressBar;
    private JButton startButton;
    private Task task;
    class Task extends SwingWorker<Void, Void> 
	{
        @Override
        public Void doInBackground()
		{
            Random random = new Random();
            int progress = 0;
           setProgress(0);
            while (progress < 100) {
                        try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException ignore) {}
               
                progress += random.nextInt(10);
                setProgress(Math.min(progress, 100));
            }
            return null;
        }
 
        
        @Override
        public void done() {
            Toolkit.getDefaultToolkit().beep();
            setCursor(null); 
		    new menu();  
			
          
        }
    }
 
    public ProgressBar() {
        super(new BorderLayout());
 
             
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
		progressBar.setStringPainted(true);
       JPanel panel = new JPanel();
         panel.add(progressBar);
        add(panel, BorderLayout.PAGE_START);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
         task = new Task();
        task.addPropertyChangeListener(this);
        task.execute();
	
     }
     public void actionPerformed(ActionEvent evt)
	 {
     }
      public void propertyChange(PropertyChangeEvent evt) {
        if ("progress" == evt.getPropertyName()) {
            int progress = (Integer) evt.getNewValue();
            progressBar.setValue(progress);
            
        } 
    }
 
 
    
    private static void createAndShowGUI()
	{
        JFrame frame = new JFrame("ProgressBarDemo");
		frame.setUndecorated(true);
		frame.setLocation(640,384);
        JComponent newContentPane = new ProgressBar();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);        
        frame.pack();
        frame.setVisible(true);
		
    }
 
    public static void main(String[] args)
	{
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
		
    }
}