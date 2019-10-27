package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.model.visitors.FormulaVisitor;

public class StringValue implements Value {
  private final String s;

  public StringValue(String s){
    this.s = s;
  }

  @Override
  public Value evaluate() {
    return this;
  }

  @Override
  public <R> R accept(FormulaVisitor<R> visitor) {
    return visitor.visitString(this.s);
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
