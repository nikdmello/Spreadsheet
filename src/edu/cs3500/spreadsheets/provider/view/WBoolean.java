package edu.cs3500.spreadsheets.provider.view;

import java.util.Objects;

/**
 * Represents a boolean value.
 */
public final class WBoolean implements Wexp, WValue {

  public static final WBoolean TRUE = new WBoolean(true);
  public static final WBoolean FALSE = new WBoolean(false);

  private final boolean b;

  /**
   * Constructs a new WBoolean with a boolean value.
   *
   * @param b the boolean
   */
  public WBoolean(boolean b) {
    this.b = b;
  }

  @Override
  public <R> R accept(WexpVisitor<R> visitor) {
    return visitor.visitBoolean(this.b);
  }

  @Override
  public <R> R accept(WValueVisitor<R> visitor) {
    return visitor.visitBoolean(this.b);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WBoolean wBoolean = (WBoolean) o;
    return b == wBoolean.b;
  }

  @Override
  public int hashCode() {
    return Objects.hash(b);
  }
}
