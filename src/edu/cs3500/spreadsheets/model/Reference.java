package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * Represents a reference to a rectangular region of cells in the spreadsheet.
 */
public class Reference implements Formula {
  private Coord c1;
  private Coord c2;
  private ArrayList<Formula> cellContents;

  @Override
  public Value evaluate() {
    return null;
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
