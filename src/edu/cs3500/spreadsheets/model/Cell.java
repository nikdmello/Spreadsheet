package edu.cs3500.spreadsheets.model;

/**
 * Represents a cell in a spreadsheet containing a formula.
 */
final public class Cell {
   private Formula form;

  /**
   * Constructs a cell containing a formula.
   * @param f the formula for the cell
   */
  public Cell(Formula f) {
    this.form = f;
  }

  /**
   * Evaluates the contents in a cell to a single Value.
   * @return the Value evaluated to
   */
  public Value evaluateCell() {
    return form.evaluate();
  }

  public Formula getFormula() {
    return this.form;
  }
}

