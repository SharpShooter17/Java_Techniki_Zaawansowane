package retire;

import java.awt.*;

/**
 * Zasoby dla języka niemieckiego, które nie są łańcuchami znaków.
 * @version 1.21 2001-08-27
 * @author Cay Horstmann
 */
public class RetireResources_de extends java.util.ListResourceBundle
{
   private static final Object[][] contents = {
   // POCZĄTEK LOKALIZACJI
         { "colorPre", Color.yellow }, { "colorGain", Color.black }, { "colorLoss", Color.red }
   // KONIEC LOKALIZACJI
   };

   public Object[][] getContents()
   {
      return contents;
   }
}
