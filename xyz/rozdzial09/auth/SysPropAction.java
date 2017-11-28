package auth;

import java.security.*;

/**
 * Akcja wyszukująca właściwość systemową.
 * @version 1.01 2007-10-06
 * @author Cay Horstmann   
*/
public class SysPropAction implements PrivilegedAction<String>
{
   private String propertyName;

   /**
    * Tworzy akcję wyszukiwania podanej właściwości.
    * @param propertyName nazwa właściwości (na przykład "user.home")
    */
   public SysPropAction(String propertyName) { this.propertyName = propertyName; }

   public String run()
   {
      return System.getProperty(propertyName);
   }
}
