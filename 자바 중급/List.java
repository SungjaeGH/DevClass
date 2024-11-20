import java.util.*;

public class ListExam {
    public List<String> addArray(String[] arr1, String[] arr2) {
        List<String> list = new ArrayList<String>();

        for (String str : arr1) {
            list.add(str);
            System.out.println(str);
        }

        for (String str : arr2) {
            list.add(str);
            System.out.println(str);
        }

        return list;
    }

    public static void main(String[] args) {
    }
}