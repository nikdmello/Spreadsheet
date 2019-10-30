package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.model.visitors.SexpToFormula;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;

public class BasicWorksheetBuilder implements WorksheetReader.WorksheetBuilder<BasicWorksheetModel> {
  private final BasicWorksheetModel sheet;

  public BasicWorksheetBuilder(){
    this.sheet = new BasicWorksheetModel();
  }

  @Override
  public WorksheetReader.WorksheetBuilder<BasicWorksheetModel> createCell(int col, int row, String contents) {
    if(contents == null){
      throw new IllegalArgumentException("Bad Cell");
    }
    if(contents.substring(0, 1).equals("=")){
      contents = contents.substring(1);
    }
    Sexp sexp = Parser.parse(contents);
    Formula f = sexp.accept(new SexpToFormula());
    sheet.createCell(col, row, f);
    return this;
  }

  @Override
  public BasicWorksheetModel createWorksheet() {
    return sheet;
  }
}
