package Viewer;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;

/**
 * File Name: ViewerGUI.java Description: This class is the GUI driver for
 * displaying the customer's reservation - their name, arrival/depart date It
 * will automatically load the database file and allow the user to search for
 * the customer's name to display their reservation. Date: 6/1/2016 Platform:
 * Windows 8, jdk 1.8.0_66, NetBeans 8.1
 *
 * @author Branavan Nagendiram , Luka Gajic , Jason Bowen
 */
public class ViewerGUI extends JFrame {

    /**
     * Description: main constructor - set forms to visible, centers it and
     * makes the default close button exit the program.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        frame = new ViewerGUI();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public ViewerGUI() {
        this.getRootPane().setDefaultButton(searchButton);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(WIDTH, HEIGHT);
        setIconImage
        (Toolkit.getDefaultToolkit().getImage(getClass()
                .getResource("hotel.png")));
        setTitle("Reservation Viewer");
        setFileMenu();
        setGUI();
        loadBinary();
        //updating clock
        new Thread() {

            //runs an updating clock that display hours minutes and seconds
            @Override
            public void run() {
                while (true) {
                    Calendar cal = new GregorianCalendar();
                    int hour = cal.get(Calendar.HOUR);
                    if (hour == 0) {
                        hour += 12;
                    }
                    int minute = cal.get(Calendar.MINUTE);
                    int second = cal.get(Calendar.SECOND);
                    int AM_PM = cal.get(Calendar.AM_PM);

                    String Day_Night = "";
                    if (AM_PM == 1) {
                        Day_Night = "PM";
                    } else {
                        Day_Night = "AM";
                    }

                    clock.setText(CurrentDate() + " Time: " + hour + ":"
                            + minute + ":" + second + " " + Day_Night);

                }

            }


        }.start();

    }//end of public

    public String CurrentDate() {
        Calendar cal = new GregorianCalendar();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return ("Date: " + (month + 1) + "/" + day + "/" + year);
    }

    /**
     * Description: Creates tabs pane for the user to search for the customer
     * with a JTextField and a JButton so the user can search and a tab pane
     * where it displays the customer's reservation with the corresponding one
     * the user selected.
     */
    private void setGUI() {
        ButtonGroup radioGroup = new ButtonGroup();
        Dimension size = new Dimension(COMPONENT_HEIGHTWIDTH, COMPONENT_HEIGHTWIDTH);
        JPanel component = new JPanel();
        component.setMaximumSize(size);
        component.setPreferredSize(size);
        component.setMinimumSize(size);
        Box horizontal = Box.createHorizontalBox();
        Box vertical = Box.createVerticalBox();
        ImageIcon icon = 
                new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass()
                        .getResource(("hotel.png"))));
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance
        (IMAGE_SIZE, IMAGE_SIZE, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        wholeButton = new JRadioButton("Search by: Full Name In Whole");
        startButton = new JRadioButton
        ("Search by: Letter the Name Starts With");
        radioGroup.add(wholeButton);
        radioGroup.add(startButton);
        wholeButton.setSelected(true);
        custName = new JTextField();
        custName.setFont(new Font("SansSerif", Font.BOLD, FONT_SIZE));
        custName.setHorizontalAlignment(JTextField.CENTER);
        JPanel buttonPanel = new JPanel();
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
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(vertical);
        buttonPanel.add(horizontal);
        buttonPanel.add(component);
        JTabbedPane tabPane = new JTabbedPane();
        tabPane.addTab("Search for Customer", icon, buttonPanel);
        JPanel customersInfo = new JPanel();
        output = new JTextArea();
        output.setEditable(false);
        customersInfo.add(output);
        output.setSize(this.size());
        tabPane.addTab("Customer(s) Reservation(s)", icon, customersInfo);
        searchButton.addActionListener(listener);
        add(tabPane);
    }

    /**
     * Description: This method will create the File Menu for the user to select
     * alternative database files if they want to load/reload the binary
     * database file.
     */
    private void setFileMenu() {
        JMenuBar menuSelector = new JMenuBar();
        JMenu helpMenu = new JMenu("Help");
        JMenu aboutMenu = new JMenu("About");
        quitItem = new JMenuItem("Quit");
        openItem = new JMenuItem("Open Database File");
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(openItem);
        fileMenu.add(quitItem);
        menuSelector.add(fileMenu);
        menuSelector.add(aboutMenu);
        menuSelector.add(helpMenu);
        quitItem.addActionListener(listener);
        openItem.addActionListener(listener);
        setJMenuBar(menuSelector);
        clock = new JMenuItem();
        menuSelector.add(Box.createGlue());
        menuSelector.add(clock);

        aboutMenu.addMenuListener(new MenuListener() {
            @Override
            public void menuCanceled(MenuEvent evt) {
            }

            @Override
            public void menuDeselected(MenuEvent evt) {
            }

            /**
             * Description: This method will call aboutMenuMenuSelected when
             * clicked and will bring up the AboutGUI information.
             *
             * @param evt Menu Listener
             */
            @Override
            public void menuSelected(MenuEvent evt) {
                aboutMenuMenuSelected(evt);
            }
        });
        helpMenu.addMenuListener(new MenuListener() {
            @Override
            public void menuCanceled(MenuEvent e) {
            }

            @Override
            public void menuDeselected(MenuEvent e) {
            }

            /**
             * Description: This method will call helpMenuMenuSelected when
             * clicked and will bring up the HelpGUI information.
             *
             * @param evt Menu Listener
             */
            @Override
            public void menuSelected(MenuEvent e) {
                helpMenuMenuSelected(e);
            }
        });

    }

    /**
     * Description: This will load the binary database file if it is present if
     * not present it will display an error to the user.
     */
    private void loadBinary() {
        chooseFile = new JFileChooser();
        File binaryFile = new File(FILE_NAME);
        chooseFile.setCurrentDirectory
        (binaryFile.getAbsoluteFile().getParentFile());
        FileFilter filter = new ExtensionFileFilter
        (".dat- Binary File ", new String[]{"dat"});
        chooseFile.setFileFilter(filter);
        if (binaryFile.canRead() && binaryFile.exists()) {
            reservation = new ReservationDatabase(binaryFile);
            reservation.readFile();
            dataArray = reservation.getDatabaseArray();
        } else {
            JOptionPane.showMessageDialog(frame, "Error: Loading " + FILE_NAME
                    + "\n" + " Please select the database file. ");
            int result = chooseFile.showOpenDialog(openItem);
            if (result == JFileChooser.APPROVE_OPTION) {
                binaryFile = chooseFile.getSelectedFile();
                if (binaryFile.canRead() && binaryFile.exists()) {
                    reservation = new ReservationDatabase(binaryFile);
                    reservation.readFile();
                    dataArray = reservation.getDatabaseArray();
                }
            }
        }
    }

    ActionListener listener = new ActionListener() {
        /**
         * Description: Overrides an actionPerformed method in order to create
         * an event when the file items are clicked it will performed said
         * action.
         *
         * @param event - check to see if action occurred
         *
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == quitItem) {
                System.exit(0);
            } else if (event.getSource() == openItem) {
                int result = chooseFile.showOpenDialog(openItem);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File binaryFile = chooseFile.getSelectedFile();
                    if (binaryFile.canRead() && binaryFile.exists()) {
                        reservation = new ReservationDatabase(binaryFile);
                        reservation.readFile();
                        Reservation[] test = reservation.getDatabaseArray();
                        Arrays.toString(test);
                        System.out.println("test");
                    }
                }
            } else if (event.getSource() == searchButton) {
                try {
                    ArrayList<Comparable> found = new ArrayList<>();
                    if (wholeButton.isSelected()) {
                        found = Search.binarySearch(dataArray, 
                                custName.getText());
                    } else if (startButton.isSelected()) {
                        found = Search.binarySearchFirstLetter
                        (dataArray, custName.getText().charAt(0));
                    }
                    StringBuilder out = new StringBuilder();
                    found.stream().forEach((item) -> {
                        out.append(item.toString());
                    });
                    output.setText(out.toString());
                } catch (NullPointerException exp) {
                    JOptionPane.showMessageDialog(null, "Name not found",
                            "Name not found Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    };

    /**
     * Description: This method will create an variable of the AboutInformation
     * type and make the GUI visible, centered, and always in front of the
     * program.
     *
     * @param evt Menu Event
     */
    private void aboutMenuMenuSelected(MenuEvent evt) {
        AboutInformation about = new AboutInformation();
        about.setResizable(false);
        about.setVisible(true);
        about.setAlwaysOnTop(true);
        about.setLocationRelativeTo(null);
    }

    /**
     * Description: This method will create an variable of the HelpGUI type and
     * make the GUI visible, centered, and always in front of the program.
     *
     * @param evt Menu Event
     */
    private void helpMenuMenuSelected(MenuEvent e) {
        HelpGUI help = new HelpGUI();
        help.setResizable(false);
        help.setVisible(true);
        help.setAlwaysOnTop(true);
        help.setLocationRelativeTo(null);
    }

    public String getCustomerName() {
        return custName.getText();
    }

    private JMenuItem quitItem;
    private JMenuItem openItem;
    private static JFrame frame;
    private JFileChooser chooseFile;
    private JTextField custName;
    private JRadioButton wholeButton;
    private JRadioButton startButton;
    private ReservationDatabase reservation;
    private JMenuItem clock;
    private Reservation[] dataArray;
    private JButton searchButton;
    private JTextArea output;

    private static final int HEIGHT = 225;
    private static final int WIDTH = 800;
    private static final String FILE_NAME = "Reservations.dat";
    private static final int FONT_SIZE = 20;
    private static final int COMPONENT_HEIGHTWIDTH = 300;
    private static final int IMAGE_SIZE = 15;
}
