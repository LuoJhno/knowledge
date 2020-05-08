package designPatterns.decorator.design;

public class ConcreteDecoratorA implements Decorator{
    public ConcreteDecoratorA(Component component){
        this.component = component;
    }

    @Override
    public void methodA() {
       System.out.println("decoratorA A");
       this.component.methodA();
        
    }

    @Override
    public void methodB() {
        System.out.println("decoratorA B");
        this.component.methodB();
    }
}