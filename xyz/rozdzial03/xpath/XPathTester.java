package xpath;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.xml.namespace.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.*;

/**
 * Program wyznaczający wyrażenia XPath.
 * @version 1.02 2016-05-10
 * @author Cay Horstmann
 */
public class XPathTester
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(() ->
         {
            JFrame frame = new XPathFrame();
            frame.setTitle("XPathTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
         });
   }
}

/**
 * Ramka prezentująca dokument XML oraz posiadająca pola
 * umożliwiające wpisanie wyrażenia i wyświetlenie wyniku.
 */
class XPathFrame extends JFrame
{
   private DocumentBuilder builder;
   private Document doc;
   private XPath path;
   private JTextField expression;
   private JTextField result;
   private JTextArea docText;
   private JComboBox<String> typeCombo;

   public XPathFrame()
   {
      JMenu fileMenu = new JMenu("Plik");
      JMenuItem openItem = new JMenuItem("Otwórz");
      openItem.addActionListener(event -> openFile());
      fileMenu.add(openItem);

      JMenuItem exitItem = new JMenuItem("Zakończ");
      exitItem.addActionListener(event -> System.exit(0));
      fileMenu.add(exitItem);

      JMenuBar menuBar = new JMenuBar();
      menuBar.add(fileMenu);
      setJMenuBar(menuBar);

      ActionListener listener = event -> evaluate();
      expression = new JTextField(20);
      expression.addActionListener(listener);
      JButton evaluateButton = new JButton("Przetwórz");
      evaluateButton.addActionListener(listener);

      typeCombo = new JComboBox<String>(new String[] { 
         "STRING", "NODE", "NODESET", "NUMBER", "BOOLEAN" });
      typeCombo.setSelectedItem("STRING");

      JPanel panel = new JPanel();
      panel.add(expression);
      panel.add(typeCombo);
      panel.add(evaluateButton);
      docText = new JTextArea(10, 40);
      result = new JTextField();
      result.setBorder(new TitledBorder("Wynik"));

      add(panel, BorderLayout.NORTH);
      add(new JScrollPane(docText), BorderLayout.CENTER);
      add(result, BorderLayout.SOUTH);

      try
      {
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         builder = factory.newDocumentBuilder();
      }
      catch (ParserConfigurationException e)
      {
         JOptionPane.showMessageDialog(this, e);
      }

      XPathFactory xpfactory = XPathFactory.newInstance();
      path = xpfactory.newXPath();
      pack();
   }

   /**
    * Otwiera plik i ładuje dokument.
    */
   public void openFile()
   {
      JFileChooser chooser = new JFileChooser();
      chooser.setCurrentDirectory(new File("xpath"));

      chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Pliki XML", "xml"));
      int r = chooser.showOpenDialog(this);
      if (r != JFileChooser.APPROVE_OPTION) return;
      File file = chooser.getSelectedFile();
      try
      {
         docText.setText(new String(Files.readAllBytes(file.toPath())));
         doc = builder.parse(file);
      }
      catch (IOException e)
      {
         JOptionPane.showMessageDialog(this, e);
      }
      catch (SAXException e)
      {
         JOptionPane.showMessageDialog(this, e);
      }
   }

   public void evaluate()
   {
      try
      {
         String typeName = (String) typeCombo.getSelectedItem();
         QName returnType = (QName) XPathConstants.class.getField(typeName).get(null);
         Object evalResult = path.evaluate(expression.getText(), doc, returnType);
         if (typeName.equals("NODESET"))
         {
            NodeList list = (NodeList) evalResult;
            // Nie można użyć String.join gdyż, NodeList nie jest typu Iterable
            StringJoiner joiner = new StringJoiner(",", "{", "}");
            for (int i = 0; i < list.getLength(); i++)
               joiner.add("" + list.item(i));
            result.setText("" + joiner);
         }
         else result.setText("" + evalResult);
      }
      catch (XPathExpressionException e)
      {
         result.setText("" + e);
      }
      catch (Exception e) // wyjątek refleksji
      {
         e.printStackTrace();
      }
   }
}
