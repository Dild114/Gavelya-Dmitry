package org.example;

import com.sun.jdi.IncompatibleThreadStateException;
import org.example.sorter.BubleSort;
import org.example.sorter.MergeSort;
import org.example.sorter.SortType;
import org.example.sorter.SortsManager;
import org.junit.jupiter.api.Test;
import org.opentest4j.IncompleteExecutionException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SortsManagerTest {
  @Test
  void NotFoundAlgorithm() throws Exception {
    MergeSort mergeSort = new MergeSort(3);
    BubleSort bubleSort = new BubleSort(10);
    SortsManager sortsManager = new SortsManager(Arrays.asList(mergeSort, bubleSort));
    assertThrows(IllegalArgumentException.class, () -> sortsManager.sort(Arrays.asList(1, 5, 3), SortType.valueOf("ASD")));
  }

  @Test
  void InvalidLenghth() throws Exception {
    MergeSort mergeSort = new MergeSort(3);
    BubleSort bubleSort = new BubleSort(10);
    SortsManager sortsManager = new SortsManager(Arrays.asList(mergeSort, bubleSort));
    assertThrows(IllegalArgumentException.class, () -> sortsManager.sort(Arrays.asList(1, 5, 4, 2), SortType.MERGE));
  }
}