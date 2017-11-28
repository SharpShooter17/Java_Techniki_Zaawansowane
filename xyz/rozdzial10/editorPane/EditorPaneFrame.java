package editorPane;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Ramka zawierająca panel edytora, pole tekstowe i przycisk Load
 * umożliwiające wprowadzenie adresu URL i załadowanie strony,
 * a także przycisk Back umożliwiający powrót do poprzedniej strony.
 */
public class EditorPaneFrame extends JFrame
{
   private static final int DEFAULT_WIDTH = 750;
   private static final int DEFAULT_HEIGHT = 400;

   public EditorPaneFrame()
   {
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

      final Stack<String> urlStack = new Stack<>();
      final JEditorPane editorPane = new JEditorPane();
      final JTextField url = new JTextField(30);

      // instaluje obiekt nasłuchujący hiperłącza

      editorPane.setEditable(false);
      editorPane.addHyperlinkListener(event ->
         {
            if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
            {
               try
               {
                  // zapamiętuje adres URL na potrzeby przycisku Back
                  urlStack.push(event.getURL().toString());
                  // prezentuje adres URL w polu tekstowym
                  url.setText(event.getURL().toString());
                  editorPane.setPage(event.getURL());
               }
               catch (IOException e)
               {
                  editorPane.setText("Wyjątek: " + e);
               }
            }
         });

      // konfiguruje pole wyboru umożliwiające włączenie trybu edycji

      final JCheckBox editable = new JCheckBox();
      editable.addActionListener(event -> 
         editorPane.setEditable(editable.isSelected()));      

      // tworzy obiekt nasłuchujący przycisku Wczytaj

      ActionListener listener = event ->
         {
            try
            {
               // zapamiętuje adres URL na potrzeby przycisku Wstecz
               urlStack.push(url.getText());
               editorPane.setPage(url.getText());
            }
            catch (IOException e)
            {
               editorPane.setText("Wyjątek: " + e);
            }
         };

      JButton loadButton = new JButton("Wczytaj");
      loadButton.addActionListener(listener);
      url.addActionListener(listener);

      // Konfiguruje przycisk Back i związaną z nim akcję

      JButton backButton = new JButton("Wstecz");
      backButton.addActionListener(event ->
         {
            if (urlStack.size() <= 1) return;
            try
            {
               // pobiera zapamiętany adres URL
               urlStack.pop();
               // wyświetla adres URL w polu tekstowym
               String urlString = urlStack.peek();
               url.setText(urlString);
               editorPane.setPage(urlString);
            }
            catch (IOException e)
            {
               editorPane.setText("Wyjątek: " + e);
            }
         });

      add(new JScrollPane(editorPane), BorderLayout.CENTER);

      // umieszcza wszystkie komponenty na głównym panelu okna

      JPanel panel = new JPanel();
      panel.add(new JLabel("Adres URL"));
      panel.add(url);
      panel.add(loadButton);
      panel.add(backButton);
      panel.add(new JLabel("Do edycji"));
      panel.add(editable);

      add(panel, BorderLayout.SOUTH);
   }
}
