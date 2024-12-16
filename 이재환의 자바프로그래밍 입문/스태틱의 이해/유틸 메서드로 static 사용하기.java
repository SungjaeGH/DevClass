public class Ex03_UtilMethod {
    public static void main(String[] args) {
        MyCalculator calc1 = new MyCalculator();
        int num1 = calc1.adder(1, 2);
        System.out.println(num1);

        int num2 = MyCalculator.adder(2, 3);
        System.out.println(num2);
    }
}

class MyCalculator {
    public static int adder(int n1, int n2) {
        return n1 + n2;
    }
}