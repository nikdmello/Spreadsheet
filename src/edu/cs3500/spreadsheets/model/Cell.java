package edu.cs3500.spreadsheets.model;

/**
 * Represents a cell in a spreadsheet containing a formula.
 */
final public class Cell {
  private Formula f;

  /**
   * Constructs a cell containing a formula.
   *
   * @param f the formula for the cell
   */
  public Cell(Formula f) {
    this.f = f;
  }

  /**
   * Evaluates the contents in a cell to a single Value.
   *
   * @return the Value evaluated to
   */
  public void evaluateCell() {
    if(f == null){
      return;
    }
    this.f = f.evaluate();
  }

  public Formula getFormula() {
    return this.f;
  }
}
