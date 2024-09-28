package org.example.animals;

import org.example.area.Flying;
import org.example.wayOfEating.Predator;

public class Eagle extends Flying implements Predator {
  @Override
  public void typeAnimal() {
    System.out.println("Eagle - predator");
  }

  @Override
  public void eat() {
    System.out.println("Eagle eat meat");
  }

  @Override
  public void fly() {
    System.out.println("Eagle fly");
  }
}
