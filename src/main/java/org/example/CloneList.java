package org.example;

import java.util.ArrayList;
import java.util.List;

public class CloneList {
  public static List<Integer> clone(List<Integer> list) {
    List<Integer> newList = new ArrayList<>();
    for (Integer elemnt : list) {
      newList.add(elemnt);
    }
    return newList;
  }
}
