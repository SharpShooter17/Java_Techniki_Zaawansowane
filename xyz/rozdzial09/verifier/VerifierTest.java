package verifier;

import java.applet.*;
import java.awt.*;

/**
 * Program demonstrujący działanie weryfikatora kodu maszyny wirtualnej. Jeśli 
 * zmodyfikujemy plik klasy, korzystając z edytora kodu szesnastkowego, 
 * weryfikator wykryje to podczas próby uruchomienia programu.
 * 
 * @version 1.00 1997-09-10
 * @author Cay Horstmann
 */
public class VerifierTest extends Applet
{
   public static void main(String[] args)
   {
      System.out.println("1 + 2 == " + fun());
   }

   /**
    * Metoda wyznaczająca wynik działania 1 + 2
    * @return 3, jeśli kod metody fun nie został zmodyfikowany
    */
   public static int fun()
   {
      int m;
      int n;
      m = 1;
      n = 2;
      // powyższą instrukcję należy zmienić w pliku klasy
      // na "m = 2", korzystając z edytora kodu szesnastkowego
      int r = m + n;
      return r;
   }

   public void paint(Graphics g)
   {
      g.drawString("1 + 2 == " + fun(), 20, 20);
   }
}
