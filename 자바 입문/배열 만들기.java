public class ArrayExam {
    public int[] makeArray() {
        // 길이가 5인 정수형 배열 array를 만들어보세요.
        // array의 원소는 차례로 1, 2, 3, 4, 5 여야합니다.
        int[] array = {1, 2, 3, 4, 5};

        // 아래는 결과 평가를 위한 코드입니다. 수정하지 마세요.
        return array;
    }

    //아래는 실행을 위한 코드입니다. 수정하지 마세요.
    public static void main(String[] args) {
        ArrayExam exam = new ArrayExam();
        int[] array = exam.makeArray();
        if (array.length == 5 && array[0] == 1 && array[1] == 2 && array[2] == 3 && array[3] == 4 && array[4] == 5) {
            System.out.println("정답입니다. [제출]을 누르세요.");
        } else {
            System.out.println("틀렸습니다.");
        }
    }
}