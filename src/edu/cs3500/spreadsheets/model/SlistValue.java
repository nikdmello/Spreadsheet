package edu.cs3500.spreadsheets.model;
//Same comment as Symbol value
/**
 * Represents a container for an Slist.
 * TODO: Explain what Slist is
 */
public class SlistValue implements Value {

  @Override
  public Value evaluate() {
    return null;
  }

  @Override
  public <R> R accept(FormulaVisitor<R> visitor) {
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

  @Override
  public String type() {
    return null;
  }

  //TODO override methods
}
