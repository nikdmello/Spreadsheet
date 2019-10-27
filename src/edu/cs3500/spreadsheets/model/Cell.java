package edu.cs3500.spreadsheets.model;

/**
 * Represents a cell in a spreadsheet containing a formula.
 */
final public class Cell {
  final private Formula f;

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
