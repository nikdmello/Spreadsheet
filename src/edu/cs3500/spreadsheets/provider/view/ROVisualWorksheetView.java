package edu.cs3500.spreadsheets.provider.view;

import java.awt.*;

import javax.swing.*;

/**
 * Represents a read-only GUI view of a worksheet.
 */
public class ROVisualWorksheetView extends JFrame implements WorksheetView {
  private static final int WIDTH = 1024;
  private static final int HEIGHT = 512;

  /**
   * Constructs a new ROVisualWorksheetView.
   *
   * @param worksheet the worksheet to represent
   */
  public ROVisualWorksheetView(ROWorksheet worksheet) {
    super();
    this.setSize(WIDTH, HEIGHT);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(new JLabel("Arrow keys: scroll / Arrow keys + Shift: fast scroll"),
            BorderLayout.SOUTH);

    GridPanel grid = new GridPanel(worksheet, (String s) -> {
      //
    }, (CellPanel c) -> {
      //
    });
    grid.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    this.add(grid);
    this.pack();
  }

  @Override
  public void setEditListener(EditListener listener) {
    // does nothing, as this view cannot be edited
  }

  @Override
  public void render() {
    this.setVisible(true);
    this.repaint();
  }
}
