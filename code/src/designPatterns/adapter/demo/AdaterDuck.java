package designPatterns.adapter.demo;

public class AdaterDuck implements Duck {
    private Turkey turkey;

    public AdaterDuck(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        this.turkey.gobble();

    }

}