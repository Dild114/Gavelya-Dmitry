package org.example;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {
  public static List<Integer> sort(String numberSort, List<Integer> array) {
    if (numberSort.equals("0")) {
      //MergeSort merge = new MergeSort();
      //List<Integer> newArray = MergeSort.Sort(array);
      return MergeSort.sort(array);
    }
    if (numberSort.equals("1")) {
      return BubleSort.bubbleSort(array);
    } else {
      System.out.println("Wrong value");
      return new ArrayList<>();
    }
  }

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    List<Integer> array = new ArrayList<>();
    System.out.println("Enter the number of array elements:");
    int n = scan.nextInt();
    System.out.println("Enter the elements:");
    for (int i = 0; i < n; i++) {
      array.add(scan.nextInt());
    }
    System.out.println("Select sorting. MergeSort - 0, BubleSort - 1");
    String numberSort = scan.next();
    System.out.println(sort(numberSort, array));
  }
}