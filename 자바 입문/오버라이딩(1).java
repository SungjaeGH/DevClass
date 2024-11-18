/* Bus.java */
public class Bus extends Car {
    // run 메소드를 오버라이드 하세요. 메소드의 접근 제한자는 public이어야 합니다.
    @Override
    public void run() {
        System.out.println("차가 달리면서 다음 정거장을 안내합니다.");
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
//다음은 실행을 위한 코드입니다. 수정하지 마세요.
class BusExam {
    public static void main(String[] args) {
        Bus bus = new Bus();
        bus.run();
    }
}