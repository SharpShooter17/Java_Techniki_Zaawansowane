package table;

import java.awt.*;
import java.awt.print.*;

import javax.swing.*;

/**
 * Program demonstrujący przykład prostej tabeli.
 * @version 1.13 2016-05-10
 * @author Cay Horstmann
 */
public class TableTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(() ->
         {
            JFrame frame = new PlanetTableFrame();
            frame.setTitle("TableTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
         });
   }
}

/**
 * Ramka zawierająca tabelę danych o planetach.
 */
class PlanetTableFrame extends JFrame
{
   private String[] columnNames = { "Planeta", "Promień", "Księżyców", "Gazowa", "Kolor" };
   private Object[][] cells = { { "Merkury", 2440.0, 0, false, Color.YELLOW },
         { "Wenus", 6052.0, 0, false, Color.YELLOW }, { "Ziemia", 6378.0, 1, false, Color.BLUE },
         { "Mars", 3397.0, 2, false, Color.RED }, { "Jupiter", 71492.0, 16, true, Color.ORANGE },
         { "Saturn", 60268.0, 18, true, Color.ORANGE },
         { "Uran", 25559.0, 17, true, Color.BLUE }, { "Neptun", 24766.0, 8, true, Color.BLUE },
         { "Pluton", 1137.0, 1, false, Color.BLACK } };

   public PlanetTableFrame()
   {
      final JTable table = new JTable(cells, columnNames);
      table.setAutoCreateRowSorter(true);
      add(new JScrollPane(table), BorderLayout.CENTER);
      JButton printButton = new JButton("Drukuj");
      printButton.addActionListener(event -> 
         { 
            try { table.print(); }
            catch (SecurityException | PrinterException ex) { ex.printStackTrace(); }
         });
      JPanel buttonPanel = new JPanel();
      buttonPanel.add(printButton);
      add(buttonPanel, BorderLayout.SOUTH);
      pack();
   }
}
