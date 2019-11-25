package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

/**
 * Represents a basic spreadsheet model. The model includes a grid of cells, each of which may have
 * a formula.
 */
public class BasicWorksheetModel implements Worksheet {
  /**
   * Represents the grid of cells with a Coord associated with a Cell.
   */
  private Hashtable<Coord, Cell> hashtable;
  /**
   * A list of Coords that is used for evaluation purposes.
   */
  private ArrayList<Coord> valueTable;
  /**
   * A list of Coords that is ordered (i.e. starting at A1), previously used when sorted Coords were
   * required.
   */
  private ArrayList<Coord> orderedCoords;

  /**
   * The number of rows and cols to be displayed.
   */
  private int cols, rows;

  /**
   * Constructor that initializes the hashtable, which will serve as the grid of cells. Within this
   * hashtable will be the Coord and Cell key-value pairs.
   */
  public BasicWorksheetModel() {
    this.hashtable = new Hashtable<>();
    this.valueTable = new ArrayList<>();
    this.orderedCoords = new ArrayList<>();
  }

  /**
   * Getter method ONLY used for testing purposes.
   *
   * @return hashtable of coord and cell
   */
  public Hashtable<Coord, Cell> getHashtable() {
    Hashtable<Coord, Cell> ret = new Hashtable<Coord, Cell>();
    for (Map.Entry<Coord, Cell> e : hashtable.entrySet()) {
      ret.put(e.getKey(), new Cell(e.getValue()));
    }
    return ret;
  }

  @Override
  public int getNumRows() {
    int furthest = 1;
    for (Map.Entry<Coord, Cell> e : hashtable.entrySet()) {
      if (e.getKey().row > furthest) {
        furthest = e.getKey().row;
      }
    }
    if(furthest < 26){
      return rows;
    }
    return furthest;
  }

  @Override
  public int getNumCols() {
    int furthest = 1;
    for (Map.Entry<Coord, Cell> e : hashtable.entrySet()) {
      if (e.getKey().col > furthest) {
        furthest = e.getKey().col;
      }
    }
    if(furthest < 26){
      return cols;
    }
    return furthest;
  }

  @Override
  public Coord reEval(Coord c) {
      for (Coord co : orderedCoords){
        try {
          if (hashtable.get(co).unevaluatedFormula().hasRef(c)) {
            hashtable.get(co).revertFormula();
            hashtable.get(co).evaluateCell();
          }
        } catch(IllegalArgumentException iae){
          hashtable.get(co).setError();
          return co;
        }
      }
      return null;
  }

  @Override
  public void setErrorAt(Coord c) {
    hashtable.get(c).setError();
  }

  @Override
  public void createCell(int col, int row, Formula f) {
    Coord c = new Coord(col, row);
    Cell cell;
    try {
      cell = new Cell(f.accept(new FormulaCopyConstructor()).evaluate(), f);
    } catch (IllegalArgumentException e) {
      cell = new Cell(f);
    }
    hashtable.put(c, cell);
    orderedCoords.add(c);
  }

  @Override
  public Cell getCellAt(Coord c) {
    return hashtable.get(c);
  }

  @Override
  public void deleteCellAt(Coord c) {
    hashtable.remove(c);
  }

  @Override
  public Coord evalAll() {
    for (Coord c : orderedCoords) {
      try {
        if (!valueTable.contains(c)) {
          hashtable.get(c).evaluateCell();
          valueTable.add(c);
        }
      } catch (IllegalArgumentException iae) {
        hashtable.get(c).setError();
        return c;
      }
    }
    return null;
  }

  @Override
  public void changeContents(Coord c, Formula f) {
    Cell cell = new Cell(f.evaluate(), f);
    hashtable.put(c, cell);
  }


}
