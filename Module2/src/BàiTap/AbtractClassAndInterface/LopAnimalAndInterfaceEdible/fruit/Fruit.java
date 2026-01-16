package AbtractClassAndInterface.LopAnimalAndInterfaceEdible.fruit;

import AbtractClassAndInterface.LopAnimalAndInterfaceEdible.edible.Edible;

public abstract class Fruit implements Edible {
    @Override
    public  String howToEat() {
        return "Apple could be slided";
    }
}
