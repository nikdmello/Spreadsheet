import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import edu.cs3500.spreadsheets.model.BasicWorksheetBuilder;
import edu.cs3500.spreadsheets.model.BasicWorksheetModel;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.view.SpreadsheetTextualView;

import static org.junit.Assert.*;

public class SpreadsheetTextualViewTest {
  private SpreadsheetTextualView view;

  private void reset() {
    String fileName = "spreadsheet2.txt";
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

    view = new SpreadsheetTextualView(model, "excel2.txt");
    view.render();
  }

  @Test
  public void testToString() {
    reset();
    String test = "B3 false\nA3 284.000000\nB2 256.000000\nA2 28.000000\nD1 12.000000\n"
                  + "C1 9.000000\nB1 4.000000\nA4 400.000000\nA1 3.000000\n";
    assertEquals(test, view.toString());
    view.render();

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

    SpreadsheetTextualView textNew = new SpreadsheetTextualView(model, fileName);

    assertEquals(test, textNew.toString());
  }
}