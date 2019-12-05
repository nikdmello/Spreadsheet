package edu.cs3500.spreadsheets.view;

/**
 * The model to view interface represents the methods that are to be used in the view.
 */
public interface ModelToView {

  /**
   * Returns the number of columns in the Spreadsheet.
   *
   * @return number of Spreadsheet columns.
   */
  int numCols();

  /**
   * Returns the number of rows in the Spreadsheet.
   *
   * @return number of Spreadsheet rows.
   */
  int numRows();

  /**
   * Gets the column headers based on the contents of the Spreadsheet model. If coords go from A1 to
   * L4, this method will return values from A to L in a String array. Returns a minimum number of
   * columns despite the amount of Spreadsheet contents, just like Excel does.
   *
   * @return column names.
   */
  String[] colNames();

  /**
   * Gets the rows headers based on the contents of the Spreadsheet model. If coords go from A1 to
   * D11, this method will return values from 1 to 11 in a String array. Returns a minimum number of
   * rows despite the amount of Spreadsheet contents, just like Excel does.
   *
   * @return row names.
   */
  String[] rowNames();

  /**
   * Wrapper method that takes the contents of the Spreadsheet hashtable and puts them into a 2D
   * array to be used in the JTable.
   *
   * @return 2D String array of Cell formulas.
   */
  String[][] translate();

  /**
   * Gets the formula to display in the formula top bar.
   *
   * @return an array map of formulas to be displayed
   */
  String[][] formTranslate();
}
