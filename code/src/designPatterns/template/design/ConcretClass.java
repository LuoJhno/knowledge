package designPatterns.template.design;

public class ConcretClass extends AbstractClass {

    @Override
    void processB() {
        System.out.println("Concret processB");

    }

    @Override
    void processC() {
        System.out.println("Concret processC");

    }

    @Override
    void processE() {
        System.out.println("Concret processE");

    }

}