package edu.cs3500.spreadsheets.provider.view;

/**
 * Represents a visitor of values that are evaluated from formulae.
 *
 * @param <R> the return type of this visitor
 */
public interface WValueVisitor<R> {

  /**
   * Visits a boolean value.
   *
   * @param b the boolean
   * @return the return value of this visitor
   */
  R visitBoolean(boolean b);

  /**
   * Visits a number value.
   *
   * @param d the number
   * @return the return value of this visitor
   */
  R visitNumber(double d);

  /**
   * Visits a String value.
   *
   * @param s the String
   * @return the return value of this visitor
   */
  R visitString(String s);

  /**
   * Visits a blank value.
   *
   * @return the return value of this visitor
   */
  R visitBlank();
}
