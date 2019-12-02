package edu.cs3500.spreadsheets.provider.view;

import java.io.IOException;

/**
 * Represents a view of a worksheet.
 */
public interface WorksheetView {

  /**
   * Sets the listener that is called when a cell was requested to be edited by the user.
   *
   * @param listener the listener to call
   */
  void setEditListener(EditListener listener);

  /**
   * Renders the view to the implemented output.
   *
   * @throws IOException when there was an exception while outputting
   */
  void render() throws IOException;

  /**
   * Represents a listener of edit events.
   */
  interface EditListener {

    /**
     * When an edit event occurs.
     *
     * @param col      the column of the cell that was edited
     * @param row      the row of the cell that was edited
     * @param contents the new contents of the edited cell
     * @throws IOException              when there was an exception rendering, if implementations
     *                                  choose to render in this listener
     * @throws IllegalArgumentException when an error occurs setting the new contents (i.e. invalid
     *                                  contents)
     */
    void onEdit(int col, int row, String contents) throws IOException;
  }
}
