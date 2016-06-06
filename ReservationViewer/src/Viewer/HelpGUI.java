package Viewer;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * File Name: HelpGUI.java
 * Description: This class creates a HelpGUI that will give information to 
 * the user on how to use the program.
 * Date: 6/1/2016
 * Platform: Windows 8, jdk 1.8.0_66, NetBeans 8.1
 * @author 
 */
public class HelpGUI extends JFrame {
    public static final int HEIGHT = 225;
    public static final int WIDTH =  600;  
    public static final int FONT_SIZE = 20;
    public static final int COMPONENT_HEIGHTWIDTH = 300;
    public static final int IMAGE_SIZE = 15;
    public static final String description = "How to navigate the program ";
    /**
     * Description: Default constructor that assigns the default close
     * to dispose the window, centers the form and makes it visible.
     * @param args - the command line arguments
     */
    public  void main(String[] args) 
    {
       JFrame help = new HelpGUI();
        help.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        help.setLocationRelativeTo(null);
        help.setVisible(true);
    }
    /**
     * Description: This method sets the layout to BorderLayout and runs
     * all the individual methods in the class.
     */
    public HelpGUI()
    {
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);  
        setFileMenu();
        setTextPanel();
        setButtonPanel();
        setTitle("Help");
        add(textPanel,BorderLayout.NORTH);
        add(buttonPanel,BorderLayout.SOUTH);
        
    }
    /**
     * Description: This method will set the file menu for the gui and add
     * a close item that will close the GUI when clicked.
     */
    public void setFileMenu()
    {
        menuSelector = new JMenuBar();
        
        closeItem  = new JMenuItem("Close");
        
        fileMenu = new JMenu("File");       
        fileMenu.add(closeItem);
        menuSelector.add(fileMenu);
        setJMenuBar(menuSelector);
        closeItem.addActionListener(new java.awt.event.ActionListener() 
        {
            /**
             * Description: calls another method closeItemActionPerformed
             * that will close the form and dispose of it when clicked.
             * @param evt - Action Event
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeItemActionPerformed(evt);
            }
        });
        
    }
    
    /**
     * Description: Sets the JTextArea of the program and assigns the text
     * for the description of the program and how to use it.
     */
    public void setTextPanel()
    {
        //Once we figure out the text make sure GUI is resizible(false);
        textPanel = new JPanel();
        setLayout(new FlowLayout());
        textArea = new JTextArea(15,40);
        add(textArea);
        textArea.setEditable(false);
        textArea.setText(description);
    
    
    }
    /**
     * Description: Set's the button panel with a close button that will close
     * the program when clicked.
     */
    public void setButtonPanel()
    {
        buttonPanel = new JPanel();
        setLayout (new FlowLayout());
        closeButton = new JButton("Close");
        add(closeButton);
              
    }
    
    /**
     * Description: This method is an ActionListener that will dispose of
     * the form when the exit item is clicked.
     * @param evt  action event of the item
     */
    private void closeItemActionPerformed(java.awt.event.ActionEvent evt) {                                          
        
        this.dispose();
        
        
    }
    
    
    JMenuBar menuSelector;
    JMenu fileMenu;
    JMenuItem closeItem;
    JPanel textPanel;
    JTextArea textArea;
    JPanel buttonPanel;
    JButton closeButton;
}
