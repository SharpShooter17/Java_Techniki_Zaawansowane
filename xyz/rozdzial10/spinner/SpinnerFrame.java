package spinner;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;

/**
 * Ramka z panelem zawierającym kilka komponentów JSpinner
 * oraz przycisk wyświetlający ich wartości.
 */
public class SpinnerFrame extends JFrame
{
   private JPanel mainPanel;
   private JButton okButton;

   public SpinnerFrame()
   {
      JPanel buttonPanel = new JPanel();
      okButton = new JButton("Ok");
      buttonPanel.add(okButton);
      add(buttonPanel, BorderLayout.SOUTH);

      mainPanel = new JPanel();
      mainPanel.setLayout(new GridLayout(0, 3));
      add(mainPanel, BorderLayout.CENTER);

      JSpinner defaultSpinner = new JSpinner();
      addRow("Domyślny", defaultSpinner);

      JSpinner boundedSpinner = new JSpinner(new SpinnerNumberModel(5, 0, 10, 0.5));
      addRow("Ograniczony", boundedSpinner);

      String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getAvailableFontFamilyNames();

      JSpinner listSpinner = new JSpinner(new SpinnerListModel(fonts));
      addRow("Lista", listSpinner);

      JSpinner reverseListSpinner = new JSpinner(new SpinnerListModel(fonts)
         {
            public Object getNextValue() { return super.getPreviousValue(); }
            public Object getPreviousValue() { return super.getNextValue(); }
         });
      addRow("Odwrócona lista", reverseListSpinner);

      JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
      addRow("Data", dateSpinner);

      JSpinner betterDateSpinner = new JSpinner(new SpinnerDateModel());
      String pattern = ((SimpleDateFormat) DateFormat.getDateInstance()).toPattern();
      betterDateSpinner.setEditor(new JSpinner.DateEditor(betterDateSpinner, pattern));
      addRow("Lepsza data", betterDateSpinner);
      
      JSpinner timeSpinner = new JSpinner(new SpinnerDateModel());
      pattern = ((SimpleDateFormat) DateFormat.getTimeInstance(DateFormat.SHORT)).toPattern();
      timeSpinner.setEditor(new JSpinner.DateEditor(timeSpinner, pattern));
      addRow("Godzina", timeSpinner);

      JSpinner permSpinner = new JSpinner(new PermutationSpinnerModel("grad"));
      addRow("Permutacje słowa", permSpinner);
      pack();
   }

   /**
    * Dodaje wiersz do głównego panelu.
    * @param labelText etykieta komponentu JSpinner
    * @param spinner przykładowy komponent JSpinner
    */
   public void addRow(String labelText, final JSpinner spinner)
   {
      mainPanel.add(new JLabel(labelText));
      mainPanel.add(spinner);
      final JLabel valueLabel = new JLabel();
      mainPanel.add(valueLabel);
      okButton.addActionListener(event ->
         {
            Object value = spinner.getValue();
            valueLabel.setText(value.toString());
         });
   }
}