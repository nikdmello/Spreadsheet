import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import edu.cs3500.spreadsheets.model.BasicWorksheetModel;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.Function;
import edu.cs3500.spreadsheets.model.FunctionType;
import edu.cs3500.spreadsheets.model.Reference;
import edu.cs3500.spreadsheets.model.Value;

import static org.junit.Assert.assertEquals;

/**
 * Represents tests for the Cell class.
 */
public class CellTest {
  private BasicWorksheetModel model = new BasicWorksheetModel();

  @Test
  public void evaluateCell() {
    model.createCell(1, 1, new Function(FunctionType.SUM,
            new ArrayList<>(Arrays.asList(new DoubleValue(1), new DoubleValue(2)))));
    model.getCellAt(new Coord(1, 1)).evaluateCell();
    model.createCell(1, 2,
            new Reference(model, new Coord(1, 1), new Coord(1, 2)));
    model.getCellAt(new Coord(1, 2)).evaluateCell();
    assertEquals(3,
            (int) ((Value) (model.getCellAt(new Coord(1, 1)).getFormula())).numberForm());
    assertEquals(3,
            (int) ((Value) (model.getCellAt(new Coord(1, 2)).getFormula())).numberForm());
  }

  @Test
  public void getFormula() {
    model.createCell(1, 1, new Function(FunctionType.SUM,
            new ArrayList<>(Arrays.asList(new DoubleValue(1), new DoubleValue(2)))));
    assertEquals("func", model.getCellAt(new Coord(1, 1)).getFormula().type());
  }
}
