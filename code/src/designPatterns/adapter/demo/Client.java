package designPatterns.adapter.demo;

public class Client {
    public static void main(String[] args) {
        Turkey turkey = new WildTurkey();
        Duck duck = new AdaterDuck(turkey);
        duck.quack();
    }
    
}