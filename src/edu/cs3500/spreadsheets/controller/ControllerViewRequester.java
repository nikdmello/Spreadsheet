package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.model.Coord;

/**
 * Allows the view to request changes to the model from the controller.
 */
public class ControllerViewRequester {
  SpreadsheetController control;

  ControllerViewRequester(SpreadsheetController control) {
    this.control = control;
  }

  public void requestCell(int row, int col, String formula) {
    if (formula.substring(0, 1).equals("=")) {
      formula = formula.substring(1);
    }
    control.cellRequest(row, col, formula);
  }

  public void delCell(int row, int col){
    control.delCell(row, col);
  }
}
