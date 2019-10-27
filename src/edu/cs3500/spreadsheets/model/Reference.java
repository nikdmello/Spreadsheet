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
  private Worksheet sheet;

  public Reference(Coord c1, Coord c2){
    this.c1 = c1;
    this.c2 = c2;
  }

  public Reference(Coord c){
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


//  public ArrayList<Formula> getCellContents() {
//    int rowMin = Integer.min(c1.row, c2.row);
//    int rowMax = Integer.max(c1.row, c2.row);
//
//    int colMin = Integer.min(c1.col, c2.col);
//    int colMax = Integer.max(c1.col, c2.col);
//
//
//    for (int i = rowMin; i < rowMax; i++) {
//      for (int j = colMin; j < colMax; j++) {
//
//      }
//    }
//  }
}
