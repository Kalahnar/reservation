package Viewer;

import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * File Name: Search.java
 * Description: This class will do a binary search on the reservation array
 * which includes all the customer's from the database file and return a value
 * if the item was found or if it wasn't found return a error message.
 * Date: 6/8/2016
 * Platform: Windows 8, jdk 1.8.0_66, NetBeans 8.1
 * @author Branavan Nagendiram , Luka Gajic , Jason Bowen
 */
public class Search implements Comparable
{
    public static int NOT_THERE = -1;
    public static int EQUAL = 0;
    /**
     * Description: This is a binary search method which will search through
     * the array of customer reservations and look for the customer that the 
     * user has searched for, if that name isn't there it will return a -1.
     * @param arr - array of customer's
     * @param searchValue - string that user searched for
     * @param low - low index of array
     * @param high - high index of array
     * @return integer value of the array that the customer name is in
     */
    
    public static int BinarySearch(Comparable[] arr, Comparable searchValue
     , int low , int high)
    {
        if(high <= low)
        {
            return NOT_THERE;
        }
        int middleValue = (high + low) / 2;
        if(arr[middleValue].equals(searchValue))
        {
            return middleValue;
        }
        else if(arr[middleValue].compareTo(searchValue) > 0 )
        {
            return BinarySearch(arr, searchValue, low , middleValue);
        }
        else
            return BinarySearch(arr, searchValue, middleValue + 1 , high);
    }
    
 

    
    public int compareTo(Reservation obj)
    {
    try
    {
        int compare = test.getCustomerName().compareTo(obj.getString());
        if(compare != EQUAL)
        {
            return compare;
        }
    }
    catch(ClassCastException exp)
    {
        JOptionPane.showMessageDialog(null, "Class is Incorrect", 
                       "Class Error", JOptionPane.ERROR_MESSAGE);
    }
        return EQUAL;
    }
    ViewerGUI test = new ViewerGUI();

    @Override
    public int compareTo(Object o) {
        return compareTo((Reservation) o);
    }
    
}
 