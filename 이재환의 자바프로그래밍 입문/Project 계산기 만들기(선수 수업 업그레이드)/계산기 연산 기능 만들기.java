import java.util.Scanner;

public class MyCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) // 반복
        {
            showMenu(); // 메뉴 출력

            int num = sc.nextInt(); // 입력 받기
            if (num == 0) // 반복문 종료
            {
                break;
            } else {
                if (num > 4) {
                    System.out.println("메뉴를 잘못 선택했습니다.");
                    continue;
                }

                // 더하기, 빼기, 곱하기, 나누기 실행 <-- STEP 4에서 코드를 작성할 위치
            }
        }
        System.out.println("계산기를 종료합니다."); // 종료 메시지
    }

    public static void showMenu() {
        System.out.println("메뉴를 선택하세요.");
        System.out.println("1.더하기");
        System.out.println("2.빼기");
        System.out.println("3.곱하기");
        System.out.println("4.나누기");
        System.out.println("0.끝내기");
    }

    public static void addNum(int num1, int num2) {

        int result = num1 + num2;
        System.out.println(num1 + " + " + num2 + " = " + result);
    }

    public static void minusNum(int num1, int num2) // 빼기
    {
        int result = num1 - num2;
        System.out.println(num1 + " - " + num2 + " = " + result);
    }

    public static void multiplyNum(int num1, int num2) // 곱하기
    {
        int result = num1 * num2;
        System.out.println(num1 + " * " + num2 + " = " + result);
    }

    public static void divideNum(int num1, int num2) // 나누기
    {
        int result1 = num1 / num2;
        System.out.println(num1 + " / " + num2 + " = " + result1);

        int result2 = num1 % num2;
        System.out.println(num1 + " % " + num2 + " = " + result2);
    }
}