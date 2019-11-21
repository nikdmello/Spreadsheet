package edu.cs3500.spreadsheets.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import edu.cs3500.spreadsheets.model.BasicWorksheetModel;

/**
 * Represents the graphical view of the Spreadsheet. This view uses the Java Swing library,
 * specifically the JTable to display the contents of the Spreadsheet.
 */
public class GUITableGraphics extends JPanel {
  private ModelToTable modelToTable;

  /**
   * Constructs a Spreadsheet graphical view with the wrapper class.
   *
   * @param mtt wrapper class.
   */
  public GUITableGraphics(ModelToTable mtt) {
    super();
    this.modelToTable = mtt;

    RowListModel listModel = new RowListModel(this.modelToTable);

    DefaultTableModel defaultTableModel = new DefaultTableModel(listModel.getSize(),
            mtt.colNames().length) {
      // Ensures that the cells cannot be edited by clients directly from the view.
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };

    // Populates the table with Cells.
    for (int i = 0; i < mtt.numRows(); i++) {
      for (int j = 0; j < mtt.numCols(); j++) {
        defaultTableModel.setValueAt(this.modelToTable.translate()[j][i], i, j);
      }
    }

    JTable table = new JTable(defaultTableModel);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    JList<String> rowHeader = new JList<String>(listModel);
    rowHeader.setFixedCellWidth(50);
    rowHeader.setFixedCellHeight(table.getRowHeight());
    rowHeader.setCellRenderer(new RowHeaderRenderer(table));

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setRowHeaderView(rowHeader);
    scrollPane.setPreferredSize(new Dimension(640, 480));
    this.add(scrollPane, BorderLayout.CENTER);
  }

  /**
   * Constructs a Spreadsheet graphical view with a new Spreadsheet.
   */
  public GUITableGraphics() {
    super();
    this.modelToTable = new ModelToTable(new BasicWorksheetModel());

    RowListModel listModel = new RowListModel(this.modelToTable);
    DefaultTableModel defaultTableModel = new DefaultTableModel(listModel.getSize(),
            0) {

      // Ensures that the cells cannot be edited by clients directly from the view.
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };

    JTable table = new JTable(defaultTableModel);
    table.setPreferredSize(new Dimension(640, 480));
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    JList<String> rowHeader = new JList<String>(listModel);
    rowHeader.setFixedCellWidth(50);
    rowHeader.setFixedCellHeight(table.getRowHeight());
    rowHeader.setCellRenderer(new RowHeaderRenderer(table));

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setRowHeaderView(rowHeader);
    scrollPane.setPreferredSize(new Dimension(640, 480));
    this.add(scrollPane, BorderLayout.CENTER);
  }
}
