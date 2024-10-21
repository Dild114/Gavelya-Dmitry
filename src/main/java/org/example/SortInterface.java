package org.example;

import java.util.List;

public interface SortInterface {
  List<Integer> sort(List<Integer> list) throws RuntimeException;

  int getLimit();

  SortType type();

  void setLimit(int limit);
}
