package edu.cs3500.spreadsheets.model;

/**
 * A visitor function object that processes a Formula to a String.
 */
public class AsStringVisitor implements FormulaVisitor<String> {
  @Override
  public String visitBoolean(boolean b) {
    return "" + b;
  }

  @Override
  public String visitDouble(double d) {
    return "" + d;
  }

  @Override
  public String visitString(String s) {
    return s;
  }

  @Override
  public String visitReference(Reference r) {
    return r.evaluate().accept(new AsStringVisitor());
  }

  @Override
  public String visitFunc(Function f) {
    return f.evaluate().accept(new AsStringVisitor());
  }
}
