/* ExceptionExam.java */
public class ExceptionExam {
    public int get50thItem(int[] array) throws IllegalArgumentException {
        if (array.length < 50) {
            throw new IllegalArgumentException("Array 크기가 50이 넘지 않습니다.");
        }
        return array[49];
    }
}

/* ExamExam.java */
//아래는 실행을 위한 코드입니다. 수정하지 마세요.
public class ExamExam {
    public static void main(String[] args) {
        ExceptionExam ex = new ExceptionExam();
    }
}
