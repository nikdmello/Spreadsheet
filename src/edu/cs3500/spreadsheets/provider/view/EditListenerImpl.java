package edu.cs3500.spreadsheets.provider.view;

import java.io.IOException;

import edu.cs3500.spreadsheets.controller.ControllerViewRequester;

/**
 * The edit listener that allows actions to be taken when a key is pressed.
 */
public class EditListenerImpl implements WorksheetView.EditListener {
  private ControllerViewRequester cvr;

  EditListenerImpl(ControllerViewRequester cvr) {
    this.cvr = cvr;
  }

  @Override
  public void onEdit(int col, int row, String contents) throws IOException {
    cvr.requestCell(row, col, contents);
  }
}
