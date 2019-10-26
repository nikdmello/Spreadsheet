package edu.cs3500.spreadsheets.model;

public class StringValue implements Value {
  private String s;

  StringValue(String s){
    this.s = s;
  }

  @Override
  public Value evaluate() {
    return this;
  }
}
