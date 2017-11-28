package listRendering;

import java.awt.*;
import java.util.*;

import javax.swing.*;

/**
 * Ta ramka zawiera listę ze zbiorem czcionek oraz obszar tekstowy, w którym tekst będzie 
 * wyświetlany wybraną czcionką.
 */
public class ListRenderingFrame extends JFrame
{
   private static final int TEXT_ROWS = 8;
   private static final int TEXT_COLUMNS = 20;

   private JTextArea text;
   private JList<Font> fontList;

   public ListRenderingFrame()
   {
      java.util.List<Font> fonts = new ArrayList<>();
      final int SIZE = 24;
      fonts.add(new Font("Serif", Font.PLAIN, SIZE));
      fonts.add(new Font("SansSerif", Font.PLAIN, SIZE));
      fonts.add(new Font("Monospaced", Font.PLAIN, SIZE));
      fonts.add(new Font("Dialog", Font.PLAIN, SIZE));
      fonts.add(new Font("DialogInput", Font.PLAIN, SIZE));
      fontList = new JList<Font>(fonts.toArray(new Font[]{}));
      fontList.setVisibleRowCount(4);
      fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      fontList.setCellRenderer(new FontCellRenderer());
      JScrollPane scrollPane = new JScrollPane(fontList);

      JPanel p = new JPanel();
      p.add(scrollPane);
      fontList.addListSelectionListener(event ->
         {
            Font font = fontList.getSelectedValue();
            text.setFont(font);
         });

      Container contentPane = getContentPane();
      contentPane.add(p, BorderLayout.SOUTH);
      text = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
      text.setText("Szybki brązowy list przeskoczył nad liniwym psem.");
      text.setFont(fonts.get(0));
      text.setLineWrap(true);
      text.setWrapStyleWord(true);
      contentPane.add(text, BorderLayout.CENTER);
      pack();
   }
}