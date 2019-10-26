package edu.cs3500.spreadsheets.model;

import java.util.List;

import edu.cs3500.spreadsheets.sexp.Sexp;
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
    return null;
    // TODO
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

