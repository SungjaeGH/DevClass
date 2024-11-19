/* Taxi.java */
public class Taxi implements Meter {

    public int BASE_FARE = 3000;

    public void start() {
        System.out.println("운행을 시작합니다.");
    }

    public int stop(int distance) {
        int fare = BASE_FARE + distance * 2;
        System.out.println("운행을 종료합니다. 요금은 " + fare + "원 입니다.");
        return fare;
    }
}

/* Meter.java */
public interface Meter {
//    public int BASE_FARE = 3000; // 기본요금(인터페이스에 정의한 변수는 상수라서 변경할 수 없습니다.)

    public abstract void start();

    public abstract int stop(int distance);
}

/* TaxiExam.java */
public class TaxiExam {
    public static void main(String[] args) {
        Taxi taxi = new Taxi();
        taxi.BASE_FARE = 2500;
    }
}