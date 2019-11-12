package edu.cs3500.spreadsheets.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.JTableHeader;

class RowHeaderRenderer extends JLabel implements ListCellRenderer {

  RowHeaderRenderer(JTable table) {
    JTableHeader header = table.getTableHeader();
    setOpaque(true);
    setBorder(UIManager.getBorder("TableHeader.cellBorder"));
    setHorizontalAlignment(CENTER);
    setForeground(header.getForeground());
    setBackground(header.getBackground());
    setFont(header.getFont());
  }

  @Override
  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    setText((value == null) ? "" : value.toString());
    return this;
  }
}
