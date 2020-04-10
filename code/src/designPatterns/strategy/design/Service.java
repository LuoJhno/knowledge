package designPatterns.strategy.design;

public class Service {
    public static void main(String[] args) {
        Strategy strategy = new ConcreteStrategyA();
        Context context = new Context(strategy);
        context.doStrategyThings();
    }
}
