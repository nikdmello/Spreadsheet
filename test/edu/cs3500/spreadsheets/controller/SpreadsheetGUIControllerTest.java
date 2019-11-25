package edu.cs3500.spreadsheets.controller;

import org.junit.Test;

import edu.cs3500.spreadsheets.model.BasicWorksheetModel;
import edu.cs3500.spreadsheets.model.BoolValue;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.StringValue;
import edu.cs3500.spreadsheets.model.Worksheet;

import static org.junit.Assert.assertEquals;

public class SpreadsheetGUIControllerTest {
  Worksheet sheet = new BasicWorksheetModel();
  SpreadsheetController controller = new SpreadsheetGUIController(sheet);

  @Test
  public void cellRequestTest() {
    controller.launchEditor();
    controller.cellRequest(1, 1, "5");
    controller.cellRequest(1, 2, "2");
    controller.cellRequest(1, 3, "true");
    controller.cellRequest(1, 4, "(SUM A1 B1)");
    assertEquals(new DoubleValue(5), sheet.getCellAt(new Coord(1, 1)).getFormula());
    assertEquals(new BoolValue(true), sheet.getCellAt(new Coord(3, 1)).getFormula());
    assertEquals(new DoubleValue(7), sheet.getCellAt(new Coord(4, 1)).getFormula());
    controller.cellRequest(1, 6, "(SUM A1 A2");
    assertEquals(new StringValue("Error"), sheet.getCellAt(new Coord(6, 1)).getFormula());
  }

  @Test
  public void delCellTest() {
    controller.delCell(1, 1);
    controller.delCell(1, 2);
    controller.delCell(1, 3);
    controller.delCell(1, 4);
    controller.delCell(1, 6);
    assertEquals(sheet.getHashtable().size(), 0);
  }
}