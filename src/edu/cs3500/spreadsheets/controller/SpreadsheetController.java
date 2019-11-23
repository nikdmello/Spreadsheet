package edu.cs3500.spreadsheets.controller;

public interface SpreadsheetController {

  /**
   * Launches a spreadsheet into editor mode.
   */
  void launchEditor();

  void cellRequest(int row, int col, String formula);

  void delCell(int row, int col);
}
