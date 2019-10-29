package edu.cs3500.spreadsheets.model.visitors;

import java.util.ArrayList;

import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.Function;
import edu.cs3500.spreadsheets.model.Reference;

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
    return null;
  }

  @Override
  public ArrayList<Formula> visitFunc(Function f) {
    return null;
  }
}
