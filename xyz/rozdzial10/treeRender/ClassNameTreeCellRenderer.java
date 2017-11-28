package treeRender;

import java.awt.*;
import java.lang.reflect.*;
import javax.swing.*;
import javax.swing.tree.*;

/**
 * Klasa opisująca węzły drzewa czcionką zwykłą lub pochyloną (w przypadku 
 * klas abstrakcyjnych).
 */
public class ClassNameTreeCellRenderer extends DefaultTreeCellRenderer
{
   private Font plainFont = null;
   private Font italicFont = null;

   public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
         boolean expanded, boolean leaf, int row, boolean hasFocus)
   {
      super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
      // pobiera obiekt użytkownika
      DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
      Class<?> c = (Class<?>) node.getUserObject();

      // przy pierwszym użyciu tworzy czcionkę pochyłą odpowiadającą danej czcionce prostej
      if (plainFont == null)
      {
         plainFont = getFont();
         // obiekt rysujący komórkę drzewa wywoływany jest czasami
         // dla etykiety, która nie posiada określonej czcionki (null).
         if (plainFont != null) italicFont = plainFont.deriveFont(Font.ITALIC);
      }

      // wybiera czcionkę pochyłą, jeśli klasa jest abstrakcyjna
      if ((c.getModifiers() & Modifier.ABSTRACT) == 0) setFont(plainFont);
      else setFont(italicFont);
      return this;
   }
}
