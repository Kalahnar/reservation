package Viewer;

import Calendar.DateAD;

/**
 * File Name: Reservation.java Description: This class will contain methods to
 * take the information from the file such as the Date as a DateAD, and the
 * customer's name as a string the arrivalDate of the customer and the
 * departureDate of the customer will be the parameters of the class and they
 * will be compared with one another to see if they are equal with each other.
 *
 * @author Branavan Nagendiram
 */
public class Reservation implements java.io.Serializable, Comparable {

    /**
     * Description: default constructor assigns values to the name of the
     * customer, depart date and arrival date of the customer
     *
     * @param name name of customer
     * @param depart date of departure of the customer
     * @param arrival date of arrival of the customer.
     */
    public Reservation(String name, DateAD arrival, DateAD depart) {
        this.name = name;
        arrivalDate = arrival.clone();
        departDate = depart.clone();
        if (departDate.lessThan(arrivalDate)) {
            departDate = arrivalDate.getTomorrow();
        }
    }

    /**
     * Description: gets a arrival DateAD and returns a clone of the arrival
     * DateAD
     *
     * @return clone of the arrival date from the customer
     */
    public DateAD getArrivalDate() {
        return arrivalDate.clone();
    }

    /**
     * Description: gets a depart DateAD and returns a clone of the departure
     * DateAD
     *
     * @return clone of the arrival date from the customer
     */
    public DateAD getDepartDate() {
        return departDate.clone();
    }

    /**
     * Description: gets the customer's name.
     *
     * @return String- containing customers name.
     */
    public String getName() {
        return name;
    }

    /**
     * Description: Overrides the toString method and creates a string with the
     * customer name at top then the arrival date and then the departure date
     * with a blank line after that
     *
     * @return String - combined string with all parameters
     */
    @Override
    public String toString() {
        return getName() + "\n" + "Arrive: " + getArrivalDate() + "\n"
                + "Depart: " + getDepartDate()
                + "\n";
    }

    @Override
    public boolean equals(Object o) {
        return name.equalsIgnoreCase(o.toString());
    }

    @Override
    public int compareTo(Object o) {
        return getName().compareToIgnoreCase(o.toString());
    }

    String name;
    DateAD arrivalDate;
    DateAD departDate;
    final int BEFORE = -1;
    final int EQUAL = 0;
    final int AFTER = 1;
}
