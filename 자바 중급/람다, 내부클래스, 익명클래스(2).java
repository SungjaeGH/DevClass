/* CarExam.java */

import java.util.*;

public class CarExam {
    public static void main(String[] args) {
        //Car객체를 만들어서 cars에 넣습니다.
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("작은차", 2, 800, 3));
        cars.add(new Car("봉고차", 12, 1500, 8));
        cars.add(new Car("중간차", 5, 2200, 0));
        cars.add(new Car("비싼차", 5, 3500, 1));

        printCarCheaperThan(cars, 2000);
    }

    public static void printCarCheaperThan(List<Car> cars, int price) {
        for (Car car : cars) {
            if (car.price < price) {
                System.out.println(car);
            }
        }
    }
}

/* Car.java */
public class Car {
    public String name;
    public int capacity;
    public int price;
    public int age;

    public Car(String name, int capacity, int price, int age) {
        this.name = name;
        this.capacity = capacity;
        this.price = price;
        this.age = age;
    }

    public String toString() {
        return name;
    }
}