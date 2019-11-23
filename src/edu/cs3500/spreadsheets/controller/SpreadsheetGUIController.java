package edu.cs3500.spreadsheets.controller;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.SexpToFormula;
import edu.cs3500.spreadsheets.model.StringValue;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.view.GUIView;
import edu.cs3500.spreadsheets.view.ModelToTable;

public class SpreadsheetGUIController implements SpreadsheetController{
  private Worksheet sheet;
  private ModelToTable mtt;
  GUIView view;

  public SpreadsheetGUIController(Worksheet sheet){
    this.sheet = sheet;
    this.mtt = new ModelToTable(sheet);
  }

  @Override
  public void launchEditor() {
    this.view = new GUIView(mtt, new ControllerViewRequester(this));
  }

  @Override
  public void cellRequest(int row, int col, String formula){

    //creates the requested formula
    Sexp toCreate;
    try{
      toCreate = Parser.parse(formula);
    } catch (IllegalArgumentException iae){
      toCreate = null;
    }

    //if it is bad then it creates a cell signifying an error
    if(toCreate == null){
      sheet.createCell(col, row, new StringValue("Error"));
      view.render();
      return;
    }

    //if its ok, check to see if it is a cell update or creating a new cell create
    //update: update the cell and re eval, errors are displayed on screen to user
    Coord c = new Coord(col, row);
    Formula f = toCreate.accept(new SexpToFormula(sheet, c));
    if(sheet.getHashtable().containsKey(c)){
      sheet.changeContents(c, f);
      sheet.reEval(c);
      view.render();
      return;
    }

    //new cell: create cell and evaluate all, this will not be taxing since cells are already evaled
    //This is necessary to see if there is an error in the cell
    sheet.createCell(col, row, f);
    sheet.evalAll();
    view.render();
  }

  @Override
  public void delCell(int row, int col) {
    Coord cell = new Coord(col, row);
    if(sheet.getHashtable().containsKey(cell)){
      sheet.deleteCellAt(cell);
    }
  }

}
