package edu.cs3500.spreadsheets.model;

/**
 * Provides copy constructors for all formulas.
 */
public class FormulaCopyConstructor implements FormulaVisitor<Formula> {
  @Override
  public Formula visitBoolean(boolean b) {
    return new BoolValue(b);
  }

  @Override
  public Formula visitDouble(double d) {
    return new DoubleValue(d);
  }

  @Override
  public Formula visitString(String s) {
    return new StringValue(s);
  }

  @Override
  public Formula visitReference(Reference r) {
    return new Reference(r);
  }

  @Override
  public Formula visitFunc(Function f) {
    return new Function(f);
  }
}
