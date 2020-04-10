package designPatterns.strategy.design;

public class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void doStrategyThings() {
        this.strategy.algorithmInterface();
    }
}
