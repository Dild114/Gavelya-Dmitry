package org.example.animals;

import org.example.area.Waterfowl;
import org.example.wayOfEating.Predator;

public class Dolphin extends Waterfowl implements Predator {
  @Override
  public void swim() {
    System.out.println("Dolphin swim");
  }

  @Override
  public void typeAnimal() {
    System.out.println("Dolphin - waterfowl");
  }

  @Override
  public void eat() {
    System.out.println("Dolphin eat fish");
  }
}
