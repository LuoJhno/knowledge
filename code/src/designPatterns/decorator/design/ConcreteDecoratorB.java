package designPatterns.decorator.design;

public class ConcreteDecoratorB implements Decorator{
    public ConcreteDecoratorB(Component component){
        this.component = component;
    }

    @Override
    public void methodA() {
       System.out.println("decoratorB A");
       this.component.methodA();
        
    }

    @Override
    public void methodB() {
        System.out.println("decoratorB B");
        this.component.methodB();
    }
}