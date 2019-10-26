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

  @Override
  public <R> R accept(ValueVisitor<R> visitor) {
    return visitor.visitString(this.s);
  }
}
