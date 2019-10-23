package edu.cs3500.spreadsheets.model;

/**
 * Container for a boolean.
 * @param <Boolean> boolean for the container to hold
 */
public class BoolContainer<Boolean> implements ValueContainer{
  Boolean b;

  public BoolContainer(Boolean b){
    this.b = b;
  }

  @Override
  public Boolean getValue() {
    return this.b;
  }
}
