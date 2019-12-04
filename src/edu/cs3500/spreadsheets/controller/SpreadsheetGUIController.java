package edu.cs3500.spreadsheets.controller;

import java.io.FileReader;

import edu.cs3500.spreadsheets.model.BasicWorksheetBuilder;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.SelfRefVisitor;
import edu.cs3500.spreadsheets.model.SexpToFormula;
import edu.cs3500.spreadsheets.model.StringValue;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.view.GUIView;
import edu.cs3500.spreadsheets.view.ModelToTable;
import edu.cs3500.spreadsheets.view.SpreadsheetTextualView;

/**
 * A controller to run the GUI Spreadsheet view.
 */
public class SpreadsheetGUIController implements SpreadsheetController {
  private Worksheet sheet;
  private ModelToTable mtt;
  private GUIView view;

  /**
   * Constructs the Gui view controller.
   *
   * @param sheet the worksheet to be edited
   */
  public SpreadsheetGUIController(Worksheet sheet) {
    this.sheet = sheet;
    this.mtt = new ModelToTable(sheet);
  }

  @Override
  public void launchEditor() {
    this.view = new GUIView(mtt, new ControllerViewRequester(this));
  }

  @Override
  public void cellRequest(int row, int col, String formula) {

    //creates the requested formula
    Sexp toCreate;
    try {
      toCreate = Parser.parse(formula);
    } catch (IllegalArgumentException iae) {
      toCreate = null;
    }

    //if it is bad then it creates a cell signifying an error
    if (toCreate == null) {
      sheet.createCell(col, row, new StringValue("Error"));
      view.render();
      return;
    }

    //if its ok, check to see if it is a cell update or creating a new cell create
    //update: update the cell and re eval, errors are displayed on screen to user
    Coord c = new Coord(col, row);
    Formula f = toCreate.accept(new SexpToFormula(sheet, c));

    if (sheet.getHashtable().containsKey(c)) {
      sheet.changeContents(c, f);
      sheet.reEval(c);
      view.render();
      return;
    }

    //new cell: create cell and evaluate all, this will not be taxing since cells are already evaled
    //This is necessary to see if there is an error in the cell
    if (f.accept(new SelfRefVisitor(c))) {
      sheet.createCell(col, row, f);
      sheet.setErrorAt(c);
    } else {
      sheet.createCell(col, row, f);
    }
    sheet.evalAll();
    view.render();
  }

  @Override
  public void delCell(int row, int col) {
    Coord cell = new Coord(col, row);
    if (sheet.getHashtable().containsKey(cell)) {
      sheet.deleteCellAt(cell);
      sheet.evalAll();
      view.render();
    }
  }

  @Override
  public void loadFile(FileReader file) {
    this.sheet = WorksheetReader.read(new BasicWorksheetBuilder(), file);
    this.mtt = new ModelToTable(this.sheet);
    this.view.setVisible(false);
    this.view.dispose();
    this.view = new GUIView(mtt, new ControllerViewRequester(this));
  }

  @Override
  public void saveFile(String text) {
    SpreadsheetTextualView saved = new SpreadsheetTextualView(this.sheet, text);
    saved.render();
  }

  @Override
  public void addCols() {
    this.sheet.addCols();
  }

  @Override
  public void addRows() {
    this.sheet.addRows();
  }

  /**
   * Sets the controller to work with a new view
   *
   * @param view the view to work with
   */
  @Override
  public void setView(GUIView view) {
    this.view = view;
  }
}
