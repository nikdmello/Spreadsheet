package edu.cs3500.spreadsheets.provider.view;

import java.util.HashMap;
import java.util.Map;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;

public class ProviderModelAdapter implements ROWorksheet {
  private Worksheet sheet;

  ProviderModelAdapter(Worksheet sheet) {
    this.sheet = sheet;
  }

  @Override
  public String get(int col, int row) {
    if (sheet.getHashtable().contains(new Coord(col, row))) {
      return sheet.getCellAt(new Coord(col, row)).getFormula().toString();
    }
    return "";
  }

  @Override
  public Map<Coord, String> getAll() {
    HashMap<Coord, String> ret = new HashMap<>();
    for (Map.Entry<Coord, Cell> e : sheet.getHashtable().entrySet()) {
      ret.put(e.getKey(), e.getValue().getFormula().toString());
    }
    return ret;
  }

  @Override
  public WValue evaluate(int col, int row) {
    Coord c = new Coord(col, row);
    if (sheet.getHashtable().containsKey(c)) {
      return sheet.getCellAt(c).getFormula().evaluate().acceptV(new ValueToWValue());
    }
    return WBlank.INSTANCE;
  }
}
