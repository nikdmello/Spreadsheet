package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Represents a basic spreadsheet model.
 */
public class BasicWorksheetModel implements Worksheet {
  private Hashtable<Coord, Cell> hashtable;
  private ArrayList<ArrayList<Cell>> cells;

  @Override
  public void initGrid() {
    hashtable = new Hashtable<>();
  }

  @Override
  public Cell getCellAt(Coord c) {
    return hashtable.get(c);
  }

}
