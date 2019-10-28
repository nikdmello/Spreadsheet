package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

import edu.cs3500.spreadsheets.model.visitors.FormulaVisitor;

/**
 * Represents a reference to a rectangular region of cells in the spreadsheet.
 */
public class Reference implements Formula {
  private Coord c1;
  private Coord c2;
  private ArrayList<Formula> cellContents;
  public final Worksheet sheet;

  public Reference(Coord c1, Coord c2, Worksheet sheet){
    this.c1 = c1;
    this.c2 = c2;
    this.sheet = sheet;
  }

  public Reference(Coord c, Worksheet sheet){
    this.c1 = c;
    this.c2 = c;
    this.sheet = sheet;
  }

  @Override
  public Value evaluate() {
    if(this.hasRepeat()){
      throw new IllegalArgumentException("Cell cannot reference itself");
    }

    if(c1.equals(c2)){
      return sheet.getCellAt(c1).evaluateCell();
    }
    throw new IllegalArgumentException("Nothing to evaluate");
  }

  /**
   * Checks to see if a reference references itself.
   * @return true if the reference references itself
   */
  private boolean hasRepeat() {

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

  public ArrayList<Coord> getCellCoords() {
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
