package auth;

import java.security.*;
import javax.security.auth.*;
import javax.security.auth.login.*;

/**
 * Program uwierzytelnia użytkownika za pomocą niestandardowej procedury 
 * logowania do systemu, a następnie wykonuje SysPropAction,
 * korzystając z pozwoleń przyznanych użytkownikowi.
 * @version 1.01 2007-10-06
 * @author Cay Horstmann
 */
public class AuthTest
{
   public static void main(final String[] args)
   {
      System.setSecurityManager(new SecurityManager());
      try
      {
         LoginContext context = new LoginContext("Login1");
         context.login();
         System.out.println("Uwierzytelnianie przebiegło pomyślnie.");
         Subject subject = context.getSubject();
         System.out.println("subject=" + subject);
         PrivilegedAction<String> action = new SysPropAction("user.home");
         String result = Subject.doAsPrivileged(subject, action, null);
         System.out.println(result);
         context.logout();
      }
      catch (LoginException e)
      {
         e.printStackTrace();
      }
   }
}
