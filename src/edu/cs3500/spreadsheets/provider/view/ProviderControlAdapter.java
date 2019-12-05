package edu.cs3500.spreadsheets.provider.view;

import java.io.IOException;

import edu.cs3500.spreadsheets.controller.ControllerViewRequester;
import edu.cs3500.spreadsheets.controller.SpreadsheetController;
import edu.cs3500.spreadsheets.controller.SpreadsheetGUIController;
import edu.cs3500.spreadsheets.model.BasicWorksheetModel;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.view.ModelToTable;

/**
 * An adapter for the worksheet.
 */
public class ProviderControlAdapter implements WorksheetController {
  private WorksheetView view;

  /**
   * Constructs a worksheet.
   */
  public ProviderControlAdapter() {
    Worksheet sheet = new BasicWorksheetModel();
    this.view = new VisualWorksheetView(new ProviderModelAdapter(sheet));
    SpreadsheetController controller = new SpreadsheetGUIController(sheet);
    ControllerViewRequester cvr = new ControllerViewRequester(controller);
    EditListenerImpl eli = new EditListenerImpl(cvr);
    this.view.setEditListener(eli);
    ProviderViewAdapter pva = new ProviderViewAdapter(new ModelToTable(sheet), cvr, view);
    controller.setView(pva);
  }

  @Override
  public void start() throws IOException {
    view.render();
  }
}
