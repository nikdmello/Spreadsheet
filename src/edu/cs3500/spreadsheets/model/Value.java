package edu.cs3500.spreadsheets.model;

import java.util.List;

import edu.cs3500.spreadsheets.sexp.SBoolean;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * Represents a value which is one of:
 * A boolean
 * A double
 * A String
 */
public class Value<ValueContainer> implements Cell {
   ValueContainer val;

  public Value(Sexp val) {
    this.val = val.accept(this);
  }

  @Override
  public ValueContainer evaluate() {
    return val;
  }

  @Override
  public ValueContainer visitBoolean(boolean b) {
    return new BoolContainer(b);
  }

  @Override
  public ValueContainer visitNumber(double d) {
    return d;
  }

  @Override
  public ValueContainer visitSymbol(String s) {
    return s;
  }

  @Override
  public ValueContainer visitString(String s) {
    return s;
  }

  @Override
  public ValueContainer visitSList(List l) {
    return l;
  }
}

