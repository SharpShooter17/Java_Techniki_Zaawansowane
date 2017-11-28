import java.io.*;

/**
 * @version 1.10 1997-07-01
 * @author Cay Horstmann
 */
class Printf4Test
{
   public static void main(String[] args)
   {
      double price = 44.95;
      double tax = 7.75;
      double amountDue = price * (1 + tax / 100);
      PrintWriter out = new PrintWriter(System.out);
      /* Poniższe wywołanie spowoduje wyrzucenie wyjątku ze względu na kombinację %% */
      Printf4.fprint(out, "Kwota do zapłaty = %%8.2f\n", amountDue);
      out.flush();
   }
}
