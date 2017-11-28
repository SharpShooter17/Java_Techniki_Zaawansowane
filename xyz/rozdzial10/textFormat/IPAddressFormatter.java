package textFormat;

import java.text.*;
import java.util.*;
import javax.swing.text.*;

/**
 * Obiekt formatujący 4-bajtowe adresy IP o postaci a.b.c.d
 */
public class IPAddressFormatter extends DefaultFormatter
{
   public String valueToString(Object value) throws ParseException
   {
      if (!(value instanceof byte[])) throw new ParseException("Nie przekazano tablicy byte[]", 0);
      byte[] a = (byte[]) value;
      if (a.length != 4) throw new ParseException("Długość != 4", 0);
      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < 4; i++)
      {
         int b = a[i];
         if (b < 0) b += 256;
         builder.append(String.valueOf(b));
         if (i < 3) builder.append('.');
      }
      return builder.toString();
   }

   public Object stringToValue(String text) throws ParseException
   {
      StringTokenizer tokenizer = new StringTokenizer(text, ".");
      byte[] a = new byte[4];
      for (int i = 0; i < 4; i++)
      {
         int b = 0;
         if (!tokenizer.hasMoreTokens()) throw new ParseException("Zbyt mało bajtów", 0);
         try
         {
            b = Integer.parseInt(tokenizer.nextToken());
         }
         catch (NumberFormatException e)
         {
            throw new ParseException("To nie jest liczba całkowita", 0);
         }
         if (b < 0 || b >= 256) throw new ParseException("Wartość liczby poza zakresem typu byte", 0);
         a[i] = (byte) b;
      }
      if (tokenizer.hasMoreTokens()) throw new ParseException("Zbyt wiele bajtów", 0);
      return a;
   }
}
