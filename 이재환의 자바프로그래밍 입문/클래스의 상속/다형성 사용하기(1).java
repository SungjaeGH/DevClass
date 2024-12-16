public class Ex01_Polymorphism1 {
    public static void main(String[] args) {
        MyCalc myCalc1 = new MyCalc();
        myCalc1.plus();
        myCalc1.minus();

        // 하위클래스 객체를 상위 클래스 객체에 대입
        Calc myCalc2 = new MyCalc();
        myCalc2.plus();
        // myCalc2.minus();
    }
}

abstract class Calc {
    int a = 5;
    int b = 6;

    abstract void plus();
}

class MyCalc extends Calc {
    void plus() {
        System.out.println(a + b);
    }

    void minus() {
        System.out.println(a - b);
    }
}