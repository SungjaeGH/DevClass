/* CarExam.java */

import java.util.*;
public class CarExam{
    public static void main(String[] args){
        List<Car> cars = new ArrayList<>();
        cars.add( new Car("작은차",2,800,3) );
        cars.add( new Car("봉고차",12,1500,8) );
        cars.add( new Car("중간차",5,2200,0) );
        cars.add( new Car("비싼차",5,3500,1) );

        printCar(cars, new CheckCarForBigAndNotExpensive());
    }

    public static void printCar(List<Car> cars, CheckCar tester){
        for(Car car : cars){
            if (tester.test(car)) {
                System.out.println(car);
            }
        }
    }

    interface CheckCar{
        boolean test(Car car);
    }

    //내부클래스를 만들어서 사용합니다.
    static class CheckCarForBigAndNotExpensive implements CheckCar{
        public boolean test(Car car){
            return car.capacity >= 4 && car.price < 2500;
        }
    }
}

/* Car.java */
public class Car{
    public String name;
    public int capacity;
    public int price;
    public int age;

    public Car(String name, int capacity, int price, int age){
        this.name = name;
        this.capacity = capacity;
        this.price = price;
        this.age = age;
    }

    public String toString(){
        return name;
    }
}