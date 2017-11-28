package permissions;

import java.awt.*;

import javax.swing.*;

/**
 * Demonstruje wykorzystanie własnej klasy pozwoleń WordCheckPermission.
 * @version 1.04 2016-05-10
 * @author Cay Horstmann
 */
public class PermissionTest
{
   public static void main(String[] args)
   {
      System.setProperty("java.security.policy", "permissions/PermissionTest.policy");      
      System.setSecurityManager(new SecurityManager());
      EventQueue.invokeLater(() ->
         {
            JFrame frame = new PermissionTestFrame();
            frame.setTitle("PermissionTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
         });
   }
}

/**
 * Ramka zawierająca pole tekstowe umożliwiające dodawanie tekstu do obszaru 
 * tekstowego, pod warunkiem że nie zawiera on zabronionych słów.
 */
class PermissionTestFrame extends JFrame
{
   private JTextField textField;
   private WordCheckTextArea textArea;
   private static final int TEXT_ROWS = 20;
   private static final int TEXT_COLUMNS = 60;

   public PermissionTestFrame()
   {
      textField = new JTextField(20);
      JPanel panel = new JPanel();
      panel.add(textField);
      JButton openButton = new JButton("Wstaw");
      panel.add(openButton);
      openButton.addActionListener(event -> insertWords(textField.getText()));

      add(panel, BorderLayout.NORTH);

      textArea = new WordCheckTextArea();
      textArea.setRows(TEXT_ROWS);
      textArea.setColumns(TEXT_COLUMNS);
      add(new JScrollPane(textArea), BorderLayout.CENTER);
      pack();
   }

   /**
    * Próbuje wstawić słowa do pola tekstowego. Jeśli jest to niemożliwe,
    * wyświetla okienko dialogowe z komunikatem.
    * @param words wstawiane słowa
    */
   public void insertWords(String words)
   {
      try
      {
         textArea.append(words + "\n");
      }
      catch (SecurityException ex)
      {
         JOptionPane.showMessageDialog(this, "Przykro mi, ale nie mogę tego zrobić.");
         ex.printStackTrace();
      }
   }
}

/**
 * Pole tekstowe, którego metoda append sprawdza, czy tekst dodawany do pola
 * nie zawiera brzydkich słów.
 */
class WordCheckTextArea extends JTextArea
{
   public void append(String text)
   {
      WordCheckPermission p = new WordCheckPermission(text, "insert");
      SecurityManager manager = System.getSecurityManager();
      if (manager != null) manager.checkPermission(p);
      super.append(text);
   }
}