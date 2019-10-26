package edu.cs3500.spreadsheets.model;

/**
 * Container for any type of data value such a double, boolean, or string.
 */
public interface Value extends Formula {
  Value evaluate();

  /**
   * Accepts a value visitor and returns R
   * @param visitor
   * @return the desired value R
   */
  <R> R accept(ValueVisitor<R> visitor);
}


