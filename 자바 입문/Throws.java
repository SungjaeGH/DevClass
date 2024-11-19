/* ExceptionExam.java */
public class ExceptionExam {
    public int get50thItem(int[] array) throws ArrayIndexOutOfBoundsException {
        return array[49];
    }
}

/* ExamExam.java */
public class ExamExam {
    public static void main(String[] args) {
        ExceptionExam ex = new ExceptionExam();
        int[] array = new int[40];

        try {
            System.out.println(ex.get50thItem(array));

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("문제 발생 : " + e.toString());
        }

    }
}
