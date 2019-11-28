package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * A visitor to determine whether a reference is self-referential in any way.
 */
public class SelfRefVisitor implements FormulaVisitor<Boolean> {
  private ArrayList<Coord> banned;

  /**
   * Constructs a SelfRefVisitor with an initial Coord c which should not be referenced.
   *
   * @param c Coord of banned cell
   */
  public SelfRefVisitor(Coord c) {
    banned = new ArrayList<Coord>();
    banned.add(c);
  }

  /**
   * Constructs a SelfRefVisitor with the coordinate to ban and a list of already banned Coords.
   *
   * @param c     Coord to ban
   * @param soFar formulas banned so far
   */
  private SelfRefVisitor(Coord c, ArrayList<Coord> soFar) {
    banned = new ArrayList<Coord>();
    banned.addAll(soFar);
  }

  @Override
  public Boolean visitBoolean(boolean b) {
    return false;
  }

  @Override
  public Boolean visitDouble(double d) {
    return false;
  }

  @Override
  public Boolean visitString(String s) {
    return false;
  }

  @Override
  public Boolean visitReference(Reference r) {
    for (Coord c : r.getCellCoords()) {
      if (banned.contains(c)) {
        return true;
      } else if (r.sheet.getCellAt(c) != null
              && r.sheet.getCellAt(c).getFormula().accept(new SelfRefVisitor(c, this.banned))) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Boolean visitFunc(Function f) {
    for (Formula formula : f.originalArgs) {
      if (formula.accept(this)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks to see if the array contains the same formula reference.
   *
   * @param arr the array to check
   * @param f   the formula to check
   * @return true if f is found in arr
   */
  private static boolean listHasFormula(ArrayList<Formula> arr, Formula f) {
    for (Formula fu : arr) {
      if (fu == f) {
        return true;
      }
    }
    return false;
  }
}
