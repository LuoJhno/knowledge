package designPatterns.adapter.demo;

public class WildTurkey implements Turkey {

    @Override
    public void gobble() {
        System.out.println("turkey gobble");

    }

}