package edu.cs3500.spreadsheets.provider.view;


/**
 * Utility class that converts WValues into String representations.
 */
public final class SingleStringView {

  private SingleStringView() {
    //
  }

  /**
   * Utility method that converts WValues into String representations.
   *
   * @param val the value to convert to a string
   * @return the stringified value
   */
  public static String valueToString(WValue val) {
    Validation.requireNonNull(val, "Value");
    return val.accept(new WValueVisitor<>() {
      @Override
      public String visitBoolean(boolean b) {
        return String.valueOf(b);
      }

      @Override
      public String visitNumber(double d) {
        return String.format("%f", d);
      }

      @Override
      public String visitString(String s) {
        return "\"" + s.replace("\\", "\\\\")
                .replace("\"", "\\\"") + "\"";
      }

      @Override
      public String visitBlank() {
        return "";
      }
    });
  }
}
