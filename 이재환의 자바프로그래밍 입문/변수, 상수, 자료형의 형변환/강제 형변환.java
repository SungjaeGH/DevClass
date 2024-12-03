public class Ex03_TypeChange2 {
    public static void main(String[] args) {
        int num1 = 2147483647;      // OK
        // int num2 = 2147483648;      // 에러 발생
        // long num3 = 2147483648;     // 에러 발생
        long num4 = 2147483648L;    // OK

        // float num5 = 1.0;           // 에러 발생
        float num6 = 1.0F;          // OK

        double num7 = 30;    // int -> double 자동 형변환
    }
}