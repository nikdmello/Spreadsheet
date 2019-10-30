package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Represents a basic spreadsheet model.
 */
public class BasicWorksheetModel implements Worksheet{
  private Hashtable<Coord, Cell> hashtable;
  private ArrayList<ArrayList<Cell>> cells;

  public BasicWorksheetModel() {
    this.hashtable = new Hashtable<>();
  }

  public Hashtable<Coord, Cell> getHashtable() {
    return hashtable;
  }

  @Override
  public void createCell(int col, int row, Formula f) {
    if(f == null){
      return;
    }
    Coord c = new Coord(col, row);
    Cell cell = new Cell(f);
    hashtable.put(c, cell);
  }

  @Override
  public Cell getCellAt(Coord c) {
    return hashtable.get(c);
  }

  @Override
  public void deleteCellAt(Coord c) {
    hashtable.remove(c);
  }


}
