package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * A visitor function object that processes a Formula to a Reference.
 */
public class RefVisitor implements FormulaVisitor<ArrayList<Formula>> {

  @Override
  public ArrayList<Formula> visitBoolean(boolean b) {
    return null;
  }

  @Override
  public ArrayList<Formula> visitDouble(double d) {
    return null;
  }

  @Override
  public ArrayList<Formula> visitString(String s) {
    return null;
  }

  @Override
  public ArrayList<Formula> visitReference(Reference r) {
    return r.getCellFormulas();
  }

  @Override
  public ArrayList<Formula> visitFunc(Function f) {
    return null;
  }
}
