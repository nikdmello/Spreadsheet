package edu.cs3500.spreadsheets;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

import edu.cs3500.spreadsheets.model.BasicWorksheetBuilder;
import edu.cs3500.spreadsheets.model.BasicWorksheetModel;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.view.GUIView;
import edu.cs3500.spreadsheets.view.ModelToTable;
import edu.cs3500.spreadsheets.view.SpreadsheetGraphicsView;
import edu.cs3500.spreadsheets.view.SpreadsheetTextualView;

/**
 * The main class for our program. The spreadsheet program should start from here.
 */
public class BeyondGood {
  /**
   * The main entry point.
   *
   * @param args any command-line arguments
   */
  public static void main(String[] args) {
    String fileName;
    String cellName;
    String newFileName;
    FileReader file;
    BasicWorksheetModel model;
    BasicWorksheetBuilder builder = new BasicWorksheetBuilder();

    // Obtain filename and cell name
    if (args.length == 4 && args[0].equals("-in") && args[2].equals("-eval")) {
      fileName = args[1];
      cellName = args[3];
      file = getFile(fileName);
      model = buildModel(builder, file);
      Coord errorCell = model.evalAll();

      if (checkErrorCell(errorCell)) {
        return;
      }

      int coordCol = Coord.colNameToIndex(cellName.substring(0, 1));
      int coordRow = Integer.parseInt(cellName.substring(1));
      Coord coord = new Coord(coordCol, coordRow);
      Cell cell = model.getCellAt(coord);
      cell.evaluateCell();
      String ret = cellString(cell);
      System.out.print(ret);
    } else if (args.length == 4 && args[0].equals("-in") && args[2].equals("-save")) {
      fileName = args[1];
      newFileName = args[3];
      file = getFile(fileName);
      model = buildModel(builder, file);
      Coord errorCell = model.evalAll();
      if (checkErrorCell(errorCell)) {
        try {
          PrintWriter writer = new PrintWriter(newFileName);
          writer.write("Error at cell " + Coord.colIndexToName(errorCell.col) + errorCell.row);
          return;
        } catch (FileNotFoundException e) {
          throw new IllegalArgumentException("File not found");
        }
      }
      SpreadsheetTextualView view = new SpreadsheetTextualView(model, newFileName);
      view.render();
    } else if (args.length == 3 && args[0].equals("-in") && args[2].equals("-gui")) {
      fileName = args[1];
      file = getFile(fileName);
      model = buildModel(builder, file);
      ModelToTable mtt = new ModelToTable(model);
      SpreadsheetGraphicsView view = new SpreadsheetGraphicsView(mtt);
      view.render();
    } else if (args.length == 1 && args[0].equals("-gui")) {
      SpreadsheetGraphicsView guiView = new SpreadsheetGraphicsView();
      guiView.render();
    } else if (args.length == 1 && args[0].equals("-edit")) {
      fileName = args[1];
      file = getFile(fileName);
      model = buildModel(builder, file);
      ModelToTable mtt = new ModelToTable(model);
      GUIView guiView = new GUIView(mtt);
    } else {
      throw new IllegalArgumentException("Invalid command.");
    }
  }

  private static BasicWorksheetModel buildModel(BasicWorksheetBuilder builder, FileReader file) {
    return WorksheetReader.read(builder, file);
  }

  private static FileReader getFile(String fileName) {
    try {
      return new FileReader(fileName);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found.");
    }
  }

  private static String cellString(Cell cell) {
    if (cell.getFormula().type().equals("double")) {
      return String.format("%f", cell.getFormula().evaluate().numberForm());
    } else {
      return "" + cell;
    }
  }

  private static Boolean checkErrorCell(Coord errorCell) {
    if (errorCell != null) {
      System.out.print("Error at cell " + Coord.colIndexToName(errorCell.col) + errorCell.row);
      return true;
    }
    return false;
  }
}