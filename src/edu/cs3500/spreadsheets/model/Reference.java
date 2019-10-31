package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a reference to a rectangular region of cells in the spreadsheet.
 */
public class Reference implements Formula {
  private Coord c1;
  private Coord c2;
  private Coord thisCoord;
  private ArrayList<Formula> cellContents;
  public final Worksheet sheet;

  /**
   * Represents a constructor for a Reference with two coordinates, the starting point of the range
   * of cells and the ending point of the range of cells.
   *
   * @param sheet a reference sheet
   * @param c1    Coord 1 to start at
   * @param c2    Coord 2 to end at
   */
  public Reference(Worksheet sheet, Coord c1, Coord c2, Coord thisCoord) {
    this.sheet = sheet;
    this.c1 = c1;
    this.c2 = c2;
    this.thisCoord = thisCoord;
  }

  /**
   * Represents a constructor with only one coordinate, meaning that this reference only refers to
   * one cell instead of a range of cells.
   *
   * @param sheet Reference sheet
   * @param c     single Coord
   */
  public Reference(Worksheet sheet, Coord c, Coord thisCoord) {
    this.sheet = sheet;
    this.c1 = c;
    this.c2 = c;
    this.thisCoord = thisCoord;
  }

  /**
   * Copy constructor.
   *
   * @param r Reference to copy
   */
  public Reference(Reference r) {
    this.sheet = r.sheet;
    this.c1 = r.c1;
    this.c2 = r.c2;
    this.thisCoord = r.thisCoord;
  }

  @Override
  public Value evaluate() {
    if (this.hasRepeat()) {
      throw new IllegalArgumentException("Cell cannot reference itself");
    }

    if (sheet.getCellAt(c1) != null && c1.equals(c2)) {
      return sheet.getCellAt(c1).getFormula().evaluate();
    }
    throw new IllegalArgumentException("Nothing to evaluate");
  }

  /**
   * Checks to see if a reference references itself.
   *
   * @return true if the reference references itself
   */
  private boolean hasRepeat() {
    return this.accept(new SelfRefVisitor(thisCoord));
  }

  @Override
  public <R> R accept(FormulaVisitor<R> visitor) {
    return visitor.visitReference(this);
  }

  @Override
  public String type() {
    return "ref";
  }

  ArrayList<Formula> getCellFormulas() {
    if (this.c1.equals(c2)) {
      return new ArrayList<Formula>(Arrays.asList(sheet.getCellAt(c1).getFormula()));
    }
    ArrayList<Formula> formulaList = new ArrayList<>();
    for (int i = c1.col; i < c2.col + 1; i++) {
      for (int j = c1.row; j < c2.row + 1; j++) {
        Cell c = sheet.getCellAt(new Coord(i, j));
        if (c != null) {
          Formula formula = c.getFormula();
          formulaList.add(formula);
        }
      }
    }
    return formulaList;
  }

  /**
   * Returns all the coordinates of cells.
   *
   * @return a collection of coordinates.
   */
  ArrayList<Coord> getCellCoords() {
    if (this.c1.equals(c2)) {
      return new ArrayList<Coord>(Arrays.asList(c1));
    }
    ArrayList<Coord> coordList = new ArrayList<Coord>();
    for (int i = c1.col; i < c2.col; i++) {
      for (int j = c1.row; j < c2.row; j++) {
        Coord c = new Coord(i, j);
        coordList.add(c);
      }
    }
    return coordList;
  }


}
