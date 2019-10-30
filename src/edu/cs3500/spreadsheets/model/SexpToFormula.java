package edu.cs3500.spreadsheets.model;

import java.util.List;

import edu.cs3500.spreadsheets.model.BoolValue;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.StringValue;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;

/**
 * Represents a visitor function object for processing a Sexp to Formula.
 */
public class SexpToFormula implements SexpVisitor<Formula> {

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

      return new Reference(firstCoord, secondCoord);
    }
    else {
      return new Reference(firstCoord);
    }
  }

  @Override
  public Formula visitString(String s) {
    return new StringValue(s);
  }

  @Override
  public Formula visitSList(List l) {
    // TODO
    return null;
  }
}

