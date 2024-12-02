class Exam07 {
    public static void main(String[] args) {
        // 반복문 : Exam06 참고
        // 조건의 연산식을 충족하는 동안 중괄호 안의 내용을 실행
        for (int i = 1; i < 10; i = i + 1) {
            System.out.print(2 * i + " ");
        }
        System.out.println();

        System.out.println("========================");

        // 반복문 : 구구단 2단에서 9단 출력
        for (int i = 1; i <= 9; i++) {
            // 반복문 안에서 반복문을 적용할 대상이 또 있음
            System.out.print(2 * i + " ");
            System.out.print(3 * i + " ");
            System.out.print(4 * i + " ");
            System.out.print(5 * i + " ");
            System.out.print(6 * i + " ");
            System.out.print(7 * i + " ");
            System.out.print(8 * i + " ");
            System.out.print(9 * i + " ");
            System.out.println();
        }
    }
}