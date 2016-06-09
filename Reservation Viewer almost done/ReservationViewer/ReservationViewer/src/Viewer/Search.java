package Viewer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * File Name: Search.java Description: This class will do a binary search on the
 * reservation array which includes all the customer's from the database file
 * and return a value if the item was found or if it wasn't found return a error
 * message. Date: 6/8/2016 Platform: Windows 8, jdk 1.8.0_66, NetBeans 8.1
 *
 * @author Branavan Nagendiram , Luka Gajic , Jason Bowen
 */
public class Search {

    private static ArrayList<Comparable> searchList;
    /**
     * Description: This method just does a binary search on the ArrayList
     * 
     * @param arr
     * @param searchValue
     * @return 
     */
    public static ArrayList<Comparable> binarySearch
        (Comparable[] arr, Comparable searchValue) 
    {
        ArrayList<Comparable> comp = new ArrayList<>();
        comp.addAll(Arrays.asList(arr));
        searchList = new ArrayList<>();
        binarySearch(comp, searchValue, 0, comp.size());
        return searchList;
    }

    /**
     * Description: This is a binary search method which will search through the
     * array of customer reservations and look for the customer that the user
     * has searched for, if that name isn't there it will return a -1.
     *
     * @param arr - array of customer's
     * @param searchValue - string that user searched for
     * @param low - low index of array
     * @param high - high index of array
     * @return integer value of the array that the customer name is in
     */
    private static void binarySearch
        (ArrayList<Comparable> arr, Comparable searchValue, int low, int high) 
    {
        if (high <= low) {
            return;
        }
        int mid = (high + low) / 2;
        if (arr.get(mid).equals(searchValue)) {
            searchList.add(arr.remove(mid));
            binarySearch(arr, searchValue, 0, arr.size());
        } else if (arr.get(mid).compareTo(searchValue) > 0) {
            binarySearch(arr, searchValue, low, mid);
        } else {
            binarySearch(arr, searchValue, mid + 1, high);
        }
    }
    /**
     * Description: This method takes the data base array and copies it into 
     * an ArrayList.
     * @param arr ArrayList database with customer's name
     * @param searchedLetter Letter user typed
     * @return 
     */
    public static ArrayList<Comparable> binarySearchFirstLetter
        (Comparable[] arr, char searchedLetter) {
        ArrayList<Comparable> comp = new ArrayList<>();
        comp.addAll(Arrays.asList(arr));
        searchList = new ArrayList<>();
        binarySearchFirstLetter(comp, Character.toLowerCase(searchedLetter),
                0, comp.size());
        return searchList;
    }

    /**
     * Description: This is a binary search method which will search through the
     * array of customer reservations and look for the customer that the user
     * has searched for, if that name isn't there it will return a -1.
     *
     * @param arr - array of customer's
     * @param searchValue - string that user searched for
     * @param low - low index of array
     * @param high - high index of array
     * @return integer value of the array that the customer name is in
     */
    private static void binarySearchFirstLetter(ArrayList<Comparable> arr
            , char searchedLetter, int low, int high) 
    {
        if (high <= low) {
            return;
        }
        int mid = (high + low) / 2;
        char firstLetter = Character.toLowerCase(arr.get(mid).toString().charAt(0));
        if (firstLetter == searchedLetter) {
            searchList.add(arr.remove(mid));
            binarySearchFirstLetter(arr, searchedLetter, 0, arr.size());
        } else if (firstLetter > searchedLetter) {
            binarySearchFirstLetter(arr, searchedLetter, low, mid);
        } else {
            binarySearchFirstLetter(arr, searchedLetter, mid + 1, high);
        }
    }
}
