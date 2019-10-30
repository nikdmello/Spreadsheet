package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.cs3500.spreadsheets.model.BoolValue;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.StringValue;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;

/**
 * Represents a visitor function object for processing a Sexp to Formula.
 */
public class SexpToFormula implements SexpVisitor<Formula> {
  Worksheet currentSheet;

  /**
   * Constructs a SexpToFormula visitor with a rference sheet to use.
   * @param sheet the reference sheet
   */
  public SexpToFormula(Worksheet sheet){
    this.currentSheet = sheet;
  }

  @Override
  public Formula visitBoolean(boolean b) {
    return new BoolValue(b);
  }

  @Override
  public Formula visitNumber(double d) {
    return new DoubleValue(d);
  }

  @Override
  public Formula visitSymbol(String s) {
    int coordCol = Coord.colNameToIndex(s.substring(0,1));
    int coordRow = Integer.parseInt(s.substring(1,2));
    Coord firstCoord = new Coord(coordCol, coordRow);

    if (s.length() > 2) {
      int coordCol2 = Coord.colNameToIndex(s.substring(3,4));
      int coordRow2 = Integer.parseInt(s.substring(4,5));
      Coord secondCoord = new Coord(coordCol2, coordRow2);

      return new Reference(this.currentSheet, firstCoord, secondCoord);
    }
    else {
      return new Reference(this.currentSheet, firstCoord);
    }
  }

  @Override
  public Formula visitString(String s) {
    return new StringValue(s);
  }

  @Override
  public Formula visitSList(List<Sexp> l) {
    if(l.size() > 1) {
      ArrayList<Formula> arr = new ArrayList<Formula>();
      for (int i = 1; i < l.size(); i++) {
        arr.add(l.get(i).accept(this));
      }
      switch (l.get(0).toString()){
        case "SUM": return new Function(FunctionType.SUM, arr);
        case "PRODUCT": return new Function(FunctionType.PRODUCT, arr);
        case "<": return new Function(FunctionType.LT, arr);
        case "CONCAT": return new Function(FunctionType.CAT, arr);
        default: throw new IllegalArgumentException("Invalid Input");
      }
    }

    return l.get(0).accept(this);
  }
}

