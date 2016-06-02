package Viewer;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * File Name: ViewerGUI.java
 * Description: This class is the GUI driver for displaying the customer's
 * reservation - their name, arrival/depart date It will automatically load
 * the database file and allow the user to search for the customer's name to
 * display their reservation.
 * Date: 6/1/2016
 * Platform: Windows 8, jdk 1.8.0_66, NetBeans 8.1
 * @author 
 */
public class ViewerGUI extends JFrame
{
    public static final int HEIGHT = 300;
    public static final int WIDTH =  600;  
    /**
     * Description: main constructor - set forms to visible, centers it and
     * makes the default close button exit the program.
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        JFrame frame = new ViewerGUI();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public ViewerGUI()
    {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(WIDTH, HEIGHT);    
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource
        ("hotel.png")));
        setTitle("Reservation Viewer");
        setFileMenu();
    }
    /**
     * Description: This method setWestPanel will layout the west side
     * of the form.
     */
    public void setWestPanel()
    {
        
    }
    /**
     * Description: This method setEastPanel will layout the east side
     * of the form.
     */
    public void setEastPanel()
    {
        
    }
    /**
     * Description: This method will create the File Menu for the user
     * to select the dictionary file if it is not already loaded.
     */
    public void setFileMenu()
    {
        menuSelector = new JMenuBar();
        helpItem = new JMenuItem("Help");
        aboutItem = new JMenuItem("About");
        quitItem  = new JMenuItem("Quit");
        fileMenu = new JMenu("File");
        fileMenu.add(helpItem);
        fileMenu.add(aboutItem);
        fileMenu.add(quitItem);
        menuSelector.add(fileMenu);
        quitItem.addActionListener(listener);
        setJMenuBar(menuSelector);
    }
    ActionListener listener = new ActionListener() 
    {
    /**
     * Description: Overrides an actionPerformed method in order to create
     * an event when the file items are clicked it will performed said action.
     * @param event - check to see if action occurred
     * 
     */
    @Override
    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == quitItem)
        {
            System.exit(0);
        }
    }
    };
    JPanel west;
    JPanel east;
    JMenuBar menuSelector;
    JMenuItem helpItem;
    JMenuItem aboutItem;
    JMenuItem quitItem;
    JMenu fileMenu;
    
}
