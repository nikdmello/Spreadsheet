package edu.cs3500.spreadsheets.view;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import edu.cs3500.spreadsheets.model.BasicWorksheetBuilder;
import edu.cs3500.spreadsheets.model.BasicWorksheetModel;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.WorksheetReader;


public class SpreadsheetGraphicsViewTest {

  ModelToTable mtt = new ModelToTable(createModel());
  SpreadsheetGraphicsView view = new SpreadsheetGraphicsView(mtt);

  @Test
  public void render() {
    view.render();
  }

  Worksheet createModel(){
    String fileName = "spreadsheet.txt";
    FileReader file;
    BasicWorksheetBuilder builder = new BasicWorksheetBuilder();

    try {
      file = new FileReader(fileName);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found.");
    }

    BasicWorksheetModel model = WorksheetReader.read(builder, file);
    Coord errorCell = model.evalAll();
    if (errorCell != null) {
      System.out.print("Error at cell " + Coord.colIndexToName(errorCell.col) + errorCell.row);
    }
    return model;
  }
}