package edu.cs3500.spreadsheets.provider.view;

import java.util.Objects;

/**
 * Represents a String value.
 */
public final class WString implements Wexp, WValue {

  private final String s;

  /**
   * Constructs a new WString with the given String value.
   *
   * @param s the String
   */
  public WString(String s) {
    this.s = s;
  }

  @Override
  public <R> R accept(WexpVisitor<R> visitor) {
    return visitor.visitString(this.s);
  }

  @Override
  public <R> R accept(WValueVisitor<R> visitor) {
    return visitor.visitString(this.s);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WString wString = (WString) o;
    return Objects.equals(s, wString.s);
  }

  @Override
  public int hashCode() {
    return Objects.hash(s);
  }
}
