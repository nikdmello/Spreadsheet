package edu.cs3500.spreadsheets.model.visitors;

import edu.cs3500.spreadsheets.model.Function;
import edu.cs3500.spreadsheets.model.Reference;

public class AsStringVisitor implements FormulaVisitor<String>{
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
