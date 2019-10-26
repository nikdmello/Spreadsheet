package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * A function which can be applied to one or more formulas as its arguments.
 */
public class Function implements Formula {
  ArrayList<Formula> args;

  /**
   * Constructs a function and its arguments.
   * @param args the formulas to be operated on.
   */
  public Function(Formula ... args){
    for(Formula f : args){
      this.args.add(f);
    }
  }
  @Override
  public Value evaluate() {
    //TODO
    return null;
  }

}
