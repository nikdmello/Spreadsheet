package edu.cs3500.spreadsheets.model;

/**
 * Container for a boolean.
 */
public class BoolValue implements Value {
  private Boolean b;

  BoolValue(Boolean b){
    this.b = b;
  }

  @Override
  public Value evaluate() {
    return this;
  }

  @Override
  ValueVisitor accept(ValueVisitor<Boolean> value){
    return value.visitBoolean();
  }
}
