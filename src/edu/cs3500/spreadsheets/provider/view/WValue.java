package edu.cs3500.spreadsheets.provider.view;

/**
 * Represents a simple value type that a user of the model will be interested in.
 */
public interface WValue {
  /**
   * Accepts the given visitor for Wexp implementations.
   *
   * @param visitor the visitor
   * @param <R>     the return type of the visitor
   * @return the return value of the visitor
   */
  <R> R accept(WValueVisitor<R> visitor);
}
