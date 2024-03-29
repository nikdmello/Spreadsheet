import org.junit.Test;

import edu.cs3500.spreadsheets.model.BoolValue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Represents tests for the boolean Value.
 */
public class BoolValueTest {
  private BoolValue bv = new BoolValue(true);

  @Test
  public void evaluateTest() {
    assertEquals(bv, bv.evaluate());
  }

  @Test
  public void type() {
    assertEquals("bool", bv.type());
  }

  @Test
  public void isNumeric() {
    assertFalse(bv.isNumeric());
  }

  @Test
  public void numberForm() {
    assertEquals(0, (int) bv.numberForm());
  }
}
