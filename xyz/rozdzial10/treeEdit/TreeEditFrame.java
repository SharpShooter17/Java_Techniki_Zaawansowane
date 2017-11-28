package treeEdit;

import java.awt.*;

import javax.swing.*;
import javax.swing.tree.*;

/**
 * Ramka zawierająca przyciski i edytowane drzewo.
 */
public class TreeEditFrame extends JFrame
{
   private static final int DEFAULT_WIDTH = 400;
   private static final int DEFAULT_HEIGHT = 200;

   private DefaultTreeModel model;
   private JTree tree;

   public TreeEditFrame()
   {
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

      // tworzy drzewo

      TreeNode root = makeSampleTree();
      model = new DefaultTreeModel(root);
      tree = new JTree(model);
      tree.setEditable(true);

      // umieszcza drzewo w przewijalnym panelu

      JScrollPane scrollPane = new JScrollPane(tree);
      add(scrollPane, BorderLayout.CENTER);

      makeButtons();
   }

   public TreeNode makeSampleTree()
   {
      DefaultMutableTreeNode root = new DefaultMutableTreeNode("Świat");
      DefaultMutableTreeNode country = new DefaultMutableTreeNode("USA");
      root.add(country);
      DefaultMutableTreeNode state = new DefaultMutableTreeNode("Kalifornia");
      country.add(state);
      DefaultMutableTreeNode city = new DefaultMutableTreeNode("San Jose");
      state.add(city);
      city = new DefaultMutableTreeNode("San Diego");
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
      return root;
   }

   /**
    * Tworzy przyciski umożliwiające dodanie węzła równorzędnego
    * dodanie węzła podrzędnego oraz usunięcie wybranego węzła.
    */
   public void makeButtons()
   {
      JPanel panel = new JPanel();
      JButton addSiblingButton = new JButton("Dodaj równorzędny");
      addSiblingButton.addActionListener(event ->
         {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
                  .getLastSelectedPathComponent();

            if (selectedNode == null) return;

            DefaultMutableTreeNode parent = (DefaultMutableTreeNode) selectedNode.getParent();

            if (parent == null) return;

            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("Nowy");

            int selectedIndex = parent.getIndex(selectedNode);
            model.insertNodeInto(newNode, parent, selectedIndex + 1);

            // wyświetla nowy węzeł

            TreeNode[] nodes = model.getPathToRoot(newNode);
            TreePath path = new TreePath(nodes);
            tree.scrollPathToVisible(path);
         });
      panel.add(addSiblingButton);

      JButton addChildButton = new JButton("Dodaj podrzędny");
      addChildButton.addActionListener(event ->
         {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
                  .getLastSelectedPathComponent();

            if (selectedNode == null) return;

            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("Nowy");
            model.insertNodeInto(newNode, selectedNode, selectedNode.getChildCount());

            // wyświetla nowy węzeł

            TreeNode[] nodes = model.getPathToRoot(newNode);
            TreePath path = new TreePath(nodes);
            tree.scrollPathToVisible(path);
         });
      panel.add(addChildButton);

      JButton deleteButton = new JButton("Usuń");
      deleteButton.addActionListener(event ->
         {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
                  .getLastSelectedPathComponent();

            if (selectedNode != null && selectedNode.getParent() != null) model
                  .removeNodeFromParent(selectedNode);
         });
      panel.add(deleteButton);
      add(panel, BorderLayout.SOUTH);
   }
}