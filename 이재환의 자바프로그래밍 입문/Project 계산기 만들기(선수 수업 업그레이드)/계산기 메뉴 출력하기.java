import java.util.Scanner; // 입력 처리 클래스

public class MyCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // 입력 처리 객체
        while (true) {

            // 메뉴 출력
            showMenu();

            int num = sc.nextInt();
            if (num == 0) {
                break;

            } else if (num > 4) {
                System.out.println("메뉴를 잘못 선택했습니다.");
                continue;
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
}