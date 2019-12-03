package edu.cs3500.spreadsheets.provider.view;

import java.util.List;

import edu.cs3500.spreadsheets.model.Coord;

/**
 * Represents a visitor of Wexp implementations.
 *
 * @param <R> the return type of this visitor
 */
public interface WexpVisitor<R> {

  /**
   * Visits a blank value.
   *
   * @return the return value of this visitor
   */
  R visitBlank();

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
   * Visits a reference.
   *
   * @param ref the reference
   * @return the return value of this visitor
   */
  R visitReference(Coord ref);

  /**
   * Visits a range.
   *
   * @param from the from range
   * @param to   the to range
   * @return the return value of this visitor
   */
  R visitRange(Coord from, Coord to);

  /**
   * Visits a function.
   *
   * @param name the name of the function
   * @param args the arguments of the function
   * @return the return value of this visitor
   */
  R visitFunction(String name, List<Wexp> args);
}
