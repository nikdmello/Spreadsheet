package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.provider.view.ValueVisitor;

/**
 * Container for any type of data value such a double, boolean, or string.
 */
public interface Value extends Formula {

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

  /**
   * Overrides the toString method to display the Value within the Cell.
   *
   * @return Value as a String
   */

  /**
   * Visitor for values.
   *
   * @param v   value visitor
   * @param <R> return type
   * @return the type for whatever the visitor is doing
   */
  <R> R acceptV(ValueVisitor<R> v);

  String toString();

  boolean equals(Object that);

  int hashCode();
}
