package designPatterns.factory.design;

public class FactoryA implements Factory{

    @Override
    public Product ManuFacture() {
        return new ProductA();
    }
    
}