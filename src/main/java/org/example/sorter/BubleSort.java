package org.example.sorter;
import org.example.CloneList;

import java.util.List;

public class BubleSort extends Sorter implements SortInterface {
  public BubleSort(int limit) {
    super(limit);
  }

  @Override
  public List<Integer> sort(List<Integer> arr) throws RuntimeException {
    if ((arr.size() > limitElement) || (arr.size() <= 0)) {
      throw new RuntimeException("Invalid length");
    }
    List<Integer> newList = CloneList.clone(arr);
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

  @Override
  public int getLimit() {
    return limitElement;
  }

  @Override
  public SortType type() {
    return SortType.BUBBLE;
  }

  @Override
  public void setLimit(int limit) {
    limitElement = limit;
  }
}