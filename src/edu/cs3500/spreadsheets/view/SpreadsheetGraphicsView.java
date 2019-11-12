package edu.cs3500.spreadsheets.view;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SpreadsheetGraphicsView extends JFrame implements SpreadsheetView {

  ModelToTable mtt;

  public SpreadsheetGraphicsView(ModelToTable mtt) {
    super();
    this.mtt = mtt;
    this.setTitle("Microsoft Excel v2.0");
    this.setSize(500, 300);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    RowListModel listModel = new RowListModel(this.mtt);

    DefaultTableModel defaultTableModel = new DefaultTableModel(listModel.getSize(), mtt.colNames().length);
    for(int i = 0; i < mtt.numRows(); i++){
      for(int j = 0; j < mtt.numCols(); j++){
        defaultTableModel.setValueAt(this.mtt.translate()[j][i], i, j);
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
    getContentPane().add(scrollPane, BorderLayout.CENTER);

  }

  @Override
  public void render() {
    SpreadsheetGraphicsView view = new SpreadsheetGraphicsView(mtt);
    view.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    view.setVisible(true);
  }
}

