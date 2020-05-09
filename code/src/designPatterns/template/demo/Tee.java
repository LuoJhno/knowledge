package designPatterns.template.demo;

public class Tee extends CoffeineBeverage{

    @Override
    void brew() {
       System.out.println("tee.brew");

    }

    @Override
    void addCondiments() {
        System.out.println("tee.addCondiments");

    }
    
}