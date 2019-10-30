package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * Represents a reference to a rectangular region of cells in the spreadsheet.
 */
public class Reference implements Formula {
  private Coord c1;
  private Coord c2;
  private ArrayList<Formula> cellContents;
  public final Worksheet sheet = new BasicWorksheetModel();

  /**
   * Represents a constructor for a Reference with two coordinates, the starting point of the range
   * of cells and the ending point of the range of cells.
   */
  public Reference(Coord c1, Coord c2) {
    this.c1 = c1;
    this.c2 = c2;
    //TODO
  }

  /**
   * Represents a constructor with only one coordinate, meaning that this reference only refers to
   * one cell instead of a range of cells.
   */
  public Reference(Coord c) {
    this.c1 = c;
    this.c2 = c;
  }

  @Override
  public Value evaluate() {
    if (this.hasRepeat()) {
      throw new IllegalArgumentException("Cell cannot reference itself");
    }

    if (c1.equals(c2)) {
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
    return this.accept(new SelfRefVisitor(this));
  }

  @Override
  public <R> R accept(FormulaVisitor<R> visitor) {
    return visitor.visitReference(this);
  }

  @Override
  public String type() {
    return "ref";
  }

  private ArrayList<Formula> getCellFormulas() {
    ArrayList<Formula> formulaList = new ArrayList<>();
    for (int i = c1.col; i < c2.col; i++) {
      for (int j = c1.row; j < c2.row; j++) {
        Formula formula = sheet.getCellAt(new Coord(i, j)).getFormula();
        formulaList.add(formula);
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
