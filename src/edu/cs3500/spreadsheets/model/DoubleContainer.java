package edu.cs3500.spreadsheets.model;

/**
 * Container for an double.
 * @param <Double> integer to contain
 */
public class DoubleContainer<Double> implements ValueContainer {
  Double value;

  /**
   * Constructs a new double container.
   * @param value int to contain
   */
  public DoubleContainer(Double value){
    this.value = value;
  }
  @Override
  public Double getValue() {
    return this.value;
  }
}
