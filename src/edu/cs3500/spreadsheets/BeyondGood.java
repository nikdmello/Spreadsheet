package edu.cs3500.spreadsheets;

import java.io.FileNotFoundException;
import java.io.FileReader;

import edu.cs3500.spreadsheets.model.BasicWorksheetBuilder;
import edu.cs3500.spreadsheets.model.BasicWorksheetModel;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorksheetReader;

//TODO: Last junit test
//TODO: Ask about imposing a limit for rows and columns
//TODO: Look at self eval and write last couple tests

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
    String cellName = "";
    FileReader file;
    BasicWorksheetBuilder builder = new BasicWorksheetBuilder();

    // Obtain filename and cell name
    if (args.length == 4 && args[0].equals("-in") && args[2].equals("-eval")) {
      fileName = args[1];
      cellName = args[3];
    } else {
      throw new IllegalArgumentException("Invalid command.");
    }

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

    int coordCol = Coord.colNameToIndex(cellName.substring(0, 1));
    int coordRow = Integer.parseInt(cellName.substring(1));
    Coord coord = new Coord(coordCol, coordRow);

    Cell cell = model.getCellAt(coord);
    cell.evaluateCell();
    String ret = "";
    if (cell.getFormula().type().equals("double")) {
      ret = String.format("%f", cell.getFormula().evaluate().numberForm());
    } else {
      ret = "" + cell;
    }
    System.out.print(ret);
  }
}