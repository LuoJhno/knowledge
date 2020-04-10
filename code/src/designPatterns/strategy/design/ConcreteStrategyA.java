package designPatterns.strategy.design;

public class ConcreteStrategyA implements Strategy {
    @Override
    public void algorithmInterface() {
        //do A things
        System.out.println("A");
    }
}

class ConcreteStrategyB implements Strategy {
    @Override
    public void algorithmInterface() {
        //do B things
        System.out.println("B");
    }
}

class ConcreteStrategyC implements Strategy {
    @Override
    public void algorithmInterface() {
        //do C things
        System.out.println("C");
    }
}
