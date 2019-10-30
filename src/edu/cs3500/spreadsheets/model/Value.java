package edu.cs3500.spreadsheets.model;

/**
 * Container for any type of data value such a double, boolean, or string.
 */
public interface Value extends Formula {
  //TODO: parse text, if first is not "=" then its a value

  /**
   * Evaluates the value as itself because values evaluate to themselves.
   *
   * @return this Value
   */
  Value evaluate();

  /**
   * Checks to see if the Value is a numeric value.
   *
   * @return true if numeric
   */
  boolean isNumeric();

  /**
   * The numeric value of a Value, returns 0 if it is not a number.
   *
   * @return numeric form of Value
   */
  double numberForm();
}
