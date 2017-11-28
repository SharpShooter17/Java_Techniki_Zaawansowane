package print;

import java.awt.*;
import java.awt.print.*;

import javax.print.attribute.*;
import javax.swing.*;

/**
 * Ramka zawierająca panel z grafiką 2D i przyciski umożliwiające 
 * wydruk grafiki oraz określenie formatu strony.
 */
public class PrintTestFrame extends JFrame
{
   private PrintComponent canvas;
   private PrintRequestAttributeSet attributes;

   public PrintTestFrame()
   {
      canvas = new PrintComponent();
      add(canvas, BorderLayout.CENTER);

      attributes = new HashPrintRequestAttributeSet();

      JPanel buttonPanel = new JPanel();
      JButton printButton = new JButton("Drukuj");
      buttonPanel.add(printButton);
      printButton.addActionListener(event ->
         {
            try
            {
               PrinterJob job = PrinterJob.getPrinterJob();
               job.setPrintable(canvas);
               if (job.printDialog(attributes)) job.print(attributes);
            }
            catch (PrinterException ex)
            {
               JOptionPane.showMessageDialog(PrintTestFrame.this, ex);
            }
         });

      JButton pageSetupButton = new JButton("Układ strony");
      buttonPanel.add(pageSetupButton);
      pageSetupButton.addActionListener(event ->
         {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.pageDialog(attributes);
         });

      add(buttonPanel, BorderLayout.NORTH);
      pack();
   }
}