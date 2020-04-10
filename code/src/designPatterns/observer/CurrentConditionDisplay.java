package designPatterns.observer;

public class CurrentConditionDisplay implements Observer {
    public CurrentConditionDisplay(Subject weatherData) {
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        System.out.println("CurrentConditionDisplay:" + temperature);
    }
}
