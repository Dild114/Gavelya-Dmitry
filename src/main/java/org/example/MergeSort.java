package org.example;

import org.example.sorter.SortType;
import org.example.sorter.Sorter;

import java.util.*;
import java.util.List;

public class MergeSort extends Sorter {

  public MergeSort(int limit) {
    super(limit);
  }

  @Override
  public List<Integer> sort(List<Integer> list) throws RuntimeException {
    if ((list.size() > limitElement)) {
      throw new IllegalArgumentException("Invalid length");
    }
    List<Integer> newList = CloneList.clone(list);
    Collections.sort(newList);
    return newList;
  }

  @Override
  public SortType type() {
    return SortType.MERGE;
  }
}
