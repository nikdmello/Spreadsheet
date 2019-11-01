package edu.cs3500.spreadsheets.model;

/**
 * Represents a formula, which is either a value, a reference to a region of cells in the
 * spreadsheet, or a function applied to one or more formulas as its arguments.
 */
public interface Formula {
  /**
   * Evaluates the formula into a single value.
   *
   * @return a Value
   */
  Value evaluate();

  /**
   * Accepts a FormulaVisitor visitor and returns R.
   *
   * @param visitor the formula visitor
   * @return the desired value R
   */
  <R> R accept(FormulaVisitor<R> visitor);

  /**
   * Returns a string telling the type of formula it is.
   *
   * @return string type
   */
  String type();
}
