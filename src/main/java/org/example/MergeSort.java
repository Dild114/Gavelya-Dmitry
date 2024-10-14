package org.example;

import java.util.*;
import java.util.List;

public class MergeSort {
  private List<Integer> list = new ArrayList<>();
  private static int MAXIMUM_NUMBER_OF_ELEMENTS = 99;

  public MergeSort() {
  }
  public MergeSort(int MAXIMUM_NUMBER_OF_ELEMENTS) {
    this.MAXIMUM_NUMBER_OF_ELEMENTS = MAXIMUM_NUMBER_OF_ELEMENTS;
  }
  private static List<Integer> clons(List<Integer> array) {
    List<Integer> newArray = new ArrayList<>();
    for (Integer i : array) {
      newArray.add(i);
    }
    return newArray;
  }

  public static List<Integer> sort(List<Integer> list) throws RuntimeException {
    if (list.size() > MAXIMUM_NUMBER_OF_ELEMENTS) {
      throw new RuntimeException("Invalid length");
    }

    List<Integer> newList = clons(list);
    Collections.sort(newList);
    return newList;
  }
}
