package designPatterns.adapter.design;

public class AdapteeImpl implements Adaptee{

    @Override
    public void methodB() {
        System.out.println("adaptee methodB");
    }
    
}