package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * A function which can be applied to one or more formulas as its arguments.
 */
public class Function implements Formula {
  public final ArrayList<Formula> args;
  private FunctionType type;

  /**
   * Constructs a function and its arguments.
   *
   * @param args the formulas to be operated on.
   */
  public Function(FunctionType type, ArrayList<Formula> args) {
    this.args = new ArrayList<>();
    this.type = type;
    for (Formula f : args) {
      if (f.type().equals("ref")) {
        this.args.addAll(f.accept(new RefVisitor()));
      } else {
        this.args.add(f);
      }
    }
  }

  /**
   * Copy constructor for function
   *
   * @param f function to copy
   */
  public Function(Function f) {
    this.args = f.args;
    this.type = f.type;
  }

  @Override
  public Value evaluate() {
    switch (this.type) {
      case SUM:
        return sum();
      case PRODUCT:
        return product();
      case LT:
        return lessThan();
      case CAT:
        return concat();
      default:
        throw new IllegalArgumentException("Function not found.");
    }
  }

  @Override
  public <R> R accept(FormulaVisitor<R> visitor) {
    return visitor.visitFunc(this);
  }

  @Override
  public String type() {
    return "func";
  }

  private DoubleValue sum() {
    double sum = 0;

    if (allNonNumeric(args)) {
      return new DoubleValue(0);
    }

    for (Formula f : args) {
      if (f != null) {
        sum += f.evaluate().numberForm();
      }
    }

    return new DoubleValue(sum);
  }

  /**
   * Multiplies all of arguments passed to the function, ignores non-numeric values.
   *
   * @return the product of all the arguments
   */
  private DoubleValue product() {
    double prod = 1;

    if (allNonNumeric(args)) {
      return new DoubleValue(0);
    }

    for (Formula f : args) {
      if (f != null && f.evaluate().isNumeric()) {
        prod *= f.evaluate().numberForm();
      }
    }

    return new DoubleValue(prod);
  }

  /**
   * Taking in only two arguments, returns true if the first is less than the second.
   *
   * @return true if the first argument is less than the second
   * @throws IllegalArgumentException if there are not exactly two arguments
   */
  private Value lessThan() throws IllegalArgumentException {
    boolean b = false;
    if (args.size() != 2 || args.get(0) == null && !args.get(0).evaluate().isNumeric()
        || args.get(1) == null && !args.get(1).evaluate().isNumeric()) {
      throw new IllegalArgumentException("Invalid arguments for less than");
    }

    b = args.get(0).evaluate().numberForm() < args.get(1).evaluate().numberForm();
    return new BoolValue(b);
  }

  /**
   * Concatenates a string version of each argument to one string.
   *
   * @return A string value containing the concatenated string
   */
  private StringValue concat() {
    StringBuilder s = new StringBuilder();
    for (Formula f : args) {
      if (f == null) {
        throw new IllegalArgumentException("Error");
      }
      s.append(f.evaluate().accept(new AsStringVisitor()));
    }
    return new StringValue(s.toString());
  }

  /**
   * Checks to see if all the evaluations in an array list of formulas return numeric values.
   *
   * @param arr array list of formulas
   * @return true if all values are numeric
   */
  private boolean allNonNumeric(ArrayList<Formula> arr) {
    for (Formula f : arr) {
      if (f != null && !f.evaluate().isNumeric()) {
        return true;
      }
    }
    return false;
  }
}
