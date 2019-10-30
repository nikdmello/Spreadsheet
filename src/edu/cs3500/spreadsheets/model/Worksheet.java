package edu.cs3500.spreadsheets.model;

/**
 * Interface for any spreadsheet model.
 */
public interface Worksheet {
  /**
   * Creates a new key-value pair and puts it in the hashtable.
   *
   * @param col the x-value of the coord
   * @param row the y-value of the coord
   * @param f   the formula contained in the Cell
   */
  void createCell(int col, int row, Formula f);

  /**
   * Gets the Cell at the specified coord.
   *
   * @param c the coord of the desired cell
   * @return the desired cell
   */
  Cell getCellAt(Coord c);

  /**
   * Deletes the Cell at the specified coord.
   *
   * @param c the coord of the desired cell
   */
  void deleteCellAt(Coord c);

  /**
   * Evaluates all cells so that the value within may be retrieved.
   */
  void evalAll();
}
