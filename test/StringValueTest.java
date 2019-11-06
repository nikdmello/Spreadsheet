import org.junit.Test;

import edu.cs3500.spreadsheets.model.StringValue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Represents tests for the string Value.
 */
public class StringValueTest {
  private StringValue sv = new StringValue("Hi");

  @Test
  public void evaluate() {
    assertEquals(sv.evaluate(), sv);
  }

  @Test
  public void type() {
    assertEquals("string", sv.type());
  }

  @Test
  public void isNumeric() {
    assertFalse(sv.isNumeric());
  }

  @Test
  public void numberForm() {
    assertEquals(0, (int) sv.numberForm());
  }
}
