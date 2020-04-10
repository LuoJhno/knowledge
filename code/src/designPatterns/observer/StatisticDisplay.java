package designPatterns.observer;

public class StatisticDisplay implements Observer {
    public StatisticDisplay(Subject weatherData) {
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        System.out.println("StatisticDisplay:" + humidity);
    }
}
