package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.view.GUIView;
import edu.cs3500.spreadsheets.view.ModelToTable;
import edu.cs3500.spreadsheets.view.SpreadsheetView;

public class SpreadsheetGUIController implements SpreadsheetController{
  private Worksheet sheet;
  private ModelToTable mtt;
  GUIView view;

  SpreadsheetGUIController(Worksheet sheet){
    this.sheet = sheet;
    this.mtt = new ModelToTable(sheet);
  }

  @Override
  public void launchEditor() {
    this.view = new GUIView(mtt);
  }

  private void cellRequest(int row, int col, Formula f){

  }
}
