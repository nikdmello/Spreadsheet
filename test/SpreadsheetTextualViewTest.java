import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import edu.cs3500.spreadsheets.model.BasicWorksheetBuilder;
import edu.cs3500.spreadsheets.model.BasicWorksheetModel;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.view.SpreadsheetTextualView;
import edu.cs3500.spreadsheets.view.SpreadsheetView;

import static org.junit.Assert.*;

public class SpreadsheetTextualViewTest {
  SpreadsheetTextualView view;

  public void reset(){
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

    view = new SpreadsheetTextualView(model);
  }

  @Test
  public void render() {
    reset();
    try {
      view.render();
    } catch (IOException e){
      throw new IllegalArgumentException("Bad");
    }
  }

  @Test
  public void testToString() {

  }
}