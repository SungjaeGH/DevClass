/* Car.java */
class Car extends Machine {
    // Machine 클래스를 상속받고, 추상 메소드를 구현하세요.
    @Override
    public void turnOn() {
        System.out.println("시작");
    }

    @Override
    public void turnOff() {
        System.out.println("종료");
    }
}

/* Machine.java */
public abstract class Machine {
    public abstract void turnOn();

    public abstract void turnOff();
}

/* CarExam.java */
//아래는 실행을 위한 코드입니다. 수정하지 마세요.
public class CarExam {
    public static void main(String[] args) {
        Car car = new Car();
        if (Machine.class.isInstance(car)) {
            System.out.println("정답입니다. [제출]을 누르세요.");
        } else {
            System.out.println("Car가 Machine을 상속받지 않았습니다.");
        }
    }
}