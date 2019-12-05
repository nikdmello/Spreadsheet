package edu.cs3500.spreadsheets.provider.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;
import java.util.function.Consumer;

import javax.swing.JPanel;

import edu.cs3500.spreadsheets.model.Coord;

/**
 * Represents a JPanel for individual cells that update themselves when scrolling.
 */
class CellPanel extends JPanel implements MouseListener {

  private final edu.cs3500.spreadsheets.provider.view.GridPanel.ScrollOffsetManager scroll;
  private final int col;
  private final int row;
  private final ROWorksheet worksheet;
  private final boolean isHeader;
  private final Consumer<CellPanel> gridConsumer;
  private final Consumer<String> frameConsumer;

  private boolean selected;

  CellPanel(edu.cs3500.spreadsheets.provider.view.GridPanel.ScrollOffsetManager scroll,
            int col, int row,
            ROWorksheet worksheet, Consumer<CellPanel> gridConsumer,
            Consumer<String> frameConsumer) {
    this.scroll = scroll;
    this.col = col;
    this.row = row;
    this.worksheet = worksheet;
    this.isHeader = this.row == 0 || this.col == 0;
    this.gridConsumer = gridConsumer;
    this.frameConsumer = frameConsumer;
    this.selected = false;
    this.addMouseListener(this);

    if (this.isHeader) {
      this.setBackground(Color.getHSBColor(0, 0, 0.9f));
    } else {
      this.setBackground(Color.WHITE);
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    final Graphics2D g2d = (Graphics2D) g;

    if (!this.isHeader) {
      if (this.selected) {
        this.setBackground(new Color(0xe8eefd));
      } else {
        this.setBackground(Color.WHITE);
      }
    }

    g2d.drawLine(0, this.getHeight(), this.getWidth(), this.getHeight());
    g2d.drawLine(this.getWidth(), 0, this.getWidth(), this.getHeight());

    updateText(g2d);
  }

  private void updateText(Graphics2D g2d) {
    g2d.setColor(Color.BLACK);

    final String contents;
    if (this.row == 0 && this.col == 0) {
      contents = "";
    } else if (this.row == 0) {
      contents = Coord.colIndexToName(this.getModelCol());
    } else if (this.col == 0) {
      contents = String.valueOf(this.getModelRow());
    } else {
      contents = SingleStringView.valueToString(this.worksheet.evaluate(this.getModelCol(),
              this.getModelRow()));
    }

    final Font font = g2d.getFont();
    final LineMetrics metrics = font.getLineMetrics(
            contents, g2d.getFontRenderContext());
    final Rectangle2D bounds = font.getStringBounds(
            contents, g2d.getFontRenderContext());
    final int centerX = (this.getWidth() - (int) bounds.getWidth()) / 2;
    final int centerY = (this.getHeight() - (int) bounds.getHeight()) / 2
            + (int) metrics.getAscent();

    if (this.isHeader) {
      g2d.drawString(contents, centerX, centerY);
    } else {
      g2d.drawString(contents, 3, centerY);
      this.setToolTipText(contents);
    }
  }

  int getModelCol() {
    return this.col + this.scroll.getColOffset();
  }

  int getModelRow() {
    return this.row + this.scroll.getRowOffset();
  }

  void setSelected(boolean shouldSelect) {
    this.selected = shouldSelect;
    this.repaint();
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    // only use mouseReleased
  }

  @Override
  public void mousePressed(MouseEvent e) {
    // only use mouseReleased
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (!this.isHeader) {
      this.gridConsumer.accept(this);
      this.frameConsumer.accept(this.worksheet.get(this.getModelCol(), this.getModelRow()));
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // only use mouseReleased
  }

  @Override
  public void mouseExited(MouseEvent e) {
    // only use mouseReleased
  }
}
