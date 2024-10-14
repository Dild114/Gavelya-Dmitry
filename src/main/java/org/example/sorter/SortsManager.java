package org.example.sorter;

import java.util.List;

public class SortsManager {
  private final List<Sorter> listSorting;

  public SortsManager(List<Sorter> listSorting) {
    this.listSorting = listSorting;
  }

  public List<Integer> sort(List<Integer> list, SortType type) throws Exception {
    for (Sorter sorter : listSorting) {
      if (sorter.type().equals(type)) {
        try {
          return sorter.sort(list);
        } catch (Exception exception) {
          // We don't throw an exception because a suitable algorithm may be found later.
          System.out.println(exception + " for " + type);
        }
      }
    }
    throw new IllegalArgumentException("Not found");
  }
}
