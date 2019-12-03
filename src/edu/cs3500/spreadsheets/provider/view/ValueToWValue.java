package edu.cs3500.spreadsheets.provider.view;

import edu.cs3500.spreadsheets.model.BoolValue;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.StringValue;

public class ValueToWValue implements ValueVisitor<WValue> {

  @Override
  public WValue visitBoolean(BoolValue b) {
    return new WBoolean(b.booleanForm());
  }

  @Override
  public WValue visitNumber(DoubleValue d) {
    return new WNumber(d.numberForm());
  }

  @Override
  public WValue visitEmpty() {
    return WBlank.INSTANCE;
  }

  @Override
  public WValue visitString(StringValue s) {
    return new WString(s.toString());
  }
}
