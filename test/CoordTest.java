import org.junit.Test;

import edu.cs3500.spreadsheets.model.Coord;

import static org.junit.Assert.assertEquals;

public class CoordTest {

  @Test
  public void colNameToIndexTest() {
    assertEquals(1, Coord.colNameToIndex("A"));
  }

  @Test
  public void colIndexToNameTest() {
    assertEquals("A", Coord.colIndexToName(1));
  }
}
