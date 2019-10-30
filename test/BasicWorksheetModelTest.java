import org.junit.Test;

import edu.cs3500.spreadsheets.model.BasicWorksheetModel;

import static org.junit.Assert.*;


public class BasicWorksheetModelTest {
  private BasicWorksheetModel model = new BasicWorksheetModel();

  @Test
  public void initGrid() {
//    model.initGrid();

    assertTrue(model.getHashtable().isEmpty());
  }
}