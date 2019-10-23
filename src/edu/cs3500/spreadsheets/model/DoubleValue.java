package edu.cs3500.spreadsheets.model;

/**
 * Container for an double.

 */
public class DoubleValue implements Value {
  Double value;

  /**
   * Constructs a new double container.
   * @param value int to contain
   */
  public DoubleValue(Double value){
    this.value = value;
  }

  @Override
  public Value evaluate() {
    return this;
  }
}
