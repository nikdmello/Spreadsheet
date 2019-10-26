package edu.cs3500.spreadsheets.model;

/**
 * Represents a formula, which is one of:
 * - A value
 * - A reference to a region of cells in the spreadsheet
 * - A function applied to one or more formulas as its arguments
 */
public interface Formula {

  /**
   *
   * @return  a Value
   */
  public Value evaluate();
}
