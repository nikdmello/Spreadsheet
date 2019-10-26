package edu.cs3500.spreadsheets.model;

/**
 * Container for an double.
 */
public class DoubleValue implements Value {
  private Double value;

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

  @Override
  public <R> R accept(ValueVisitor<R> visitor) {
    return visitor.visitDouble(this.value);
  }
}
