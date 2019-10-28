package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.model.visitors.FormulaVisitor;

/**
 * Represents a formula, which is one of:
 * - A value
 * - A reference to a region of cells in the spreadsheet
 * - A function applied to one or more formulas as its arguments
 */
public interface Formula {
  /**
   * Evaluates the formula into a single value.
   * @return  a Value
   */
  Value evaluate();

  /**
   * Accepts a FormulaVisitor visitor and returns R.
   * @param visitor
   * @return the desired value R
   */
  <R> R accept(FormulaVisitor<R> visitor);

  /**
   * Returns a string telling the type of formula it is.
   * @return string type
   */
  String type();
}
