package edu.cs3500.spreadsheets.provider.view;

import java.io.IOException;

import edu.cs3500.spreadsheets.controller.ControllerViewRequester;
import edu.cs3500.spreadsheets.view.GUIView;
import edu.cs3500.spreadsheets.view.ModelToView;

/**
 * Adapts the provider view to our model.
 */
public class ProviderViewAdapter extends GUIView {
  WorksheetView pview;

  /**
   * Creates and initializes the view.
   *
   * @param mtt The model to view translator
   * @param cvr the view to controller translator
   */
  public ProviderViewAdapter(ModelToView mtt, ControllerViewRequester cvr, WorksheetView pv) {
    super(mtt, cvr);
    this.pview = pv;
    super.dispose();
  }

  @Override
  public void render() {
    try {
      pview.render();
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Something went wrong!");
    }
  }

}
