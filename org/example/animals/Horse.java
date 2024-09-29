package org.example.animals;

import org.example.area.Overland;
import org.example.wayOfEating.Herbivorous;

public class Horse extends Overland implements Herbivorous {
  @Override
  public void eat(String food) {
    if (food.equals("grass") || food.equals("Grass")) {
      System.out.println("Horse eat grass");
    } else {
      System.out.println("Horse doesn't eat it");
    }
  }

  @Override
  public void typeAnimal() {
    System.out.println("Horse eat grass");
  }
  @Override
  public void walk() {
  System.out.println("Horse walk");
  }
  @Override
  public void printEat() {
      System.out.println("Horse eat grass");
    }
}
