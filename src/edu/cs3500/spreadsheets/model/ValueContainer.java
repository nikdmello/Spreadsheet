package edu.cs3500.spreadsheets.model;

/**
 * Container for any type of data value such a s int, boolean, or string.
 * @param <R> Type of the container
 */
public interface ValueContainer<R> {
  /**
   * Gets the value from the container.
   * @return the value
   */
  public R getValue();
}
