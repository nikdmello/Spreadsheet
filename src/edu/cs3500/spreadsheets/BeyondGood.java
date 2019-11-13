package edu.cs3500.spreadsheets;

import java.io.FileNotFoundException;
import java.io.FileReader;

import edu.cs3500.spreadsheets.model.BasicWorksheetBuilder;
import edu.cs3500.spreadsheets.model.BasicWorksheetModel;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.view.ModelToTable;
import edu.cs3500.spreadsheets.view.SpreadsheetGraphicsView;

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
    String newFileName;
    String cellName = "";
    FileReader file;
    BasicWorksheetBuilder builder = new BasicWorksheetBuilder();

    /*
    TODO
    -in some-filename -save some-new-filename — opens the first file, and saves it as the second file, using the textual view you created
    -in some-filename -gui — opens your graphical view and loads the requested file and evaluates it
    -gui — opens your graphical view with a blank new spreadsheet
   */

    // Obtain filename and cell name
    if (args.length == 4 && args[0].equals("-in") && args[2].equals("-eval")) {
      fileName = args[1];
      cellName = args[3];
    }
    else if (args.length == 4 && args[0].equals("-in") && args[2].equals("-save")) {
      fileName = args[1];
      newFileName = args[3];
    }
    else if (args.length == 3 && args[0].equals("-in") && args[2].equals("-gui")) {
      return;
    }
    else if (args.length == 1 && args[0].equals("-gui")) {
      return;
    }
    else {
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

    ModelToTable mtt = new ModelToTable(model);
    SpreadsheetGraphicsView view = new SpreadsheetGraphicsView(mtt);
    view.render();
  }


}