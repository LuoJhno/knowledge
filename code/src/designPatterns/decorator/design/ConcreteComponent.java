package designPatterns.decorator.design;

public class ConcreteComponent implements Component{

    @Override
    public void methodA() {
        System.out.print("super A");
        
    }
    @Override
    public void methodB() {
        System.out.print("super ");
        
    }
    
}