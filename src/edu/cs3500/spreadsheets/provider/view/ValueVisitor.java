package edu.cs3500.spreadsheets.provider.view;

import edu.cs3500.spreadsheets.model.BoolValue;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.StringValue;

public interface ValueVisitor<R> {

  R visitBoolean(BoolValue b);

  R visitNumber(DoubleValue d);

  R visitEmpty();

  R visitString(StringValue s);

}
