package edu.cs3500.spreadsheets.model.visitors;

import java.util.ArrayList;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.Function;
import edu.cs3500.spreadsheets.model.Reference;

public class SelfRefVisitor implements FormulaVisitor<Boolean>{
  private ArrayList<Coord> banned;

  public SelfRefVisitor(Coord c){
    banned = new ArrayList<Coord>();
    banned.add(c);
  }

  private SelfRefVisitor(Coord c, ArrayList<Coord> soFar){
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
    for(Coord c : banned){
      if(r.getCellCoords().contains(c)){
        return true;
      }
    }
    for(Coord c : r.getCellCoords()){
      try {
        if (r.sheet.getCellAt(c) != null) {
          if(r.sheet.getCellAt(c).evaluateCell().accept(new SelfRefVisitor(c, banned))) {
            return true;
          }
        }
      } catch (IllegalArgumentException e){
        return true;
      }
    }
    return false;
  }

  @Override
  public Boolean visitFunc(Function f) {
    //TODO: think more simply
}
