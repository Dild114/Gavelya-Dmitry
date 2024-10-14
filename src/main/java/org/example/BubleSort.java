package org.example;

import java.util.ArrayList;
import java.util.List;

public class BubleSort {
  private static int MAXIMUM_NUMBER_OF_ELEMENTS = 50;

  public BubleSort() {
  }

  public BubleSort(int MAXIMUM_NUMBER_OF_ELEMENTS) {
    this.MAXIMUM_NUMBER_OF_ELEMENTS = MAXIMUM_NUMBER_OF_ELEMENTS;
  }

  private static List<Integer> clons(List<Integer> array) {
    List<Integer> newArray = new ArrayList<>();
    for (Integer i : array) {
      newArray.add(i);
    }
    return newArray;
  }

  public static List<Integer> bubbleSort(List<Integer> arr) throws RuntimeException {
    if (arr.size() > MAXIMUM_NUMBER_OF_ELEMENTS) {
      throw new RuntimeException("Invalid length");
    }
    List<Integer> newList = clons(arr);
    for (int i = 0; i < newList.size(); i++) {
      for (int j = i + 1; j < newList.size(); j++) {
        if (newList.get(i) > newList.get(j)) {
          int temp = newList.get(i);
          newList.set(i, newList.get(j));
          newList.set(j, temp);
        }
      }
    }
    return newList;
  }
}