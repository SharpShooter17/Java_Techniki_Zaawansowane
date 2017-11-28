package retire;

import java.awt.*;

/**
 * Zasoby dla języka chińskiego, które nie są łańcuchami znaków.
 * @version 1.21 2001-08-27
 * @author Cay Horstmann
 */
public class RetireResources_zh extends java.util.ListResourceBundle
{
   private static final Object[][] contents = {
   // POCZĄTEK LOKALIZACJI
         { "colorPre", Color.red }, { "colorGain", Color.blue }, { "colorLoss", Color.yellow }
   // KONIEC LOKALIZACJI
   };

   public Object[][] getContents()
   {
      return contents;
   }
}
