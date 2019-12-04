package edu.cs3500.spreadsheets.provider.view;

import java.io.IOException;

import edu.cs3500.spreadsheets.controller.ControllerViewRequester;

public class EditListenerImpl implements WorksheetView.EditListener {
  ControllerViewRequester cvr;

  public EditListenerImpl(ControllerViewRequester cvr) {
    this.cvr = cvr;
  }

  @Override
  public void onEdit(int col, int row, String contents) throws IOException {
    cvr.requestCell(row, col, contents);
  }
}
