class Exam07 {
    public static void main(String[] args) {
        // 반복문 : Exam06 참고
        // 조건의 연산식을 충족하는 동안 중괄호 안의 내용을 실행
        for (int i = 1; i < 10; i = i + 1) {
            System.out.print(2 * i + " ");
        }
        System.out.println();

        System.out.println("========================");

        // 반복문의 중첩
        for (int i = 2; i < 10; i = i + 1) {
            for (int j = 2; j <= 9; j++) {

                System.out.print(i * j + " ");

            }
            System.out.println();
        }
    }
}