package edu.cs3500.spreadsheets.model;

/**
 * Represents a container for a symbol value.
 * TODO: Explain what symbol is
 */
public class SymbolValue implements Value {
  @Override
  public Value evaluate() {
    return null;
  }

  @Override
  public <R> R accept(FormulaVisitor<R> visitor) {
    return null;
  }

  @Override
  public String type() {
    return null;
  }

  @Override
  public boolean isNumeric() {
    return false;
  }

  @Override
  public double numberForm() {
    return 0;
  }

}
