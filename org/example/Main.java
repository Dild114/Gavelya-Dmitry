package org.example;

import org.example.animals.*;
import org.example.area.Flying;
import org.example.area.Overland;

public class Main {
  public static void main(String[] args) {
    Camel camel = new Camel();
    camel.walk();
    camel.eat();
    camel.typeAnimal();

    Tiger tiger = new Tiger();
    tiger.eat();
    tiger.walk();
    tiger.typeAnimal();

    Eagle eagle = new Eagle();
    eagle.fly();
    eagle.eat();
    eagle.typeAnimal();

    Dolphin dolphin = new Dolphin();
    dolphin.swim();
    dolphin.eat();
    dolphin.typeAnimal();

    Flying flying = new Flying();
    flying.fly();

    Overland overland = new Overland();
    overland.walk();
  }
}