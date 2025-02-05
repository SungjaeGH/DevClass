/*
* 삽입 정렬 : 앞에서부터 차례대로 이미 정렬된 부분과 비교하여 교환하는 방식
* */

public class InsertSort {
    public static void insertSort(int[] num) {

        int size = num.length;
        int temp = 0;
        int j = 0;

        for (int i = 1; i < size; i++) {
            temp = num[i];

            for (j = i - 1; j >= 0 && temp < num[j]; j--) {
                num[j + 1] = num[j];
            }
            num[j + 1] = temp;
        }
    }

    public static void main(String[] args) {
        int num[] = {5, 3, 1, 4, 2};

        insertSort(num);

        for (int i = 0; i < num.length; i++) {
            System.out.print(num[i] + "\t");
        }
        System.out.println();
    }
}