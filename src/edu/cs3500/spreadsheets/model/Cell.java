package edu.cs3500.spreadsheets.model;

import java.util.Objects;

/**
 * Represents a cell in a spreadsheet containing a formula.
 */
public final class Cell {
  private Formula f;


  /**
   * Constructs a cell containing a formula.
   *
   * @param f the formula for the cell
   */
  public Cell(Formula f) {
    if (f == null) {
      throw new IllegalArgumentException("Cell can not be created with a null formula");
    }
    this.f = f;
  }


  /**
   * Evaluates the contents in a cell to a single Value.
   */
  public void evaluateCell() {
    if (f == null) {
      return;
    }
    this.f = f.evaluate();
  }

  /**
   * Getter method ONLY used for testing purposes.
   * @return the formula
   */
  public Formula getFormula() {
    return this.f;
  }

  public void changeContents(Formula f){
    if (f == null) {
      throw new IllegalArgumentException("Cell can not be created with a null formula");
    }
    this.f = f;
  }
  @Override
  public String toString() {
    return this.f.toString();
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }

    if (!(that instanceof Cell)) {
      return false;
    }

    return ((Cell) that).f == (this.f);
  }

  @Override
  public int hashCode() {
    return Objects.hash(f);
  }
}
