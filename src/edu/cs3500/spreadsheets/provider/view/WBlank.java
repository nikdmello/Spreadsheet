package edu.cs3500.spreadsheets.provider.view;

/**
 * Represents a blank value.
 */
public final class WBlank implements Wexp, WValue {

  public static final WBlank INSTANCE = new WBlank();

  private WBlank() {
    //
  }

  @Override
  public <R> R accept(WexpVisitor<R> visitor) {
    return visitor.visitBlank();
  }

  @Override
  public <R> R accept(WValueVisitor<R> visitor) {
    return visitor.visitBlank();
  }
}
