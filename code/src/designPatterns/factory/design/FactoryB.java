package designPatterns.factory.design;

public class FactoryB implements Factory {

    @Override
    public Product ManuFacture() {
        return new ProductB();
    }

}