public class ExceptionExam {
    public static void main(String[] args) {
        int[] array = new int[10];
        try {
            array[20] = 5;

        } catch (Exception e) {
            System.out.println("에러 발생 : " + e.toString());

        }
    }
}