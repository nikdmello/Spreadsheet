package edu.cs3500.spreadsheets.provider.view;

/**
 * Represents a Wexp: an expression for worksheets.
 */
public interface Wexp {

  /**
   * Accepts the given visitor for Wexp implementations.
   *
   * @param visitor the visitor
   * @param <R>     the return type of the visitor
   * @return the return value of the visitor
   */
  <R> R accept(WexpVisitor<R> visitor);
}
