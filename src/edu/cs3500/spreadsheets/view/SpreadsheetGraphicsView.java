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

    this.setLayout(new BorderLayout());
    SpreadsheetTable spreadsheetTable = new SpreadsheetTable();
    JScrollPane scrollPane = new JScrollPane(spreadsheetTable);
    this.add(scrollPane, BorderLayout.CENTER);
    this.pack();

    ListModel listModel = new AbstractListModel() {

      String[] headers = {"A"};

      @Override
      public int getSize() {
        return headers.length;
      }

      @Override
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




  }

  @Override
  public void render() {
  }

}
