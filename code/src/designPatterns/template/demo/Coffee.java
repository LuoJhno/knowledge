package designPatterns.template.demo;

public class Coffee extends CoffeineBeverage{

    @Override
    protected void brew() {
        System.out.println("Coffee.brew");

    }

    @Override
    protected void addCondiments() {
        System.out.println("Coffee.addcondiments");

    }
    
}