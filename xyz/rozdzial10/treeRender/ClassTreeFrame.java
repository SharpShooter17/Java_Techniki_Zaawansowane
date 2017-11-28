package treeRender;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;

/**
 * Ramka zawierająca drzewo klas, pole tekstowe pokazujące składowe 
 * wybranej klasy oraz pole tekstowe umożliwiające dodawanie nowych klas 
 * do drzewa.
 */
public class ClassTreeFrame extends JFrame
{
   private static final int DEFAULT_WIDTH = 700;
   private static final int DEFAULT_HEIGHT = 300;

   private DefaultMutableTreeNode root;
   private DefaultTreeModel model;
   private JTree tree;
   private JTextField textField;
   private JTextArea textArea;

   public ClassTreeFrame()
   {
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

      // w korzeniu drzewa znajduje się klasa Object
      root = new DefaultMutableTreeNode(java.lang.Object.class);
      model = new DefaultTreeModel(root);
      tree = new JTree(model);

      // dodanie tej klasy pozwala wypełnić drzewo jakimiś danymi
      addClass(getClass());

      // tworzy ikony węzłów
      ClassNameTreeCellRenderer renderer = new ClassNameTreeCellRenderer();
      renderer.setClosedIcon(new ImageIcon(getClass().getResource("red-ball.gif")));
      renderer.setOpenIcon(new ImageIcon(getClass().getResource("yellow-ball.gif")));
      renderer.setLeafIcon(new ImageIcon(getClass().getResource("blue-ball.gif")));
      tree.setCellRenderer(renderer);
      
      // konfiguruje sposób wyboru węzłów
      tree.addTreeSelectionListener(event ->
         {
            // użytkownik wybrał inny węzeł drzewa i należy zaktualizować opis klasy
            TreePath path = tree.getSelectionPath();
            if (path == null) return;
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path
                  .getLastPathComponent();
            Class<?> c = (Class<?>) selectedNode.getUserObject();
            String description = getFieldDescription(c);
            textArea.setText(description);
         });
      int mode = TreeSelectionModel.SINGLE_TREE_SELECTION;
      tree.getSelectionModel().setSelectionMode(mode);

      // obszar tekstowy zawierający opis klasy
      textArea = new JTextArea();

      // dodaje komponenty drzewa i pola tekstowego do panelu
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(1, 2));
      panel.add(new JScrollPane(tree));
      panel.add(new JScrollPane(textArea));

      add(panel, BorderLayout.CENTER);

      addTextField();
   }

   /**
    * Dodaje pole tekstowe i przycisk "Dodaj" umożliwiające dodanie 
    * nowej klasy do drzewa.
    */
   public void addTextField()
   {
      JPanel panel = new JPanel();

      ActionListener addListener = event ->
         {
            // dodaje do drzewa klasę, której nazwa znajduje się w polu tekstowym
            try
            {
               String text = textField.getText();
               addClass(Class.forName(text)); // czyści pole
               textField.setText("");
            }
            catch (ClassNotFoundException e)
            {
               JOptionPane.showMessageDialog(null, "Nie znaleziono klasy");
            }
         };

      // pole tekstowe, w którym wprowadzane są nazwy nowych klas
      textField = new JTextField(20);
      textField.addActionListener(addListener);
      panel.add(textField);

      JButton addButton = new JButton("Dodaj");
      addButton.addActionListener(addListener);
      panel.add(addButton);

      add(panel, BorderLayout.SOUTH);
   }

   /**
    * Odnajduje obiekt w drzewie.
    * @param obj poszukiwany obiekt
    * @return the węzeł zawierający obiekt lub null, jeśli obiektu nie udało się znaleźć
    */
   @SuppressWarnings("unchecked")
   public DefaultMutableTreeNode findUserObject(Object obj)
   {
      // znajduje węzeł zawierający przekazany obiekt 
      Enumeration<TreeNode> e = (Enumeration<TreeNode>) root.breadthFirstEnumeration();
      while (e.hasMoreElements())
      {
         DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
         if (node.getUserObject().equals(obj)) return node;
      }
      return null;
   }

   /**
    * Dodaje do drzewa klasę i jej klasy bazowe, których nie ma 
    * jeszcze w drzewie.
    * @param c dodawana klasa
    * @return nowo dodany węzeł.
    */
   public DefaultMutableTreeNode addClass(Class<?> c)
   {
      // dodaje nową klasę do drzewa

      // pomija typy, które nie są klasami
      if (c.isInterface() || c.isPrimitive()) return null;

      // jeśli klasa znajduje się już w drzewie, to zwraca jej węzeł
      DefaultMutableTreeNode node = findUserObject(c);
      if (node != null) return node;

      // jeśli klasa nie znajduje się w drzewie, 
      // to najpierw należy dodać rekurencyjnie do drzewa jej klasy bazowe

      Class<?> s = c.getSuperclass();

      DefaultMutableTreeNode parent;
      if (s == null) parent = root;
      else parent = addClass(s);

      // dodaje klasę jako węzeł podrzędny
      DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(c);
      model.insertNodeInto(newNode, parent, parent.getChildCount());

      // sprawia, że węzeł jest widoczny
      TreePath path = new TreePath(model.getPathToRoot(newNode));
      tree.makeVisible(path);

      return newNode;
   }

   /**
    * Zwraca opis składowych klasy.
    * @param klasa
    * @return łańcuch znaków zawierający nazwy i typy zmiennych
    */
   public static String getFieldDescription(Class<?> c)
   {
      // korzysta z mechanizmu refleksji
      StringBuilder r = new StringBuilder();
      Field[] fields = c.getDeclaredFields();
      for (int i = 0; i < fields.length; i++)
      {
         Field f = fields[i];
         if ((f.getModifiers() & Modifier.STATIC) != 0) r.append("static ");
         r.append(f.getType().getName());
         r.append(" ");
         r.append(f.getName());
         r.append("\n");
      }
      return r.toString();
   }
}
