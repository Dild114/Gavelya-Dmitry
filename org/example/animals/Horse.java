package org.example.animals;

import org.example.area.Overland;
import org.example.wayOfEating.Herbivorous;

public class Horse extends Overland implements Herbivorous {
  @Override
  public void eat() {
    System.out.println("Horse eat grass");
  }

  @Override
  public void typeAnimal() {
    System.out.println("Horse - Herbivorous");
  }

  @Override
  public void walk() {
  System.out.println("Horse walk");
  }
}
