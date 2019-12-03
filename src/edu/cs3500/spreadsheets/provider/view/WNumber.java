package edu.cs3500.spreadsheets.provider.view;

import java.util.Objects;

/**
 * Represents a number value.
 */
public final class WNumber implements Wexp, WValue {

  private final double d;

  /**
   * Constructs a new WNumber with the given number value.
   *
   * @param d the double
   */
  public WNumber(double d) {
    this.d = d;
  }

  @Override
  public <R> R accept(WexpVisitor<R> visitor) {
    return visitor.visitNumber(this.d);
  }

  @Override
  public <R> R accept(WValueVisitor<R> visitor) {
    return visitor.visitNumber(this.d);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WNumber wNumber = (WNumber) o;
    return Double.compare(wNumber.d, d) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(d);
  }
}
