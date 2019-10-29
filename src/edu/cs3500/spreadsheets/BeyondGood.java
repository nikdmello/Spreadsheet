package edu.cs3500.spreadsheets;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import edu.cs3500.spreadsheets.model.BasicWorksheetBuilder;
import edu.cs3500.spreadsheets.model.WorksheetReader;

/**
 * The main class for our program.
 */
public class BeyondGood {
  /**
   * The main entry point.
   * @param args any command-line arguments
   */
  public static void main(String[] args) throws IOException {
    String fileName = "";
    String cellName = "";
    FileReader file;
    Scanner scanner;
    BasicWorksheetBuilder builder = new BasicWorksheetBuilder();

    /*
      TODO: For now, look in the args array to obtain a filename and a cell name,
      - read the file and build a model from it, 
      - evaluate all the cells, and
      - report any errors, or print the evaluated value of the requested cell.
    */


    Readable r = new InputStreamReader(System.in);
    Appendable a = new OutputStreamWriter(System.out);

    // Parse data inside spreadsheet
    scanner = new Scanner(r);

    // Obtain filename and cell name
    if (args.length > 0) {
      fileName = args[1];
      cellName = args[3];
    }
    else {
      throw new IllegalArgumentException("Invalid command.");
    }

    int i;
    // Read file using first two arguments
    try {
      file = new FileReader(fileName);
      while ((i = file.read()) != -1) {
        System.out.println((char) i);
      }
    }
    catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found.");
    }

    WorksheetReader.read(builder, r);


  }
}