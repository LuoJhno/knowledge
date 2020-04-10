package designPatterns.strategy.demo;

import java.util.Arrays;

public class DemoContext {
    public static void main(String[] args) {
        Student[] students = {
                new Student("张三", 11),
                new Student("Lsi", 21),
                new Student("wangwu", 31),
        };
        Arrays.sort(students, new StudentAgeComparator());
    }
}
