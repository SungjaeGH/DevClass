/* Car.java */
public class Car {
    public String name;
    public int number;

    public Car(String name, int number) {
        this.name = name;
        this.number = number;
    }
}

/* CarExam.java */
public class CarExam {
    public static void main(String[] args) {
        Car car = new Car("포니", 1234);

        System.out.println("name: " + car.name);
        System.out.println("number: " + car.number);

    }
}