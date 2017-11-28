package treeModel;

import java.lang.reflect.*;
import java.util.*;

/**
 * Klasa reprezentująca zmienną posiadającą typ, nazwę i wartość.
 */
public class Variable
{
   private Class<?> type;
   private String name;
   private Object value;
   private ArrayList<Field> fields;

   /**
    * Tworzy obiekt reprezentujący zmienną.
    * @param aType typ zmiennej
    * @param aName nazwa zmiennej
    * @param aValue wartość zmiennej
    */
   public Variable(Class<?> aType, String aName, Object aValue)
   {
      type = aType;
      name = aName;
      value = aValue;
      fields = new ArrayList<>();

      // znajduje wszystkie pola, jeśli zmienna jest typu klasy,
      // nie rozwija jedynie łańcuchów znaków i wartości null

      if (!type.isPrimitive() && !type.isArray() && !type.equals(String.class) && value != null)
      {
         // pobiera pola klasy i pola wszystkich jej klas bazowych
         for (Class<?> c = value.getClass(); c != null; c = c.getSuperclass())
         {
            Field[] fs = c.getDeclaredFields();
            AccessibleObject.setAccessible(fs, true);

            // pobiera wszystkie pola, które nie są statyczne
            for (Field f : fs)
               if ((f.getModifiers() & Modifier.STATIC) == 0) fields.add(f);
         }
      }
   }

   /**
    * Zwraca wartość zmiennej.
    * @return wartość
    */
   public Object getValue()
   {
      return value;
   }

   /**
    * Zwraca wszystkie pola zmiennej, które nie są statyczne.
    * @return tablica zmiennych opisujących pola
    */
   public ArrayList<Field> getFields()
   {
      return fields;
   }

   public String toString()
   {
      String r = type + " " + name;
      if (type.isPrimitive()) r += "=" + value;
      else if (type.equals(String.class)) r += "=" + value;
      else if (value == null) r += "=null";
      return r;
   }
}
