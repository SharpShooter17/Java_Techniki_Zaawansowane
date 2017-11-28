package objectStream;

public class Manager extends Employee
{
   private Employee secretary;

   /**
    * Konstruuje obiekt menedżera bez sekretarki
    * @param n imię pracownika
    * @param s wynagrodzenie
    * @param year rok zatrudnienia
    * @param month miesiąc zatrudnienia
    * @param day dzień zatrudnienia
    */
   public Manager(String n, double s, int year, int month, int day)
   {
      super(n, s, year, month, day);
      secretary = null;
   }

   /**
    * Przypisuje sekretarkę menedżerowi.
    * @param s sekretarka
    */
   public void setSecretary(Employee s)
   {
      secretary = s;
   }

   public String toString()
   {
      return super.toString() + "[secretary=" + secretary + "]";
   }
}
