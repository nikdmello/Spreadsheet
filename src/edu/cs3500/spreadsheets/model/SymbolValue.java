package edu.cs3500.spreadsheets.model;

import java.util.Objects;

//Im not sure we need this class, a formula is only a string, double, boolean, reference, or function.

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

  //TODO override methods
//  @Override
//  public String toString() {
//    return String.valueOf(b);
//  }
//
//  @Override
//  public boolean equals(Object that) {
//    if (this == that) {
//      return true;
//    }
//
//    if (!(that instanceof BoolValue)) {
//      return false;
//    }
//
//    return ((BoolValue) that).b == (this.b);
//  }
//
//  @Override
//  public int hashCode() {
//    return Objects.hash(b);
//  }


}
