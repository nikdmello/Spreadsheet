package edu.cs3500.spreadsheets.model;

public class StringContainer<String> implements ValueContainer{
  String s;

  public StringContainer(String s){
    this.s = s;
  }

  @Override
  public String getValue() {
    return this.s;
  }
}
