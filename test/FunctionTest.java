import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import edu.cs3500.spreadsheets.model.BasicWorksheetModel;
import edu.cs3500.spreadsheets.model.BoolValue;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.Function;
import edu.cs3500.spreadsheets.model.FunctionType;
import edu.cs3500.spreadsheets.model.Reference;
import edu.cs3500.spreadsheets.model.StringValue;
import edu.cs3500.spreadsheets.model.Value;

import static org.junit.Assert.assertEquals;

/**
 * Represents tests for the boolean Value.
 */
public class FunctionTest {
  private BasicWorksheetModel model = new BasicWorksheetModel();

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
    ArrayList<Formula> arrfu = new ArrayList<>(Arrays.asList(model.getCellAt(
            new Coord(4, 6)).getFormula()));
    model.createCell(4, 7, new Function(FunctionType.PRODUCT, arrfu));
    assertEquals(100,
            (int) ((Value) (model.getCellAt(new Coord(1, 1)).getFormula())).numberForm());
    assertEquals(100, (int) model.getCellAt(new Coord(4, 7))
            .getFormula().evaluate().numberForm());
  }

  @Test
  public void evaluateLessThanTest() {
    model.createCell(1, 1, new Function(FunctionType.LT,
            new ArrayList<Formula>(Arrays.asList(new DoubleValue(1), new DoubleValue(2)))));
    model.getCellAt(new Coord(1, 1)).evaluateCell();
    assertEquals("true",
            (model.getCellAt(new Coord(1, 1)).getFormula()).toString());
  }

  @Test
  public void evaluateConcatTest() {
    model.createCell(1, 1, new Function(FunctionType.CAT,
            new ArrayList<Formula>(Arrays.asList(new DoubleValue(1), new DoubleValue(2)))));
    model.getCellAt(new Coord(1, 1)).evaluateCell();
    assertEquals("1.02.0",
            (model.getCellAt(new Coord(1, 1)).getFormula()).toString());
  }

  @Test
  public void valueToStringTest() {
    model.createCell(1,1, new DoubleValue(10));
    model.createCell(1,2, new StringValue("hi"));
    model.createCell(1,3, new BoolValue(true));

    assertEquals("10.0", model.getCellAt(new Coord(1,1)).toString());
    assertEquals("hi", model.getCellAt(new Coord(1,2)).toString());
    assertEquals("true", model.getCellAt(new Coord(1,3)).toString());
  }

  @Test
  public void sumOfSameCellTest() {
    Coord c = new Coord(1,1);
    Coord c2 = new Coord(1,2);
    model.createCell(1,1, new DoubleValue(10));
    model.createCell(1, 2, new Function(FunctionType.SUM,
            new ArrayList<>(Arrays.asList(model.getCellAt(c).getFormula(),
                    model.getCellAt(c).getFormula()))).evaluate());
    assertEquals("20.0", model.getCellAt(c2).getFormula().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void sumOfDifferentTypesTest() {
    Coord c = new Coord(1,1);
    Coord c2 = new Coord(1,2);
    Coord c3 = new Coord(1,3);
    model.createCell(1,1, new DoubleValue(10));
    model.createCell(1,2, new StringValue("hi"));
    model.createCell(1, 3, new Function(FunctionType.SUM,
            new ArrayList<>(Arrays.asList(model.getCellAt(c).getFormula(),
                    model.getCellAt(c2).getFormula()))).evaluate());
  }

  @Test
  public void sumRegionOfCellsTest() {
    model.createCell(1,1, new DoubleValue(3));
    model.createCell(1,2, new DoubleValue(4));
    model.createCell(1,3, new DoubleValue(9));
    model.createCell(1,4, new DoubleValue(12));
    model.createCell(1,5, new Reference(model, new Coord(1,1),
            new Coord(1,4), new Coord(1,5)));

    Reference ref = new Reference(model, new Coord(1,1), new Coord(1,4),
            new Coord(1,5));
    Function sumRegion = new Function(FunctionType.SUM, new ArrayList<>(Arrays.asList(ref)));

    assertEquals("28.0", sumRegion.evaluate().toString());
  }
}
