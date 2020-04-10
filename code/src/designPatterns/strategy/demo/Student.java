package designPatterns.strategy.demo;

public class Student {
    private int age;
    private String name;

    public Student(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }
}
