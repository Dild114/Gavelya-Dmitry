package org.example;

import org.example.BubleSort;
import org.example.MergeSort;
import org.example.SortType;
import org.example.SortsManager;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws Exception {
    Scanner scan = new Scanner(System.in);
    List<Integer> newList;
    MergeSort mergeSort = new MergeSort(3);
    BubleSort bubleSort = new BubleSort(3);
    List<Integer> array = new ArrayList<>();
    System.out.println("Enter the number of array elements:");
    int n = scan.nextInt();
    System.out.println("Enter the elements:");
    for (int i = 0; i < n; i++) {
      array.add(scan.nextInt());
    }
    System.out.println("Select sorting:");
    for (SortType type : SortType.values()) {
      System.out.println(type);
    }
    String numberSort = scan.next();
    SortsManager sortsManager = new SortsManager(Arrays.asList(mergeSort, bubleSort));
    newList = sortsManager.sort(array, SortType.valueOf(numberSort));

    for (Integer element : newList) {
      System.out.print(element + " ");
    }
  }
}