import java.util.StringTokenizer;

public class Ex09_StringTokenizer {
    public static void main(String[] args) {
        StringTokenizer st1 = new StringTokenizer("a b c");
        //StringTokenizer st2 = new StringTokenizer("1,2,3", ",");

        while (st1.hasMoreTokens()) {
            System.out.println(st1.nextToken());
        }
    }
}