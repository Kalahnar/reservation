package Viewer;

import Calendar.DateAD;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * File Name: ReservationDatabase.java
 * Description: This class will contain all of the reservations loaded from
 * the database file.
 * Date: 6/1/2016
 * Platform: Windows 8, jdk 1.8.0_66, NetBeans 8.1
 * @author Branavan Nagendiram , Luka Gajic , Jason Bowen
 */
public class ReservationDatabase 
{
    /**
     * Description: assigns variable file to the database file.
     * @param file database file as file
     */
    public ReservationDatabase(File file)
    {
        getFile(file);
        dates = new ArrayList();
    }
    /**
     * Description: this methods assigns the file variable to file.
     * @param file - database file as a file
     */
    public void getFile(File file)
    {
        databaseFile = file;
    }
    /**
     * Description: This method will read the database file into an array
     * and get all the reservations from the database and put into a array
     * so that it can be searched.
     */
    public void readFile()
    {
        if(databaseFile.exists() && databaseFile.canRead() )
        {
           try
           {
           buffered = new BufferedReader(new FileReader
                                        (databaseFile));
           while(buffered.ready())
           {
               String customerName = buffered.readLine();
               String arrive = buffered.readLine();
               String depart = buffered.readLine();
               arrive = arrive.substring(arrive.indexOf(':') + 2);
               depart = depart.substring(depart.indexOf(':') + 2);
               DateAD arriveDate = new DateAD(arrive);
               DateAD departDate = new DateAD(depart);
               buffered.readLine();
               databaseReservation = new Reservation(customerName, arriveDate,
               departDate);
               dates.add(databaseReservation);
           }
           }
           catch(IOException | NullPointerException except)
           {
           JOptionPane.showMessageDialog(gui, this, 
                   "Error- File cannot be read or found"
                   , JOptionPane.ERROR_MESSAGE);
           }
       }
       databaseArray = new Reservation[dates.size()];
       databaseArray = dates.toArray(databaseArray);
       dates = null;
    }
    /**
     * Description: Returns the databaseArray so it can be accessed.
     * @return Reservation Array - contains all the reservations from the 
     * database.
     */
    public Reservation[] getDatabaseArray()
    {
        return databaseArray;
    }
    
    File databaseFile;
    BufferedReader buffered;
    Reservation[] databaseArray;
    Reservation databaseReservation;
    ArrayList<Reservation> dates;
    ViewerGUI gui;

}
