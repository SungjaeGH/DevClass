/* Car.java */
public class Car {
    String name;
    int number;

    //toString을 오버라이드 해 보세요.
    //return 형식은 아래줄을 참고하세요:
    //"name: " + name + ", number: " + number;
    @Override
    public String toString() {
        return "name: " + name + ", number: " + number;
    }
}

/* CarExam.java */
//아래는 실행을 위한 코드입니다. 수정하지 마세요.
public class CarExam {
    public static void main(String[] args) {
        Car ex = new Car();
    }
}