package edu.cs3500.spreadsheets.model;

/**
 * Represents a cell in a spreadsheet containing a formula.
 */
public class Cell {
  Formula f;

  /**
   * Constructs a cell containing a formula.
   * @param f the formula for the cell
   */
  public Cell(Formula f) {
    this.f = f;
  }

  public Value evaluateCell() {
    return f.evaluate();
  }
}
