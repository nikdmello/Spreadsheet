import org.junit.Test;

import edu.cs3500.spreadsheets.model.BasicWorksheetModel;
import edu.cs3500.spreadsheets.model.BoolValue;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.Reference;
import edu.cs3500.spreadsheets.model.StringValue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
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
    model.createCell(3, 4, new DoubleValue(1));
    model.createCell(99, 3, new DoubleValue(2));
    model.createCell(4, 2, new DoubleValue(131));

    assertEquals(3, model.getHashtable().size());

    for (Cell cell : model.getHashtable().values()) {
      assertTrue(model.getHashtable().contains(cell));
    }
  }

  @Test
  public void testCellNumericValue() {
    model.createCell(1, 1, new DoubleValue(42));

    Cell c = model.getCellAt(new Coord(1, 1));

    assertEquals(c.getFormula(), new DoubleValue(42));
  }

  @Test
  public void testCellBooleanValue() {
    model.createCell(17, 1, new BoolValue(true));

    Cell c = model.getCellAt(new Coord(17, 1));

    assertEquals(c.getFormula(), new BoolValue(true));
  }

  @Test
  public void testCellStringValue() {
    model.createCell(858, 8484, new StringValue("ayyy lmao"));

    Cell c = model.getCellAt(new Coord(858, 8484));

    assertEquals(c.getFormula(), new StringValue("ayyy lmao"));
  }


  @Test
  public void testGetCellAt() {
    Cell cell = model.getCellAt(new Coord(3, 4));
    assertEquals(model.getHashtable().get(new Coord(3, 4)), cell);
  }

  @Test
  public void testCellEmpty() {
    assertNull(model.getHashtable().get(new Coord(10, 10)));
  }

  @Test
  public void testDeleteCell() {
    model.deleteCellAt(new Coord(3, 4));
    assertFalse(model.getHashtable().contains(new Coord(3, 4)));
  }

  @Test
  public void testEvalAll() {
    assertNull(model.evalAll());
  }

  @Test
  public void testEvalAllError() {
    model.createCell(2, 7,
            new Reference(model, new Coord(9, 8), new Coord(102, 309)));
    assertEquals(new Coord(2, 7), model.evalAll());
  }

}
