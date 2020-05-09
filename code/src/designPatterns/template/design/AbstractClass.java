package designPatterns.template.design;

public abstract class AbstractClass {

    final void allProcess(){
        processA();
        processB();
        processC();
        processD();
        processE();
        processF();
    }

    void processA(){
        System.out.println("processA");
    }

    abstract void processB();

    abstract void processC();

    void processD(){
        System.out.println("process");
    }

    abstract void processE();

    void processF(){
        System.out.println("processF");
    }

}