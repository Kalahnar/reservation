package Viewer;
import Calendar.DateAD;
import java.util.Objects;

/**
 * File Name: Reservation.java
 * Description: This class will contain methods to take the information from
 * the file such as the Date as a DateAD, and the customer's name as a string
 * the arrivalDate of the customer and the departureDate of the customer
 * will be the parameters of the class and they will be compared with one
 * another to see if they are equal with each other. 
 * @author Branavan Nagendiram
 */
public class Reservation implements java.io.Serializable, 
        Comparable<Reservation>
{
    /**
     * Description: default constructor assigns values to the name of the
     * customer, depart date and arrival date of the customer
     * @param name name of customer
     * @param depart date of departure of the customer
     * @param arrival date of arrival of the customer.
     */
    public Reservation(String name, DateAD arrival, DateAD depart)
    {
        custName = name;
        arrivalDate = arrival.clone();
        departDate = depart.clone();
        if(departDate.lessThan(arrivalDate))
        {
            departDate = arrivalDate.getTomorrow();
        }
    }
    /**
     * Description: gets a arrival DateAD and returns a clone of the
     * arrival DateAD
     * @return clone of the arrival date from the customer
     */
    public DateAD getArrivalDate()
    {
        return arrivalDate.clone();
    }
    /**
     * Description: gets a depart DateAD and returns a clone of the
     * departure DateAD
     * @return clone of the arrival date from the customer
     */
    public DateAD getDepartDate()
    {
        return departDate.clone();
    }
    /**
     * Description: Gets a string with the customers name.
     * @return String- containing customers name.
     */
    public String getString()
    {
        return custName;
    }
    /**
     * Description: Overrides the toString method and creates a string
     * with the customer name at top then the arrival date and then the
     * departure date with a blank line after that
     * @return String - combined string with all parameters
     */
    @Override
    public String toString()
    {
        return getString() + "\n" + "Arrive: " + getArrivalDate() + "\n" 
                + "Depart: " + getDepartDate()
                + "\n";
    }
    /**
     * Description: Checks to see if all the three items are equal to each
     * other, if it does then the boolean returns true else it returns false.
     * @param obj - object that will be compared to see if equal.
     * @return true or false - checks to see if the items equal each other.
     */
    @Override
    public boolean equals(Object obj)
    {
       if(this == obj)
       {
           return true;
       }
       if(!(obj instanceof Reservation))
       {
           return false;
       }
       Reservation res = (Reservation) obj;
       return
       (this.getArrivalDate() == res.getArrivalDate()) &&
       (this.getDepartDate() == res.getDepartDate()) &&
       (this.getString().equals(res.getString()));
               
    }
    /**
     * Description: This method overrides the hashCode Method and assigns
     * hash values to each of the objects in the class like the customer
     * name, arrivalDate, and the departDate.
     * @return integer- hash code of the objects.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.custName);
        hash = 41 * hash + Objects.hashCode(this.arrivalDate);
        hash = 41 * hash + Objects.hashCode(this.departDate);
        return hash;
    }
    
   
    /**
     * Description: First this method compares the name to check whether
     * or not it comes before or after then does the same with the departure
     * and arrival date. 
     * @param res - object from the reservation class.
     * @return integer  -1 if objects comes before, 0 if they are equal
     * and 1 if it comes after.
     * @throws  java.lang.ClassCastException
     */
  
    @Override
    public int compareTo(Reservation res)
    {
      try
      {
        int compare = getString().compareTo(res.getString());
        if(compare != EQUAL)
        {
            return compare;
        }
        compare = arrivalDate.compareTo(res.getArrivalDate());
        if(compare != EQUAL)
        {
           
            return compare;
        }
        compare = departDate.compareTo(res.getDepartDate());
        if(compare != EQUAL)
        {
            return compare;
        }
      }
      catch(ClassCastException exp)
      {
          System.out.println("Objects being compared aren't both type"
                  + " Reservation.");
      }
      return EQUAL;
    }
    
    String custName = "";
    DateAD arrivalDate;
    DateAD departDate;
    final int BEFORE = -1;
    final int EQUAL = 0;
    final int AFTER = 1;
}
