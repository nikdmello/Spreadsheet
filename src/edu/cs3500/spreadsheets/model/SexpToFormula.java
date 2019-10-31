package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import edu.cs3500.spreadsheets.model.BoolValue;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.StringValue;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;

/**
 * Represents a visitor function object for processing a Sexp to Formula.
 */
public class SexpToFormula implements SexpVisitor<Formula> {
  private Worksheet currentSheet;
  private Coord toCreate;

  /**
   * Constructs a SexpToFormula visitor with a rference sheet to use.
   *
   * @param sheet the reference sheet
   */
  public SexpToFormula(Worksheet sheet, Coord toC) {
    this.currentSheet = sheet;
    this.toCreate = toC;
  }

  @Override
  public Formula visitBoolean(boolean b) {
    return new BoolValue(b);
  }

  @Override
  public Formula visitNumber(double d) {
    return new DoubleValue(d);
  }

  @Override
  public Formula visitSymbol(String s) {
    if (s.contains(" ") || s.contains(":")) {
      boolean hasCol = s.contains(":");
      String firstC;
      String secondC;
      if (hasCol) {
        int colnInd = s.indexOf(":");
        firstC = s.substring(0, colnInd);
        secondC = s.substring(colnInd + 1);
      } else {
        Scanner scan = new Scanner(s);
        firstC = scan.next();
        secondC = scan.next();
      }
      return new Reference(this.currentSheet, turnToCoord(firstC), turnToCoord(secondC), toCreate);
    } else {
      return new Reference(this.currentSheet, turnToCoord(s), toCreate);
    }
  }

  private static Coord turnToCoord(String firstC) {
    String letters;
    int number;
    int breakPoint = 0;
    for (char c : firstC.toCharArray()) {
      if (Character.isDigit(c)) {
        break;
      }
      breakPoint++;
    }
    letters = firstC.substring(0, breakPoint);
    number = Integer.parseInt(firstC.substring(breakPoint));
    return new Coord(Coord.colNameToIndex(letters), number);
  }

  @Override
  public Formula visitString(String s) {
    return new StringValue(s);
  }

  @Override
  public Formula visitSList(List<Sexp> l) {
    if (l.size() > 1) {
      ArrayList<Formula> arr = new ArrayList<Formula>();
      for (int i = 1; i < l.size(); i++) {
        arr.add(l.get(i).accept(this));
      }
      switch (l.get(0).toString()) {
        case "SUM":
          return new Function(FunctionType.SUM, arr);
        case "PRODUCT":
          return new Function(FunctionType.PRODUCT, arr);
        case "<":
          return new Function(FunctionType.LT, arr);
        case "CONCAT":
          return new Function(FunctionType.CAT, arr);
        default:
          throw new IllegalArgumentException("Invalid Input");
      }
    }

    return l.get(0).accept(this);
  }
}

