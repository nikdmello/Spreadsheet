package edu.cs3500.spreadsheets.model;

/**
 * Enumerates the different type of functions that are supported.
 */
public enum FunctionType {
  SUM("SUM"), PRODUCT("PRODUCT"), LT("<"), CAT("CONCAT");
  final String type;

  /**
   * Constructs a FunctionType with the given type.
   *
   * @param type the type of function
   */
  FunctionType(String type) {
    this.type = type;
  }
}
