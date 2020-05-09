package designPatterns.template.demo;

public class Client {
    public static void main(String[] args) {
        CoffeineBeverage coffeineBeverage = new Coffee();
        coffeineBeverage.prepareRecipe();

        coffeineBeverage = new Tee();
        coffeineBeverage.prepareRecipe();
    }
}