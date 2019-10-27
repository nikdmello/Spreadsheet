package edu.cs3500.spreadsheets.model;

/**
 * An abstracted function object for processing any {@link Value}.
 * @param <R> The return type of this function
 */
public interface FormulaVisitor<R> {
  /**
   * Process a boolean value.
   * @param b the value
   * @return the desired result
   */
  R visitBoolean(boolean b);

  /**
   * Process a double value.
   * @param d the value
   * @return the desired result
   */
  R visitDouble(double d);

  /**
   * Process a String value.
   * @param s the value
   * @return the desired result
   */
  R visitString(String s);
}
