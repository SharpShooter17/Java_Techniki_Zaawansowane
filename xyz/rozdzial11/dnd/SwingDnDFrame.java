package dnd;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

public class SwingDnDFrame extends JFrame
{
   public SwingDnDFrame()
   {
      JTabbedPane tabbedPane = new JTabbedPane();

      JList<String> list = SampleComponents.list();
      tabbedPane.addTab("Lista", list);
      JTable table = SampleComponents.table();
      tabbedPane.addTab("Tabela", table);
      JTree tree = SampleComponents.tree();
      tabbedPane.addTab("Drzewo", tree);
      JFileChooser fileChooser = new JFileChooser();
      tabbedPane.addTab("Wybór pliku", fileChooser);
      JColorChooser colorChooser = new JColorChooser();
      tabbedPane.addTab("Wybór koloru", colorChooser);

      final JTextArea textArea = new JTextArea(4, 40);
      JScrollPane scrollPane = new JScrollPane(textArea);
      scrollPane.setBorder(new TitledBorder(new EtchedBorder(), "Tu przecinągnij tekst"));

      JTextField textField = new JTextField("Tu przeciągnij kolor");
      textField.setTransferHandler(new TransferHandler("background"));
      
      tabbedPane.addChangeListener(event -> textArea.setText(""));             

      tree.setDragEnabled(true);
      table.setDragEnabled(true);
      list.setDragEnabled(true);
      fileChooser.setDragEnabled(true);
      colorChooser.setDragEnabled(true);
      textField.setDragEnabled(true);

      add(tabbedPane, BorderLayout.NORTH);
      add(scrollPane, BorderLayout.CENTER);
      add(textField, BorderLayout.SOUTH);
      pack();
   }
}
