package edu.cs3500.spreadsheets.model;

import java.util.Objects;

import edu.cs3500.spreadsheets.provider.view.ValueVisitor;

/**
 * Represents a container for a String, which is a Value.
 */
public class StringValue implements Value {
  private final String s;

  public StringValue(String s) {
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
  public String type() {
    return "string";
  }

  @Override
  public boolean hasRef(Coord c) {
    return false;
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
  public <R> R acceptV(ValueVisitor<R> v) {
    return v.visitString(this);
  }

  @Override
  public String toString() {
    return s;
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }

    if (!(that instanceof StringValue)) {
      return false;
    }

    return ((StringValue) that).s.equals(this.s);
  }

  @Override
  public int hashCode() {
    return Objects.hash(s);
  }
}
