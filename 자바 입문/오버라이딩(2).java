/* Bus.java */
public class Bus extends Car {
    public void run() {
        // Car 클래스의 run을 호출합니다.
        super.run();
        System.out.println("다음 정거장을 안내합니다.");
    }
}

/* Car.java */
public class Car {
    public void run() {
        System.out.println("차가 달립니다.");
    }

    public void stop() {
        System.out.println("차가 멈춥니다.");
    }

    public void horn() {
        System.out.println("경적을 울립니다.");
    }
}

/* BusExam.java */
public class BusExam {
    public static void main(String[] args) {
        Bus bus = new Bus();
        bus.run();
    }
}