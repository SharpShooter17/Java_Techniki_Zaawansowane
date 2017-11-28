package composite;

import java.awt.*;

/**
 * Klasa reprezentująca regułę Portera-Duffa.
 */
class Rule
{
   private String name;
   private String porterDuff1;
   private String porterDuff2;

   /**
    * Tworzy obiekt reprezentujący regułę Portera-Duffa
    * @param n nazwa reguły
    * @param pd1 pierwszy wiersz diagramu Portera-Duffa
    * @param pd2 drugi wiersz diagramu Portera-Duffa
    */
   public Rule(String n, String pd1, String pd2)
   {
      name = n;
      porterDuff1 = pd1;
      porterDuff2 = pd2;
   }

   /**
    * Zwraca objaśnienie sposobu działania reguły.
    * @return objaśnienie
    */
   public String getExplanation()
   {
      StringBuilder r = new StringBuilder("");

      if (porterDuff2.equals(" D") || porterDuff2.equals("D ")) {
         if (porterDuff2.equals(" D")) 
            r.append("Wartość alfa ");
         if (porterDuff2.equals("D "))
            r.append( "Uzupełnienie wartości alfa " );
         
         r.append( "obrazu źródłowego modyfikuje" );
      } else 
         r.append( "Obraz źródłowy " ) ;

      if (porterDuff2.equals("  ")) r.append("czyści");
      if (porterDuff2.equals(" S")) r.append("nadpisuje");
      if (porterDuff2.equals("DD")) r.append("nie ma wpływu na");
      if (porterDuff2.equals("DS")) 
         r.append("łączy się z obrazem docelowym" ); 
      else 
         r.append(" obraz docelowy");

      if (porterDuff1.equals(" S")) r.append(" i nadpisuje puste piksele");
      r.append(".");
      return r.toString();


   }

   public String toString()
   {
      return name;
   }

   /**
    * Zwraca wartość wyznaczoną przez regułę
    * jako obiekt klasy AlphaComposite
    * @return stała AlphaComposite lub -1, jeśli nie istnieje odpowiednia stała.
    */
   public int getValue()
   {
      try
      {
         return (Integer) AlphaComposite.class.getField(name).get(null);
      }
      catch (Exception e)
      {
         return -1;
      }
   }
}
