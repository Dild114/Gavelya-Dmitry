package org.example;

abstract public class Sorter implements SortInterface {
  protected int limitElement;

  public Sorter(int limit) {
    this.limitElement = limit;
  }

  @Override
  public int getLimit() {
    return limitElement;
  }

  @Override
  public void setLimit(int limit) {
    if (limit <= 0) {
      throw new IllegalArgumentException("Wrong Value");
    }
    this.limitElement = limit;
  }
}
