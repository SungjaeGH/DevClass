public class OverloadingUse {
    public static void main(String[] args) {

        Calc calc = new Calc();
        int nRtn1 = calc.add(3, 9);
        int nRtn2 = calc.add(3);
        double nRtn3 = calc.add(3.0, 9.0);

        System.out.println("Rtn1 = " + nRtn1);
        System.out.println("Rtn2 = " + nRtn2);
        System.out.println("Rtn3 = " + nRtn3);
    }
}

class Calc {
    public int add(int num1, int num2) {
        return num1 + num2;
    }

    public int add(int num) {
        return num;
    }

    public double add(double num1, double num2) {
        return num1 + num2;
    }
}