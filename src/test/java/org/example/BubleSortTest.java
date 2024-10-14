package org.example;

import org.example.sorter.BubleSort;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BubleSortTest {

  @Test
  void sort() {
    List<Integer> array = Arrays.asList(3, 5, 1, 45, 7, 7, 1, 6, 7, 3, 6);
    BubleSort bubleSort = new BubleSort(12);
    array = bubleSort.sort(array);
    assertEquals(array, Arrays.asList(1, 1, 3, 3, 5, 6, 6, 7, 7, 7, 45));
  }

  @Test
  void sortWithException() {
    List<Integer> array = Arrays.asList(3, 5, 1, 45, 7, 7, 1, 6, 7, 3, 6);
    BubleSort bubleSort = new BubleSort(4);
    RuntimeException exception = assertThrows(RuntimeException.class, () -> bubleSort.sort(array));
    assertEquals("Invalid length", exception.getMessage());
  }
}
