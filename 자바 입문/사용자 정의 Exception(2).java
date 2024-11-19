/* ExceptionExam.java */
public class ExceptionExam {
    public static void main(String[] args) {
        ExceptionExam exam = new ExceptionExam();
        int[] array = new int[10];

        try {
            exam.get50thItem(array);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int get50thItem(int[] array) throws MyCheckedException {
        if (array.length < 50) {
            throw new MyCheckedException();
        }
        return array[49];
    }
}


/* MyCheckedException.java */
public class MyCheckedException extends Exception {

}