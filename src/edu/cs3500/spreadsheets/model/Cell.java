package edu.cs3500.spreadsheets.model;

import java.util.Objects;

/**
 * Represents a cell in a spreadsheet containing a formula.
 * The contents of the cell could change.
 */
public final class Cell {
  private Formula f;

  /**
   * Constructs a cell containing a formula.
   *
   * @param f the formula for the cell
   */
  Cell(Formula f) {
    if (f == null) {
      throw new IllegalArgumentException("Cell can not be created with a null formula");
    }
    this.f = f;
  }

  /**
   * Copy constructor for a cell.
   * @param c the cell to copy
   */
  public Cell(Cell c) {
    this.f = c.f;
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
   * Getter method ONLY used for testing purposes. This would be package-private to avoid mutation,
   * but it is public for testing purposes.
   *
   * @return the formula
   */
  public Formula getFormula() {
    return this.f;
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
