/* CarExam.java */
class CarExam {
    public static void main(String[] args) {
        // 이 아래에서 car에 Car클래스의 인스턴스를 생성하고 run메소드를 사용해 보세요.
        Car car = new Car();
        car.run();
    }
}

/* Car.java */
class Car {
    void run() {
        System.out.println("차가 달립니다.");
    }
}