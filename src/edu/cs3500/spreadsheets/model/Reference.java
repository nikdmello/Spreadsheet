package edu.cs3500.spreadsheets.model;

import java.sql.Ref;
import java.util.ArrayList;

import edu.cs3500.spreadsheets.model.visitors.FormulaVisitor;

/**
 * Represents a reference to a rectangular region of cells in the spreadsheet.
 */
public class Reference implements Formula {
  private Coord c1;
  private Coord c2;
  private ArrayList<Formula> cellContents;
  private Worksheet sheet;

  public Reference(Coord c1, Coord c2) {
    this.c1 = c1;
    this.c2 = c2;
  }

  public Reference(Coord c) {
    this.c1 = c;
    this.c2 = c;
  }

  @Override
  public Value evaluate() {
    if(c1.equals(c2)){
      return sheet.getCellAt(c1).evaluateCell();
    }
    throw new IllegalArgumentException("Nothing to evaluate");
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
}
