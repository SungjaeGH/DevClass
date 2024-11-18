public class WhileExam {
    public static void main(String[] args) {
        int i = 1;
        while (i < 11) {

            // if 문을 추가해, i가 짝수일때만 i를 출력하는 코드를 짜보세요.
            if (i % 2 == 0) {
                System.out.println(i);
            }

            i++;
        }
    }
}