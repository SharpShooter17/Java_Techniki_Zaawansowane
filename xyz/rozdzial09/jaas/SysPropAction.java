package jaas;

import java.security.*;

/**
 * Ta akcja wyszukuje właściwość systemową.
 */
public class SysPropAction implements PrivilegedAction<String>
{
   private String propertyName;

   /**
    * Tworzy akcję służąca do wyszukiwania podanej właściwości.
    * @param propertyName Nazwa właściwości (taka jak "user.home")
    */
   public SysPropAction(String propertyName)
   {
      this.propertyName = propertyName;
   }

   public String run()
   {
      return System.getProperty(propertyName);
   }
}
