package list;

import java.awt.*;

import javax.swing.*;

/**
 * Ramka zawierająca listę słów i etykietę pokazującą zdanie ułożone
 * z wybranych słów. Przytrzymując klawisz Ctrl, wybrać można wiele słów,
 * a klawisz Shift pozwala na wybór całego zakresu słów.
 */
class ListFrame extends JFrame
{
   private static final int DEFAULT_WIDTH = 500;
   private static final int DEFAULT_HEIGHT = 300;

   private JPanel listPanel;
   private JList<String> wordList;
   private JLabel label;
   private JPanel buttonPanel;
   private ButtonGroup group;
   private String prefix = "Ten ";
   private String suffix = "lis przeskakuje nad leniwym psem.";

   public ListFrame()
   {
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

      String[] words = { "szybki", "brązowy", "głodny", "dziki", "cichy", "ogromny", "private",
            "abstract", "static", "final" };

      wordList = new JList<>(words);
      wordList.setVisibleRowCount(4);
      JScrollPane scrollPane = new JScrollPane(wordList);

      listPanel = new JPanel();
      listPanel.add(scrollPane);
      wordList.addListSelectionListener(event ->
         {
            StringBuilder text = new StringBuilder(prefix);
            for (String value : wordList.getSelectedValuesList())
            {                  
               text.append(value);
               text.append(" ");
            }
            text.append(suffix);

            label.setText(text.toString());
         });

      buttonPanel = new JPanel();
      group = new ButtonGroup();
      makeButton("W pionie", JList.VERTICAL);
      makeButton("W pionie z przenoszeniem", JList.VERTICAL_WRAP);
      makeButton("W poziomie z przenoszeniem", JList.HORIZONTAL_WRAP);

      add(listPanel, BorderLayout.NORTH);
      label = new JLabel(prefix + suffix);
      add(label, BorderLayout.CENTER);
      add(buttonPanel, BorderLayout.SOUTH);
   }

   /**
    * Tworzy przycisk wyboru układu listy.
    * @param label etykieta przycisku
    * @param orientation układ listy
    */
   private void makeButton(String label, final int orientation)
   {
      JRadioButton button = new JRadioButton(label);
      buttonPanel.add(button);
      if (group.getButtonCount() == 0) button.setSelected(true);
      group.add(button);
      button.addActionListener(event ->
         {
            wordList.setLayoutOrientation(orientation);
            listPanel.revalidate();
         });
   }
}