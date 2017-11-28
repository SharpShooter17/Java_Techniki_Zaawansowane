package tabbedPane;

import java.awt.*;

import javax.swing.*;

/**
 * Ramka zawierająca panel z kartami oraz przyciski
 * umożliwiające przełączanie sposobu prezentacji kart.
 */
public class TabbedPaneFrame extends JFrame
{
   private static final int DEFAULT_WIDTH = 450;
   private static final int DEFAULT_HEIGHT = 300;

   private JTabbedPane tabbedPane;

   public TabbedPaneFrame()
   {
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

      tabbedPane = new JTabbedPane();
      // załadowanie komponentów kart odkładamy
      // do momentu ich pierwszej prezentacji

      ImageIcon icon = new ImageIcon(getClass().getResource("yellow-ball.gif"));

      tabbedPane.addTab("Merkury", icon, null);
      tabbedPane.addTab("Wenus", icon, null);
      tabbedPane.addTab("Ziemia", icon, null);
      tabbedPane.addTab("Mars", icon, null);
      tabbedPane.addTab("Jowisz", icon, null);
      tabbedPane.addTab("Saturn", icon, null);
      tabbedPane.addTab("Uran", icon, null);
      tabbedPane.addTab("Neptun", icon, null);
      tabbedPane.addTab("Pluton", null, null);
      
      final int plutoIndex = tabbedPane.indexOfTab("Pluton");      
      JPanel plutoPanel = new JPanel();
      plutoPanel.add(new JLabel("Pluton", icon, SwingConstants.LEADING));
      JToggleButton plutoCheckBox = new JCheckBox();
      plutoCheckBox.addActionListener(event -> tabbedPane.remove(plutoIndex));            
      plutoPanel.add(plutoCheckBox);      
      tabbedPane.setTabComponentAt(plutoIndex, plutoPanel);
      
      add(tabbedPane, "Center");

      tabbedPane.addChangeListener(event ->
         {
            // sprawdza, czy na karcie umieszczony jest już komponent
            if (tabbedPane.getSelectedComponent() == null)
            {
               // ładuje obrazek ikony
               int n = tabbedPane.getSelectedIndex();
               loadTab(n);
            }            
         });

      loadTab(0);

      JPanel buttonPanel = new JPanel();
      ButtonGroup buttonGroup = new ButtonGroup();
      JRadioButton wrapButton = new JRadioButton("Zawinięte karty");
      wrapButton.addActionListener(event ->
         tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT));
      buttonPanel.add(wrapButton);
      buttonGroup.add(wrapButton);
      wrapButton.setSelected(true);
      JRadioButton scrollButton = new JRadioButton("Przewijanie kart");
      scrollButton.addActionListener(event ->
         tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT));
      buttonPanel.add(scrollButton);
      buttonGroup.add(scrollButton);
      add(buttonPanel, BorderLayout.SOUTH);
   }

   /**
    * Ładuje kartę o podanym indeksie.
    * @param n indeks ładowanej karty
    */
   private void loadTab(int n)
   {
      String title = tabbedPane.getTitleAt(n);
      ImageIcon planetIcon = new ImageIcon(getClass().getResource(title + ".gif"));
      tabbedPane.setComponentAt(n, new JLabel(planetIcon));

      // zaznacza, że karta była już przeglądana

      tabbedPane.setIconAt(n, new ImageIcon(getClass().getResource("red-ball.gif")));
   }
}
