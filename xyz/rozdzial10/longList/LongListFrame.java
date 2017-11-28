package longList;

import java.awt.*;

import javax.swing.*;

/**
 * Ramka zawierająca długą listę słów i etykietę pokazującą zdanie
 * ułożone z wybranych słów.
 */
public class LongListFrame extends JFrame
{
   private JList<String> wordList;
   private JLabel label;
   private String prefix = "Szybki brązowy ";
   private String suffix = " przeskoczył nad leniwym psem.";

   public LongListFrame()
   {
      wordList = new JList<String>(new WordListModel(3));
      wordList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      wordList.setPrototypeCellValue("www");
      JScrollPane scrollPane = new JScrollPane(wordList);

      JPanel p = new JPanel();
      p.add(scrollPane);
      wordList.addListSelectionListener(event -> setSubject(wordList.getSelectedValue()));

      Container contentPane = getContentPane();
      contentPane.add(p, BorderLayout.NORTH);
      label = new JLabel(prefix + suffix);
      contentPane.add(label, BorderLayout.CENTER);
      setSubject("lis");
      pack();
   }

   /**
    * Określa podmiot zdania pokazywanego za pomocą etykiety.
    * @param word nowy podmiot zdania
    */
   public void setSubject(String word)
   {
      StringBuilder text = new StringBuilder(prefix);
      text.append(word);
      text.append(suffix);
      label.setText(text.toString());
   }
}
