package designPatterns.adapter.design;

public class Adapter implements Target {
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void methodA() {
        this.adaptee.methodB();
    }

}