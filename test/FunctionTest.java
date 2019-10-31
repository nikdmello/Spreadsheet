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
 * Represents tests for the boolean Value.
 */
public class FunctionTest {
  BasicWorksheetModel model = new BasicWorksheetModel();

  @Test
  public void evaluateSumTest() {
    model.createCell(1, 1, new Function(FunctionType.SUM,
            new ArrayList<Formula>(Arrays.asList(new DoubleValue(1), new DoubleValue(2)))));
    model.getCellAt(new Coord(1, 1)).evaluateCell();
    assertEquals(3,
            (int) ((Value) (model.getCellAt(new Coord(1, 1)).getFormula())).numberForm());
  }

  @Test
  public void evaluateProdTest() {
    model.createCell(4, 4, new DoubleValue(10));
    model.createCell(4, 5, new DoubleValue(10));
    model.createCell(4, 6,
            new Reference(model,
                    new Coord(4, 4), new Coord(4, 5),
                    new Coord(4, 6)));
    ArrayList<Formula> arrf = new ArrayList<>(Arrays.asList(new DoubleValue(5),
            new DoubleValue(5)));
    model.createCell(3, 4, new Function(FunctionType.SUM, arrf));
    model.createCell(1, 1, new Function(FunctionType.PRODUCT,
            new ArrayList<Formula>(Arrays.asList(model.getCellAt(new Coord(3, 4))
                    .getFormula(), new DoubleValue(10)))));
    model.getCellAt(new Coord(1, 1)).evaluateCell();
    ArrayList<Formula> arrfu = new ArrayList<>(Arrays.asList(model.
            getCellAt(new Coord(4, 6)).getFormula()));
    model.createCell(4, 7, new Function(FunctionType.PRODUCT, arrfu));
    assertEquals(100,
            (int) ((Value) (model.getCellAt(new Coord(1, 1)).getFormula())).numberForm());
    assertEquals(100, (int) model.getCellAt(new Coord(4, 7))
            .getFormula().evaluate().numberForm());
  }

  @Test
  public void evaluateLTTest() {
    model.createCell(1, 1, new Function(FunctionType.LT,
            new ArrayList<Formula>(Arrays.asList(new DoubleValue(1), new DoubleValue(2)))));
    model.getCellAt(new Coord(1, 1)).evaluateCell();
    assertEquals("true",
            (model.getCellAt(new Coord(1, 1)).getFormula()).toString());
  }

  @Test
  public void evaluateCatTest() {
    model.createCell(1, 1, new Function(FunctionType.CAT,
            new ArrayList<Formula>(Arrays.asList(new DoubleValue(1), new DoubleValue(2)))));
    model.getCellAt(new Coord(1, 1)).evaluateCell();
    assertEquals("1.02.0",
            (model.getCellAt(new Coord(1, 1)).getFormula()).toString());
  }
}
