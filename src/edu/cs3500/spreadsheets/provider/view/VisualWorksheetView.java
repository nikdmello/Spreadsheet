package edu.cs3500.spreadsheets.provider.view;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.*;

/**
 * Represents a GUI view of the worksheet, showing a grid and allowing editing of cells.
 */
public class VisualWorksheetView extends JFrame implements WorksheetView {

  private static final int WIDTH = 1024;
  private static final int HEIGHT = 512;

  private final JTextField textField;
  private CellPanel selected;

  /**
   * Constructs a new VisualWorksheetView that shows a GUI for the given loaded worksheet.
   *
   * @param worksheet the worksheet to render and send events for
   */
  public VisualWorksheetView(ROWorksheet worksheet) {
    super();
    Validation.requireNonNull(worksheet, "Read-only worksheet");
    this.selected = null;

    this.setTitle("Spreadsheet editor");
    this.setSize(WIDTH, HEIGHT);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    final JPanel topBar = new JPanel(new BorderLayout());
    final JPanel formulaBar = new JPanel();
    this.textField = new JTextField(45);
    formulaBar.add(this.textField);
    formulaBar.add(new JLabel("Enter: submit formula (clear if blank) / Esc: cancel selection"));
    topBar.add(formulaBar, BorderLayout.NORTH);
    final JLabel formulaHelp = new JLabel(" # / true|false / \"text\" / "
            + "=(SUM A1...) / =(PRODUCT A1...) / =(< A1 A2) / =(CAPS A1)", JLabel.CENTER);
    formulaHelp.setForeground(Color.GRAY);
    topBar.add(formulaHelp, BorderLayout.SOUTH);
    this.add(topBar, BorderLayout.NORTH);

    this.add(new JLabel("Tab: change focus between formula bar and grid / "
            + "Arrow keys: scroll / Arrow keys + Shift: fast scroll"), BorderLayout.SOUTH);

    GridPanel grid = new GridPanel(worksheet, this.textField::setText,
            this::select);
    grid.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    this.add(grid, BorderLayout.CENTER);

    this.pack();
    grid.requestFocusInWindow();
  }

  /**
   * Selects the given cell, and deselects the previously selected cell. If the provided cell is
   * null, all that happens is the deselection of the previously selected cell.
   *
   * @param cell the cell to select
   */
  private void select(CellPanel cell) {
    if (this.selected != null) {
      this.selected.setSelected(false);
    }

    this.selected = (this.selected == cell) ? null : cell;

    if (this.selected != null) {
      this.selected.setSelected(true);
    } else {
      this.textField.setText("");
    }
  }

  @Override
  public void setEditListener(EditListener editListener) {
    for (KeyListener l : this.textField.getKeyListeners()) {
      this.textField.removeKeyListener(l);
    }
    this.textField.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        // do nothing
      }

      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER && selected != null) {
          try {
            editListener.onEdit(selected.getModelCol(), selected.getModelRow(),
                    textField.getText());
          } catch (IOException ie) {
            throw new IllegalStateException("This view should not throw "
                    + "IOExceptions");
          } catch (IllegalArgumentException ie) {
            JOptionPane.showMessageDialog(null, ie.getMessage());
          }
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
        // do nothing
      }
    });
  }

  @Override
  public void render() {
    this.setVisible(true);
    this.repaint();
  }
}
