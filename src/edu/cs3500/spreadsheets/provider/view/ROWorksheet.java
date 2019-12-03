package edu.cs3500.spreadsheets.provider.view;

import java.util.Map;

import edu.cs3500.spreadsheets.model.Coord;

/**
 * Represents a worksheet of cells that have String contents that parse to formulas. Read-only
 * methods.
 */
public interface ROWorksheet {
  /**
   * Gets the raw cell content at the given coordinate. Defaults to the empty string when the cell
   * is blank.
   *
   * @param col the column of the cell to evaluate
   * @param row the row of the cell to evaluate
   * @return the raw, unaltered, unparsed cell content
   */
  String get(int col, int row);

  /**
   * Gets the raw cell content of all non-blank cells.
   *
   * @return all non-blank cells
   */
  Map<Coord, String> getAll();

  /**
   * Evaluates a coordinate, returning a WValue. If evaluation could not reduce to a blank, number,
   * boolean, or String, an exception is thrown.
   *
   * @param col the column of the cell to evaluate
   * @param row the row of the cell to evaluate
   * @return the resultant WValue
   * @throws IllegalArgumentException when the coordinate could not be evaluated
   */
  WValue evaluate(int col, int row);
}
