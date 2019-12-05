package edu.cs3500.spreadsheets.provider.view;

import edu.cs3500.spreadsheets.model.BoolValue;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.StringValue;

/**
 * An abstracted function object for processing a Value to WValue.
 *
 * @param <R> The return type of this function
 */
public interface ValueVisitor<R> {

  R visitBoolean(BoolValue b);

  R visitNumber(DoubleValue d);

  R visitEmpty();

  R visitString(StringValue s);

}
