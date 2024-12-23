/*
* 아래 부분 실행 시 해당 컴파일 에러 발생
/Ex03_CircleUsing.java:29: error: duplicate class: Circle class Circle
* */
public class Ex03_CircleUsing {
    public static void main(String[] args) {
        Circle c1 = new Circle(3.5);
        System.out.println("반지름 3.5 원 넓이: " + c1.getArea());

        Circle c2 = new Circle(3.5);
        System.out.println("반지름 3.5 원 둘레: " + c2.getCircumference());
    }
}

class Circle {
    double rad;
    final double PI;

    public Circle(double r) {
        rad = r;
        PI = 3.14;
    }

    // 원의 넓이 반환
    public double getArea() {
        return (rad * rad) * PI;
    }
}

class Circle {
    double rad;
    final double PI;

    public Circle(double r) {
        rad = r;
        PI = 3.14;
    }

    // 원의 둘레 길이 반환
    public double getCircumference() {
        return (rad * 2) * PI;
    }
}