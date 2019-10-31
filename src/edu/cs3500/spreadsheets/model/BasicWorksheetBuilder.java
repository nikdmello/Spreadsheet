package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;

public class BasicWorksheetBuilder implements WorksheetReader.WorksheetBuilder<BasicWorksheetModel> {
  private final BasicWorksheetModel sheet;

  /**
   * Constructor for the builder than instantiates a new BasicWorksheetModel.
   */
  public BasicWorksheetBuilder() {
    this.sheet = new BasicWorksheetModel();
  }

  @Override
  public WorksheetReader.WorksheetBuilder<BasicWorksheetModel> createCell(int col, int row,
                                                                          String contents) {
    if (contents == null) {
      throw new IllegalArgumentException("Cell contents is null.");
    }
    if (contents.substring(0, 1).equals("=")) {
      contents = contents.substring(1);
    }
    Sexp sexp = Parser.parse(contents);
    Formula f = sexp.accept(new SexpToFormula(this.sheet, new Coord(col, row)));
    sheet.createCell(col, row, f);
    return this;
  }

  @Override
  public BasicWorksheetModel createWorksheet() {
    return sheet;
  }
}
