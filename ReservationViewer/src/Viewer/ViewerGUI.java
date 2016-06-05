package Viewer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

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
    public static final int HEIGHT = 225;
    public static final int WIDTH =  600;  
    public static final String FILE_NAME = "Reservations.dat";
    public static final int FONT_SIZE = 20;
    public static final int COMPONENT_HEIGHTWIDTH = 300;
    public static final int IMAGE_SIZE = 15;
    /**
     * Description: main constructor - set forms to visible, centers it and
     * makes the default close button exit the program.
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        frame = new ViewerGUI();
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
        tabPane = new JTabbedPane();
        setFileMenu();
        loadBinary();
        setTabPanes();
       
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
     * Description: Creates tabs pane for the user to search for the customer
     * with a JTextField and a JButton so the user can search
     * and a tab pane where it displays the customer's reservation with the
     * corresponding one the user selected.
     */
    public void setTabPanes()
    {
        radioGroup = new ButtonGroup();
        font = new Font("SansSerif", Font.BOLD, FONT_SIZE);
        size = new Dimension(COMPONENT_HEIGHTWIDTH,COMPONENT_HEIGHTWIDTH);
        component = new JPanel();
        component.setMaximumSize(size);
        component.setPreferredSize(size);
        component.setMinimumSize(size);
        Box horizontal = Box.createHorizontalBox();
        Box vertical = Box.createVerticalBox();
        icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage
        (getClass().getResource(("hotel.png"))));
        Image image = icon.getImage();
        Image newImage = 
                image.getScaledInstance(IMAGE_SIZE, IMAGE_SIZE
                        , java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        wholeButton = new JRadioButton("Search by: Full Name In Whole");
        startButton = new JRadioButton
        ("Search by: Letter the Name Starts With");
        radioGroup.add(wholeButton);
        radioGroup.add(startButton);
        custName = new JTextField();
        custName.setFont(font);
        custName.setHorizontalAlignment(JTextField.CENTER);
        buttonPanel = new JPanel();
        horizontal.add(Box.createHorizontalGlue());
        searchButton = new JButton("Search");
        horizontal.add(searchButton);
        horizontal.add(Box.createHorizontalGlue());
        vertical.add(Box.createVerticalGlue());
        vertical.add(custName);
        vertical.add(Box.createVerticalGlue());
        vertical.add(wholeButton);
        vertical.add(startButton);
        buttonPanel.setBorder(BorderFactory.createTitledBorder
        ("Search for Customer's Name"));
        buttonPanel.setLayout(new BoxLayout(buttonPanel , BoxLayout.Y_AXIS));
        buttonPanel.add(vertical);
        buttonPanel.add(horizontal);
        buttonPanel.add(component);
        tabPane.addTab("Search for Customer", icon, buttonPanel);
        customersInfo = new JPanel();
        tabPane.addTab("Customer(s) Reservation(s)", icon, customersInfo);
        add(tabPane);
    }
    /**
     * Description: This method will create the File Menu for the user
     * to select alternative database files if they want to load/reload
     * the binary database file.
     */
    public void setFileMenu()
    {
        menuSelector = new JMenuBar();
        helpItem = new JMenuItem("Help");
        aboutItem = new JMenuItem("About");
        quitItem  = new JMenuItem("Quit");
        openItem = new JMenuItem("Open Database File");
        fileMenu = new JMenu("File");
        fileMenu.add(openItem);       
        fileMenu.add(helpItem);
        fileMenu.add(aboutItem);
        fileMenu.add(quitItem);
        menuSelector.add(fileMenu);
        quitItem.addActionListener(listener);
        openItem.addActionListener(listener);
        setJMenuBar(menuSelector);
    }
    /**
     * Description: This will load the binary database file if it is present
     * if not present it will display an error to the user.
     */
    public void loadBinary()
    {
        chooseFile = new JFileChooser();
        binaryFile = new File(FILE_NAME);
        chooseFile.setCurrentDirectory
            (binaryFile.getAbsoluteFile().getParentFile());
        if(binaryFile.canRead() && binaryFile.exists())
        {
            
        }
        else
        {
           JOptionPane.showMessageDialog(frame, "Error: Loading " + FILE_NAME
           + "\n" + " Please select the database file. ");
           int result = chooseFile.showOpenDialog(openItem);
            if(result == JFileChooser.APPROVE_OPTION)
            {
                binaryFile = chooseFile.getSelectedFile();
                if(binaryFile.canRead() && binaryFile.exists())
                {
                    System.out.println("File is read");
                }
            }
        }
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
        if(event.getSource() == openItem)
        {
            int result = chooseFile.showOpenDialog(openItem);
            if(result == JFileChooser.APPROVE_OPTION)
            {
                binaryFile = chooseFile.getSelectedFile();
                if(binaryFile.canRead() && binaryFile.exists())
                {
                    System.out.println("File is read");
                }
            }
        }
    }
    };
    JPanel west;
    JPanel east;
    JMenuBar menuSelector;
    JMenuItem helpItem;
    JMenuItem aboutItem;
    JMenuItem quitItem;
    JMenuItem openItem;
    File binaryFile;
    JMenu fileMenu;
    static JFrame frame;
    JFileChooser chooseFile;
    JButton searchButton;
    JTabbedPane tabPane;
    JPanel buttonPanel;
    JTextField custName;
    JPanel customersInfo;
    ImageIcon icon;
    Font font;
    JComponent component;
    Dimension size;
    JRadioButton wholeButton;
    JRadioButton startButton;
    JLabel dateLabel;
    JLabel timeLabel;
    JTextField dateTextField;
    JTextField timeTextField;
    ButtonGroup radioGroup;
}
