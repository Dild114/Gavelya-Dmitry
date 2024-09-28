package org.example.animals;

import org.example.area.Overland;
import org.example.wayOfEating.Herbivorous;
import org.example.wayOfEating.Predator;

public class Tiger extends Overland implements Herbivorous {
  @Override
  public void typeAnimal() {
    System.out.println("Tiger - predator");
  }
  @Override
  public void walk() {
    System.out.println("Tiger walk");
  }
  @Override
  public void eat() {
    System.out.println("Tiger eat beef");
  }
}