package edu.cs3500.spreadsheets.model;

/**
 * A visitor function object that processes a Formula to a boolean.
 */
public class AsBooleanVisitor implements FormulaVisitor<Boolean> {
  @Override
  public Boolean visitBoolean(boolean b) {
    return b;
  }

  @Override
  public Boolean visitDouble(double d) {
    return null;
  }

  @Override
  public Boolean visitString(String s) {
    return null;
  }

  @Override
  public Boolean visitReference(Reference r) {
    return null;
  }

  @Override
  public Boolean visitFunc(Function f) {
    return null;
  }
}
