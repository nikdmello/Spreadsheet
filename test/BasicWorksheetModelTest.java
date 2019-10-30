import org.junit.Test;

import edu.cs3500.spreadsheets.model.BasicWorksheetModel;

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
}