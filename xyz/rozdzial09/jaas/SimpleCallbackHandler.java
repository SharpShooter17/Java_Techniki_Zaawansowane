package jaas;

import javax.security.auth.callback.*;

/**
 * Prosty obiekt obsługi prezentujący nazwę użytkownika i jego hasło.
 */
public class SimpleCallbackHandler implements CallbackHandler
{
   private String username;
   private char[] password;

   /**
    * Konstruktor.
    * @param username nazwa użytkownika
    * @param password tablica znaków zawierająca hasło
    */
   public SimpleCallbackHandler(String username, char[] password)
   {
      this.username = username;
      this.password = password;
   }

   public void handle(Callback[] callbacks)
   {
      for (Callback callback : callbacks)
      {
         if (callback instanceof NameCallback)
         {
            ((NameCallback) callback).setName(username);
         }
         else if (callback instanceof PasswordCallback)
         {
            ((PasswordCallback) callback).setPassword(password);
         }
      }
   }
}
