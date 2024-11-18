/* Car.java */
public class Car {
    String name;
    int number;

    Car(String name, int number) {
        this.name = name;
        this.number = number;
    }

    Car() {
        this("이름없음", 0);
    }

    Car(String name) {
        this.name = name;
        this.number = 0;
    }
}

/* CarExam.java */
// 실행을 위한 코드입니다.
public class CarExam {
    public static void main(String[] args) {
        Car car1 = new Car();
        Car car2 = new Car("자동차");
        Car car3 = new Car("자동차", 1234);
    }
}