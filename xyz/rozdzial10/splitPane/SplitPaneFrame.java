package splitPane;

import java.awt.*;

import javax.swing.*;

/**
 * Ramka zawierająca dwa zagnieżdżone panele dzielone wyświetlające
 * obrazki planet i opisy.
 */
class SplitPaneFrame extends JFrame
{
   private static final int DEFAULT_WIDTH = 300;
   private static final int DEFAULT_HEIGHT = 300;

   private Planet[] planets = { new Planet("Merkury", 2440, 0), new Planet("Wenus", 6052, 0),
         new Planet("Ziemia", 6378, 1), new Planet("Mars", 3397, 2),
         new Planet("Jowisz", 71492, 16), new Planet("Saturn", 60268, 18),
         new Planet("Uran", 25559, 17), new Planet("Neptun", 24766, 8),
         new Planet("Pluton", 1137, 1), };

   public SplitPaneFrame()
   {
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

      // tworzy komponenty dla prezentacji nazw i opisu planet oraz ich obrazków

      final JList<Planet> planetList = new JList<>(planets);
      final JLabel planetImage = new JLabel();
      final JTextArea planetDescription = new JTextArea();

      planetList.addListSelectionListener(event ->
         {
            Planet value = (Planet) planetList.getSelectedValue();

            // aktualizuje obrazek i opis

            planetImage.setIcon(value.getImage());
            planetDescription.setText(value.getDescription());
         });

      // tworzy panele dzielone

      JSplitPane innerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, planetList, planetImage);

      innerPane.setContinuousLayout(true);
      innerPane.setOneTouchExpandable(true);

      JSplitPane outerPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, innerPane,
            planetDescription);

      add(outerPane, BorderLayout.CENTER);
   }
}