package org.example.animals;

import org.example.area.Overland;
import org.example.wayOfEating.Herbivorous;

public class Camel extends Overland implements Herbivorous{
  @Override
  public void walk() {
    System.out.println("Camel walk");
  }

  @Override
  public void eat() {
    System.out.println("Camel eat grass");
  }

  @Override
  public void typeAnimal() {
    System.out.println("Camel - herbivorous");
  }
}
