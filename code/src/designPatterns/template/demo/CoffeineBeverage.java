package designPatterns.template.demo;

public abstract class CoffeineBeverage {
    final void prepareRecipe() {
        boilWater();
        brew();
        poureInCup();
        addCondiments();
    }

    abstract void brew();

    abstract void addCondiments();

    void boilWater() {
        System.out.println("boilWater");
    }

    void poureInCup() {
        System.out.println("pourInCup");
    }
}