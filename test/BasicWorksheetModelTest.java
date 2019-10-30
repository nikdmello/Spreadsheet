import org.junit.Test;

import java.util.Map;

import edu.cs3500.spreadsheets.model.BasicWorksheetModel;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.DoubleValue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Represents the tests for the basic worksheet model.
 */
public class BasicWorksheetModelTest {
  private BasicWorksheetModel model = new BasicWorksheetModel();


  @Test
  public void testEmptySpreadsheet() {
    assertTrue(model.getHashtable().isEmpty());
  }

  @Test
  public void testCellsPresentInWorksheet() {
    model.createCell(1,1, new DoubleValue(1));
    model.createCell(1,2, new DoubleValue(2));
    model.createCell(4,2, new DoubleValue(131));

    for (Cell cell : model.getHashtable().values()) {
      assertTrue(model.getHashtable().contains(cell));
    }
  }
}