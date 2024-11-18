/* Bus.java */
public class Bus extends Car {
    int fee;

    public Bus(String name, int number, int fee) {
        // super를 이용해서 Car클래스의 생성자를 이용하세요.
        super(name, number);
        this.fee = fee;
    }
}

/* Car.java */
public class Car {
    String name;
    int number;

    public Car(String name, int number) {
        this.name = name;
        this.number = number;
    }
}

/* BusExam.java */
//아래는 실행을 위한 코드입니다. 수정하지 마세요.
public class BusExam {
    public static void main(String[] args) {
        Bus bus = new Bus(new String("뛰뛰"), 3000, 1050);
        if (!bus.name.equals("뛰뛰")) {
            System.out.println("bus의 name이 다릅니다.");
        } else if (bus.number != 3000) {
            System.out.println("bus의 number가 다릅니다.");
        } else if (bus.fee != 1050) {
            System.out.println("bus의 fee가 다릅니다.");
        } else {
            System.out.println("정답입니다. [제출]을 누르세요.");
        }

    }
}