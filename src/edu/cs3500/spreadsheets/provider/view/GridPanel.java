package edu.cs3500.spreadsheets.provider.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;

import javax.swing.*;

class GridPanel extends JPanel {

  private static final int COLS = 12;
  private static final int ROWS = 21;

  private final ScrollOffsetManager scroll;
  private final Consumer<String> editBarConsumer;
  private final Consumer<CellPanel> cellSelectedConsumer;

  GridPanel(ROWorksheet worksheet, Consumer<String> editBarConsumer,
            Consumer<CellPanel> cellSelectedConsumer) {

    this.scroll = new ScrollOffsetManager();
    this.editBarConsumer = editBarConsumer;
    this.cellSelectedConsumer = cellSelectedConsumer;

    this.setLayout(new GridLayout(ROWS, COLS));
    this.setFocusable(true);

    final InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "scroll-left");
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "scroll-right");
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "scroll-up");
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "scroll-down");
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,
            InputEvent.SHIFT_DOWN_MASK), "scroll-left-skip");
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,
            InputEvent.SHIFT_DOWN_MASK), "scroll-right-skip");
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,
            InputEvent.SHIFT_DOWN_MASK), "scroll-up-skip");
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,
            InputEvent.SHIFT_DOWN_MASK), "scroll-down-skip");
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "deselect");

    getActionMap().put("scroll-left", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        scroll(-1, 0);
      }
    });
    getActionMap().put("scroll-right", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        scroll(1, 0);
      }
    });
    getActionMap().put("scroll-up", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        scroll(0, -1);
      }
    });
    getActionMap().put("scroll-down", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        scroll(0, 1);
      }
    });
    getActionMap().put("scroll-left-skip", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        scroll(-10, 0);
      }
    });
    getActionMap().put("scroll-right-skip", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        scroll(10, 0);
      }
    });
    getActionMap().put("scroll-up-skip", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        scroll(0, -10);
      }
    });
    getActionMap().put("scroll-down-skip", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        scroll(0, 10);
      }
    });
    getActionMap().put("deselect", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        cellSelectedConsumer.accept(null);
      }
    });

    for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLS; j++) {
        final CellPanel c = new CellPanel(this.scroll, j, i, worksheet,
                cellSelectedConsumer, editBarConsumer);
        this.add(c);
      }
    }
  }

  /*
  Scrolls the view by changing the offsets of the scroll manager.
   */
  private void scroll(int offsetX, int offsetY) {
    this.cellSelectedConsumer.accept(null);
    this.scroll.colOffset = Math.max(0, this.scroll.colOffset + offsetX);
    this.scroll.rowOffset = Math.max(0, this.scroll.rowOffset + offsetY);
    repaint();
  }

  /*
  A "scroll manager" which holds the current row offset and column offset.
  Cells use the manager to
  determine which spreadsheet cell they correspond to at any given time.
   */
  static class ScrollOffsetManager {
    private int rowOffset;
    private int colOffset;

    ScrollOffsetManager() {
      this.rowOffset = 0;
      this.colOffset = 0;
    }

    int getRowOffset() {
      return this.rowOffset;
    }

    int getColOffset() {
      return this.colOffset;
    }
  }
}
