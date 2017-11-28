package set;

import java.util.*;
import bytecodeAnnotations.*;

/**
 * Produkt z opisem i numerem.
 * @version 1.01 2012-01-26
 * @author Cay Horstmann
 */
public class Item
{
   private String description;
   private int partNumber;

   /**
    * Konstruktor.
    * @param aDescription opis produktu
    * @param aPartNumber numer produktu
    */
   public Item(String aDescription, int aPartNumber)
   {
      description = aDescription;
      partNumber = aPartNumber;
   }

   /**
    * Zwraca opis produktu.
    * @return opis
    */
   public String getDescription()
   {
      return description;
   }

   public String toString()
   {
      return "[description=" + description + ", partNumber=" + partNumber + "]";
   }

   @LogEntry(logger = "com.horstmann")
   public boolean equals(Object otherObject)
   {
      if (this == otherObject) return true;
      if (otherObject == null) return false;
      if (getClass() != otherObject.getClass()) return false;
      Item other = (Item) otherObject;
      return Objects.equals(description, other.description) && partNumber == other.partNumber;
   }

   @LogEntry(logger = "com.horstmann")
   public int hashCode()
   {
      return Objects.hash(description, partNumber);
   }
}
