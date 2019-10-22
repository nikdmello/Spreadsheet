package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.SBoolean;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * Represents a value which is one of:
 * A boolean
 * A double
 * A String
 */
public class Value implements Cell {
  Sexp val;

  public Value(Sexp val) {
    this.val = val;
  }

  @Override
  public Sexp evaluate() {
    return val;
  }
}

