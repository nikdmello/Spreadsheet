package edu.cs3500.spreadsheets.provider.view;

import java.io.IOException;

import edu.cs3500.spreadsheets.model.BasicWorksheetModel;


public class ProviderControlAdapter implements WorksheetController {
  WorksheetView view;

  public ProviderControlAdapter() {
    this.view = new VisualWorksheetView(new ProviderModelAdapter(new BasicWorksheetModel()));
  }

  @Override
  public void start() throws IOException {
    view.render();
  }
}
