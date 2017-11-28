package jaas;

import java.awt.*;

import javax.security.auth.*;
import javax.security.auth.login.*;
import javax.swing.*;

/**
 * Ta ramka tworzy pola testowe do podania nazwy użytkownika i hasła,
 * pole do podania nazwy żądanej właściwości systemowej oraz pole
 * do wyświetlenia wartości tej właściwości.
 */
public class JAASFrame extends JFrame
{
   private JTextField username;
   private JPasswordField password;
   private JTextField propertyName;
   private JTextField propertyValue;

   public JAASFrame()
   {
      username = new JTextField(20);
      password = new JPasswordField(20);
      propertyName = new JTextField("user.home");
      propertyValue = new JTextField(20);
      propertyValue.setEditable(false);

      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(0, 2));
      panel.add(new JLabel("nazwa użytkownika:"));
      panel.add(username);
      panel.add(new JLabel("hasło:"));
      panel.add(password);
      panel.add(propertyName);
      panel.add(propertyValue);
      add(panel, BorderLayout.CENTER);

      JButton getValueButton = new JButton("Pobierz wartość");
      getValueButton.addActionListener(event -> getValue());
      JPanel buttonPanel = new JPanel();
      buttonPanel.add(getValueButton);
      add(buttonPanel, BorderLayout.SOUTH);
      pack();
   }

   public void getValue()
   {
      try
      {
         LoginContext context = new LoginContext("Login1", new SimpleCallbackHandler(
            username.getText(), password.getPassword()));
         System.out.println("Próba logowania z użyciem: " + username.getText() 
            + " oraz " + new String(password.getPassword()));
         context.login();
         Subject subject = context.getSubject();
         propertyValue.setText(""
            + Subject.doAsPrivileged(subject, new SysPropAction(propertyName.getText()), null));
         context.logout();
      }
      catch (LoginException ex)
      {
         ex.printStackTrace();
         Throwable ex2 = ex.getCause();
         if (ex2 != null) ex2.printStackTrace();         
      }
   }
}