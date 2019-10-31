import org.junit.Test;

import edu.cs3500.spreadsheets.model.DoubleValue;

import static org.junit.Assert.assertEquals;

public class DoubleValueTest {
  DoubleValue dv = new DoubleValue(2);

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
    assertEquals(true, dv.isNumeric());
  }

  @Test
  public void numberForm() {
    assertEquals(2, (int)dv.numberForm());
  }
}
