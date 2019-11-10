package edu.cs3500.spreadsheets.view;

import java.lang.reflect.Array;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Value;
import edu.cs3500.spreadsheets.model.Worksheet;

class ModelToTable {
  private final Worksheet sheet;

  ModelToTable(Worksheet sheet){
    this.sheet = sheet;
  }

  String[] colNames(){
    String headers[] = new String[sheet.getNumCols()];
    for(int i = 0; i < headers.length; i++){
      headers[i] = Coord.colIndexToName(i + 1);
    }
    return headers;
  }

  String[] rowNames(){
    String headers[] = new String[sheet.getNumRows()];
    for(int i = 0; i < headers.length; i++){
      headers[i] = "" + i + 1;
    }
    return headers;
  }

  String[][] translate(){
    String[][] translation = new String[sheet.getNumCols()][sheet.getNumRows()];
    for(int i = 0; i < sheet.getNumCols(); i++){
      for(int j = 0; j < sheet.getNumRows(); j++){
        Coord check = new Coord(i + 1, j + 1);
        if(sheet.getHashtable().containsKey(check)){
          Cell cell = sheet.getHashtable().get(check);
          if (cell.getFormula().type().equals("double")) {
            translation[i][j] = String.format("%f", cell.getFormula().evaluate().numberForm());
          } else {
            translation[i][j] = "" + cell;
          }
        }
        else{
          translation[i][j] = null;
        }
      }
    }
    return translation;
  }
}
