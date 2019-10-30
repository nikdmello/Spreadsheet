package edu.cs3500.spreadsheets.model;

import java.util.Objects;

/**
 * Container for a boolean.
 */
public class BoolValue implements Value {
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

  @Override
  public String toString() {
    return String.valueOf(b);
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }

    if (!(that instanceof BoolValue)) {
      return false;
    }

    return ((BoolValue) that).b == (this.b);
  }

  @Override
  public int hashCode() {
    return Objects.hash(b);
  }
}
