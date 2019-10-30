package edu.cs3500.spreadsheets;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.OutputStreamWriter;

import edu.cs3500.spreadsheets.model.BasicWorksheetBuilder;
import edu.cs3500.spreadsheets.model.BasicWorksheetModel;
import edu.cs3500.spreadsheets.model.WorksheetReader;

/**
 * The main class for our program.
 */
public class BeyondGood {
  /**
   * The main entry point.
   * @param args any command-line arguments
   */
  public static void main(String[] args) {
    String fileName;
    String cellName = "";
    FileReader file;
    BasicWorksheetBuilder builder = new BasicWorksheetBuilder();

    /*
      TODO: For now, look in the args array to obtain a filename and a cell name,
      - read the file and build a model from it, 
      - evaluate all the cells, and
      - report any errors, or print the evaluated value of the requested cell.
    */

    Appendable a = new OutputStreamWriter(System.out);

    // Obtain filename and cell name
    if (args.length == 4 && args[0].equals("-in") && args[2].equals("-eval")) {
      fileName = args[1];
      cellName = args[3];
    }
    else {
      throw new IllegalArgumentException("Invalid command.");
    }

    try {
      file = new FileReader(fileName);
    }
    catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found.");
    }

    BasicWorksheetModel model = WorksheetReader.read(builder, file);
  }
}