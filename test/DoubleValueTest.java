import org.junit.Test;

import edu.cs3500.spreadsheets.model.DoubleValue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Represents tests for the double Value.
 */
public class DoubleValueTest {
  private DoubleValue dv = new DoubleValue(2);

  @Test
  public void evaluate() {
    assertEquals(dv, dv.evaluate());
  }

  @Test
  public void type() {
    assertEquals("double", dv.type());
  }

  @Test
  public void isNumeric() {
    assertTrue(dv.isNumeric());
  }

  @Test
  public void numberForm() {
    assertEquals(2, (int) dv.numberForm());
  }
}
