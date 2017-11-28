package tableSelection;

import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

/**
 * Ta ramka pokazuje tabliczkę mnożenie i udostępnia menu pozwalające na wybieranie trybu
 * zaznaczania wierszy, kolumn i komórek, jak również do dodawania i usuwania wierszy 
 * oraz kolumn tabeli.
 */
class TableSelectionFrame extends JFrame
{
   private DefaultTableModel model;
   private JTable table;
   private ArrayList<TableColumn> removedColumns;

   private static final int DEFAULT_WIDTH = 400;
   private static final int DEFAULT_HEIGHT = 300;

   public TableSelectionFrame()
   {
      setTitle("TableSelectionTest");
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

      // przygotowanie tabliczki mnożenia

      model = new DefaultTableModel(10, 10);

      for (int i = 0; i < model.getRowCount(); i++)
         for (int j = 0; j < model.getColumnCount(); j++)
            model.setValueAt((i + 1) * (j + 1), i, j);

      table = new JTable(model);

      add(new JScrollPane(table), "Center");

      removedColumns = new ArrayList<TableColumn>();

      // utworzenie menu

      JMenuBar menuBar = new JMenuBar();
      setJMenuBar(menuBar);

      JMenu selectionMenu = new JMenu("Zaznaczanie");
      menuBar.add(selectionMenu);

      final JCheckBoxMenuItem rowsItem = new JCheckBoxMenuItem("Wiersze");
      final JCheckBoxMenuItem columnsItem = new JCheckBoxMenuItem("Kolumny");
      final JCheckBoxMenuItem cellsItem = new JCheckBoxMenuItem("Komórki");

      rowsItem.setSelected(table.getRowSelectionAllowed());
      columnsItem.setSelected(table.getColumnSelectionAllowed());
      cellsItem.setSelected(table.getCellSelectionEnabled());

      rowsItem.addActionListener(event ->
         {
            table.clearSelection();
            table.setRowSelectionAllowed(rowsItem.isSelected());
            cellsItem.setSelected(table.getCellSelectionEnabled());
         });
      selectionMenu.add(rowsItem);

      columnsItem.addActionListener(event ->
         {
            table.clearSelection();
            table.setColumnSelectionAllowed(columnsItem.isSelected());
            cellsItem.setSelected(table.getCellSelectionEnabled());
         });
      selectionMenu.add(columnsItem);

      cellsItem.addActionListener(event ->
         {
            table.clearSelection();
            table.setCellSelectionEnabled(cellsItem.isSelected());
            rowsItem.setSelected(table.getRowSelectionAllowed());
            columnsItem.setSelected(table.getColumnSelectionAllowed());
         });
      selectionMenu.add(cellsItem);

      JMenu tableMenu = new JMenu("Edycja");
      menuBar.add(tableMenu);

      JMenuItem hideColumnsItem = new JMenuItem("Ukryj kolumny");
      hideColumnsItem.addActionListener(event ->
         {
            int[] selected = table.getSelectedColumns();
            TableColumnModel columnModel = table.getColumnModel();

            // usuwa z widoku kolumny, zaczynając do ostatniego 
            // indeksu, tak że numery kolumn nie ulegają zmianie

            for (int i = selected.length - 1; i >= 0; i--)
            {
               TableColumn column = columnModel.getColumn(selected[i]);
               table.removeColumn(column);

               // zapisuje usunięte kolumny na potrzeby późniejszego odtworzenia

               removedColumns.add(column);
            }
         });
      tableMenu.add(hideColumnsItem);

      JMenuItem showColumnsItem = new JMenuItem("Pokaż kolumny");
      showColumnsItem.addActionListener(event ->
         {
            // odtwarza wszystkie usunięte kolumny
            for (TableColumn tc : removedColumns)
               table.addColumn(tc);
            removedColumns.clear();
         });
      tableMenu.add(showColumnsItem);

      JMenuItem addRowItem = new JMenuItem("Dodaj wiersz");
      addRowItem.addActionListener(event ->
         {
            // dodaje nowy wiersz do tabeli mnożenia w modelu

            Integer[] newCells = new Integer[model.getColumnCount()];
            for (int i = 0; i < newCells.length; i++)
               newCells[i] = (i + 1) * (model.getRowCount() + 1);
            model.addRow(newCells);
         });
      tableMenu.add(addRowItem);

      JMenuItem removeRowsItem = new JMenuItem("Usuń wiersze");
      removeRowsItem.addActionListener(event ->
         {
            int[] selected = table.getSelectedRows();

            for (int i = selected.length - 1; i >= 0; i--)
               model.removeRow(selected[i]);
         });
      tableMenu.add(removeRowsItem);

      JMenuItem clearCellsItem = new JMenuItem("Czyść komórki");
      clearCellsItem.addActionListener(event ->
         {
            for (int i = 0; i < table.getRowCount(); i++)
               for (int j = 0; j < table.getColumnCount(); j++)
                  if (table.isCellSelected(i, j)) table.setValueAt(0, i, j);
         });
      tableMenu.add(clearCellsItem);
   }
}