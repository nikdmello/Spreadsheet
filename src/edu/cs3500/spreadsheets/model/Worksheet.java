package edu.cs3500.spreadsheets.model;

/**
 * Interface for any spreadsheet model.
 */
public interface Worksheet {
  void initGrid();
  void createCell(int col, int row, Formula f);
  Cell getCellAt(Coord c);
}
