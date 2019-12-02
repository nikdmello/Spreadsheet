package edu.cs3500.spreadsheets.provider.view;

/**
 * Represents Validation logic and exception throwing.
 */
public final class Validation {

  /**
   * Private constructor.
   */
  private Validation() {
    //
  }

  /**
   * Requires that the given expression is true, or else throws an {@link IllegalArgumentException}
   * with a message.
   *
   * @param exp     the expression
   * @param message the message
   */
  public static void requireTrue(boolean exp, String message) {
    if (!exp) {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * Requires that the given object is not null, or else throws an {@link IllegalArgumentException}
   * with a message.
   *
   * @param o    the object
   * @param noun the name of the object (i.e. the "X" in "X must not be null")
   * @param <T>  the type of the object
   * @return {@code o} when it is not null
   */
  public static <T> T requireNonNull(T o, String noun) {
    if (o == null) {
      throw new IllegalArgumentException(noun + " must not be null");
    }
    return o;
  }
}
