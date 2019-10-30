package edu.cs3500.spreadsheets.model;

import java.text.Normalizer;
import java.util.ArrayList;

/**
 * A visitor to determine whether a reference is self-referential in any way.
 */
public class SelfRefVisitor implements FormulaVisitor<Boolean> {
  private ArrayList<Formula> banned;

  /**
   * Constructs a SelfRefVisitor with an initial Formula f which should not be referenced.
   *
   * @param f Formula of banned cell
   */
  public SelfRefVisitor(Formula f) {
    banned = new ArrayList<Formula>();
    banned.add(f);
  }

  /**
   * Constructs a SelfRefVisitor with the coordinate to ban and a list of already banned Coords.
   *
   * @param f     formula to ban
   * @param soFar formulas banned so far
   */
  private SelfRefVisitor(Formula f, ArrayList<Formula> soFar) {
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
      if (listHasFormula(this.banned, r.sheet.getCellAt(c).getFormula())) {
        return true;
      } else if (r.sheet.getCellAt(c)
              .getFormula()
              .accept(new SelfRefVisitor(r.sheet.getCellAt(c).getFormula(), this.banned))) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Boolean visitFunc(Function f) {
    for (Formula formula : f.args) {
      if (formula.accept(this)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks to see if the array contains the same formula reference.
   * @param arr the array to check
   * @param f the formula to check
   * @return true if f is found in arr
   */
  private static boolean listHasFormula(ArrayList<Formula> arr, Formula f){
    for(Formula fu : arr){
      if(fu == f){
        return true;
      }
    }
    return false;
  }
}
