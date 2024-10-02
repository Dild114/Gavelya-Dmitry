package org.example;

import org.example.animals.*;
import org.example.area.Flying;
import org.example.area.Overland;

public class Main {
  public static void main(String[] args) {
    Camel camel = new Camel();
    camel.walk();
    camel.printEat();
    camel.eat("Grass");
    camel.typeAnimal();

    Tiger tiger = new Tiger();
    tiger.printEat();
    tiger.walk();
    tiger.typeAnimal();

    Eagle eagle = new Eagle();
    eagle.fly();
    eagle.printEat();
    eagle.typeAnimal();

    Dolphin dolphin = new Dolphin();
    dolphin.swim();
    dolphin.printEat();
    dolphin.typeAnimal();
  }
}