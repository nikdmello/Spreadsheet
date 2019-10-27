package edu.cs3500.spreadsheets.model;

/**
 * Interface for any spreadsheet model.
 */
public interface Worksheet {
  void initGrid();
  Cell getCellAt(Coord c);
}
