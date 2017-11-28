package progressMonitor;

import java.awt.*;

import javax.swing.*;

/**
 * Ramka zawierająca przycisk uruchamiający symulację czasochłonnej 
 * operacji oraz pole tekstowe.
 */
class ProgressMonitorFrame extends JFrame
{
   public static final int TEXT_ROWS = 10;
   public static final int TEXT_COLUMNS = 40;

   private Timer cancelMonitor;
   private JButton startButton;
   private ProgressMonitor progressDialog;
   private JTextArea textArea;
   private SimulatedActivity activity;

   public ProgressMonitorFrame()
   {
      // pole tekstowe, w którym prezentowane jest działanie wątku
      textArea = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);

      // tworzy panel przycisków
      JPanel panel = new JPanel();
      startButton = new JButton("Start");
      panel.add(startButton);

      add(new JScrollPane(textArea), BorderLayout.CENTER);
      add(panel, BorderLayout.SOUTH);

      // tworzy obiekt nasłuchujący przycisku

      startButton.addActionListener(event ->
         {
            startButton.setEnabled(false);
            final int MAX = 1000;

            // uruchamia symulację
            activity = new SimulatedActivity(MAX);
            activity.execute();
            
            // uruchamia okno dialogowe monitora
            progressDialog = new ProgressMonitor(ProgressMonitorFrame.this,
                  "Oczekiwanie na symulowaną aktywność", null, 0, MAX);
            cancelMonitor.start();              
         });

      // konfiguruje akcję licznika czasu

      cancelMonitor = new Timer(500, event ->
         {               
            if (progressDialog.isCanceled())
            {                  
               activity.cancel(true);
               startButton.setEnabled(true);                  
            }
            else if (activity.isDone())
            {
               progressDialog.close();
               startButton.setEnabled(true);                  
            }
            else
            {
               progressDialog.setProgress(activity.getProgress());                  
            }
         });
      pack();
   }

   class SimulatedActivity extends SwingWorker<Void, Integer>
   {
      private int current;
      private int target;

      /**
       * Tworzy wątek symulowanej operacji. Zwiększa on wartość licznika
       * do momentu osiągnięcia wartości docelowej.
       * @param t wartość docelowa licznika.
       */
      public SimulatedActivity(int t)
      {
         current = 0;
         target = t;
      }

      protected Void doInBackground() throws Exception
      {
         try
         {
            while (current < target)
            {
               Thread.sleep(100);
               current++;
               textArea.append(current + "\n");               
               setProgress(current);
            }
         }
         catch (InterruptedException e)
         {
         }
         return null;
      }     
   }      
}