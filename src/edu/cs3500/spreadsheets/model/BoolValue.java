package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.model.visitors.FormulaVisitor;

/**
 * Container for a boolean.
 */
public final class BoolValue implements Value {
  private Boolean b;

  public BoolValue(Boolean b) {
    this.b = b;
  }

  @Override
  public Value evaluate() {
    return this;
  }

  @Override
  public String type() {
    return "bool";
  }

  @Override
  public <R> R accept(FormulaVisitor<R> visitor) {
    return visitor.visitBoolean(this.b);
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
