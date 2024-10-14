package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {
  @Test
  void sort() {
    List<Integer> array = Arrays.asList(3, 5, 1, 45, 7, 7, 1, 6, 7, 3, 6);
    MergeSort mergeSort = new MergeSort(12);
    array = mergeSort.sort(array);
    assertEquals(array, Arrays.asList(1, 1, 3, 3, 5, 6, 6, 7, 7, 7, 45));
  }

  @Test
  void sortWithMuchElement() {
    List<Integer> array = Arrays.asList(3, 5, 1, 45, 7, 7, 1, 6, 7, 3, 6);
    MergeSort mergeSort = new MergeSort(3);
    RuntimeException exception = assertThrows(RuntimeException.class, () -> mergeSort.sort(array));
    assertEquals("Invalid length", exception.getMessage());
  }
}