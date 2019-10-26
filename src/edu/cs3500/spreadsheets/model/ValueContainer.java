package edu.cs3500.spreadsheets.model;

/**
 * Represents a visitor function object for processing a Value to String.
 */
public class ValueContainer implements ValueVisitor<String> {
  
  @Override
  public String visitBoolean(boolean b) {
    return String.valueOf(b);
  }

  @Override
  public String visitDouble(double d) {
    return String.valueOf(d);
  }

  @Override
  public String visitString(String s) {
    return s;
  }
}
