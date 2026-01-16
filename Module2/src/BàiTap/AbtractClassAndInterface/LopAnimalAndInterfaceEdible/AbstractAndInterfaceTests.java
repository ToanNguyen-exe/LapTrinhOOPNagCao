package AbtractClassAndInterface.LopAnimalAndInterfaceEdible;

import AbtractClassAndInterface.LopAnimalAndInterfaceEdible.edible.Edible;
import AbtractClassAndInterface.LopAnimalAndInterfaceEdible.fruit.Fruit;
import AbtractClassAndInterface.LopAnimalAndInterfaceEdible.fruit.Apple;
import AbtractClassAndInterface.LopAnimalAndInterfaceEdible.fruit.Orange;
public class AbstractAndInterfaceTests {
    public static void main(String[] args) {
        Edible[] edibles = new Edible[2];
        edibles[0] = new Apple();
        edibles[1] = new Orange();
        for (Edible edible : edibles) {
            System.out.println(edible.howToEat());
        }
        Animal[] animals = new Animal[2];
        animals[0] = new Tiger();
        animals[1] = new Chicken();
        for (Animal animal : animals) {
            System.out.println(animal.makeSound());

            if (animal instanceof Chicken) {
                Edible edible = (Edible) animal;
                System.out.println(edible.howToEat());
            }
        }
    }
}
