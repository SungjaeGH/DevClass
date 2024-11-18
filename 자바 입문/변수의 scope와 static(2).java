/* Car.java */
public class Car {
    static int wheelCount;
}

/* StaticExam.java */
public class StaticExam {
    public static void main(String[] args) {
        Car taxi = new Car();
        Car suv = new Car();

        taxi.wheelCount = 10;
        suv.wheelCount = 4;

        System.out.println("taxi의 바퀴수:" + taxi.wheelCount);
        System.out.println("suv의 바퀴수:" + suv.wheelCount);
    }
}