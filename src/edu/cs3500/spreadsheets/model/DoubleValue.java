package edu.cs3500.spreadsheets.model;

import java.util.Objects;

/**
 * Container for an double.
 */
public class DoubleValue implements Value {
  private final double value;

  /**
   * Constructs a new double container.
   *
   * @param value int to contain
   */
  public DoubleValue(double value) {
    this.value = value;
  }

  @Override
  public Value evaluate() {
    return this;
  }

  @Override
  public String type() {
    return "double";
  }

  @Override
  public <R> R accept(FormulaVisitor<R> visitor) {
    return visitor.visitDouble(this.value);
  }

  @Override
  public boolean isNumeric() {
    return true;
  }

  @Override
  public double numberForm() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }

    if (!(that instanceof DoubleValue)) {
      return false;
    }

    return ((DoubleValue) that).value == (this.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}

