package edu.cs3500.spreadsheets.view;

import javax.swing.*;

/**
 * TODO
 */
public class RowListModel extends AbstractListModel {
  private String[] rows;

  /**
   * TODO
   * @param mtt
   */
  RowListModel(ModelToTable mtt){
    rows = mtt.rowNames();
  }

  @Override
  public int getSize() {
    return rows.length;
  }

  @Override
  public Object getElementAt(int index) {
    return rows[index];
  }
}