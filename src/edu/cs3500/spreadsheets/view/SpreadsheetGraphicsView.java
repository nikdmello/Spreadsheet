package edu.cs3500.spreadsheets.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SpreadsheetGraphicsView extends JFrame implements SpreadsheetView {

  SpreadsheetGraphicsView() {
    super();
    this.setTitle("Microsoft Excel v2.0");
    this.setSize(800, 800);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    SpreadsheetTable spreadsheetTable = new SpreadsheetTable();

    ListModel listModel = new AbstractListModel() {
      String[] headers = {"a", "b", "c", "d", "e", "f", "g", "h", "i"};
      public int getSize() { return headers.length; }
      public Object getElementAt(int index) {
        return headers[index];
      }
    };

    DefaultTableModel defaultTableModel = new DefaultTableModel(listModel.getSize(), 10);
    JTable table = new JTable(defaultTableModel);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    JList rowHeader = new JList(listModel);
    rowHeader.setFixedCellWidth(50);
    rowHeader.setFixedCellHeight(table.getRowHeight());
    rowHeader.setCellRenderer(new RowHeaderRenderer(spreadsheetTable));

    JScrollPane scrollPane = new JScrollPane(spreadsheetTable);
    scrollPane.setRowHeaderView(rowHeader);
    getContentPane().add(scrollPane, BorderLayout.CENTER);

  }

  @Override
  public void render() {
  }

}
