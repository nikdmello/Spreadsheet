package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * Enumerates the different type of functions that are supported.
 */
enum FunctionType{SUM, PRODUCT, LT}

/**
 * A function which can be applied to one or more formulas as its arguments.
 */
public class Function implements Formula {
  private ArrayList<Formula> args;
  FunctionType type;

  /**
   * Constructs a function and its arguments.
   * @param args the formulas to be operated on.
   */
  public Function(FunctionType type, ArrayList<Formula> args){
    for(Formula f : args){
      if(f.visitFormuala(f).equals("ref")){
        this.args.addAll((Reference)f.getCellContents);
      }
      else {
        this.args.add(f);
      }
    }

  }
  @Override
  public Value evaluate() {
    switch(this.type){
      case SUM: return sum();
      case PRODUCT: return product();
      case LT: return lessthan();
      default: throw new IllegalArgumentException("Function not found.");
    }
  }

  @Override
  public String type() {
    return "func";
  }

  private DoubleValue sum(){
    double sum = 0;

    if(allNonNumeric(args)){
      return new DoubleValue(0);
    }

    for(Formula f : args){
      sum += f.evaluate().numberForm();
    }

    return new DoubleValue(sum);
  }

  private DoubleValue product(){
    double prod = 1;

    if(allNonNumeric(args)){
      return new DoubleValue(0);
    }

    for(Formula f : args){
      if(f.evaluate().isNumeric()){
        prod *= f.evaluate().numberForm();
      }
    }

    return new DoubleValue(prod);
  }

  private Value lessthan(){
    boolean b = false;
    if(args.size() > 2 || !args.get(0).evaluate().isNumeric() || !args.get(1).evaluate().isNumeric()){
      throw new IllegalArgumentException("Invalid arguments for less than");
    }

    b = args.get(0).evaluate().numberForm() < args.get(1).evaluate().numberForm();
    return new BoolValue(b);
  }

  private boolean allNonNumeric(ArrayList<Formula> arr){
    for(Formula f : arr){
      if(!f.evaluate().isNumeric()){
        return false;
      }
    }

    return true;
  }


}
