package org.example.wayOfEating;

import com.sun.security.jgss.GSSUtil;

public abstract class Predator {
  public void typeAnimal() {
    System.out.println("Predator");
  };
  public void printEat() {
    System.out.println("Meat");
  };
  public void eat(String food) {}
}