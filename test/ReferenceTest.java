import org.junit.Test;

import edu.cs3500.spreadsheets.model.BasicWorksheetModel;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.Reference;

import static org.junit.Assert.assertEquals;

/**
 * Represents tests for Reference.
 */
public class ReferenceTest {
  private BasicWorksheetModel model;

  private void reset() {
    model = new BasicWorksheetModel();
    model.createCell(1, 1, new DoubleValue(2));
    model.createCell(1, 2, new Reference(model,
            new Coord(1, 1), new Coord(1, 2)));
    model.createCell(1, 3, new Reference(model,
            new Coord(1, 4), new Coord(1, 3)));
    model.createCell(1, 4, new Reference(model,
            new Coord(1, 3), new Coord(1, 4)));
    model.createCell(1, 5, new Reference(model,
            new Coord(2, 1), new Coord(2, 3)));
  }

  @Test
  public void evaluateTest() {
    reset();
    assertEquals(2,
            (int) model.getCellAt(new Coord(1, 2)).getFormula().evaluate().numberForm());
  }

  @Test(expected = IllegalArgumentException.class)
  public void evalTestNothingToEval() {
    reset();
    model.getCellAt(new Coord(1, 5)).evaluateCell();
  }

  @Test
  public void testSelfReferential() {
    reset();
    try {
      model.getCellAt(new Coord(1, 3)).evaluateCell();
    } catch (IllegalArgumentException ie) {
      assertEquals("Cell cannot reference itself", ie.getMessage());
    }
  }
}
