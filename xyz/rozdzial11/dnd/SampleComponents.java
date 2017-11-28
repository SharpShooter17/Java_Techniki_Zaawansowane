package dnd;

import java.awt.*;

import javax.swing.*;
import javax.swing.tree.*;

public class SampleComponents
{
   public static JTree tree()
   {
      DefaultMutableTreeNode root = new DefaultMutableTreeNode("Świat");
      DefaultMutableTreeNode country = new DefaultMutableTreeNode("USA");
      root.add(country);
      DefaultMutableTreeNode state = new DefaultMutableTreeNode("Kalifornia");
      country.add(state);
      DefaultMutableTreeNode city = new DefaultMutableTreeNode("San Jose");
      state.add(city);
      city = new DefaultMutableTreeNode("Cupertino");
      state.add(city);
      state = new DefaultMutableTreeNode("Michigan");
      country.add(state);
      city = new DefaultMutableTreeNode("Ann Arbor");
      state.add(city);
      country = new DefaultMutableTreeNode("Niemcy");
      root.add(country);
      state = new DefaultMutableTreeNode("Schleswig-Holstein");
      country.add(state);
      city = new DefaultMutableTreeNode("Kilonia");
      state.add(city);
      return new JTree(root);
   }

   public static JList<String> list()
   {
      String[] words = { "szybki", "brązowy", "głodny", "dziki", "milczący", "ogromny", "private",
            "abstract", "static", "final" };

      DefaultListModel<String> model = new DefaultListModel<>();
      for (String word : words)
         model.addElement(word);
      return new JList<>(model);
   }

   public static JTable table()
   {
      Object[][] cells = { { "Merkury", 2440.0, 0, false, Color.YELLOW },
            { "Wenus", 6052.0, 0, false, Color.YELLOW },
            { "Ziemia", 6378.0, 1, false, Color.BLUE }, 
            { "Mars", 3397.0, 2, false, Color.RED },
            { "Jowisz", 71492.0, 16, true, Color.ORANGE },
            { "Saturn", 60268.0, 18, true, Color.ORANGE },
            { "Uran", 25559.0, 17, true, Color.BLUE },
            { "Neptun", 24766.0, 8, true, Color.BLUE },
            { "Pluton", 1137.0, 1, false, Color.BLACK } };

      String[] columnNames = { "Planeta", "Promień", "Księżyców", "Gazowa", "Kolor" };
      return new JTable(cells, columnNames);
   }
}
