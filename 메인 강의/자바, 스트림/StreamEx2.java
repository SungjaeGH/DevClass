import java.util.stream.IntStream;

public class StreamEx2 {
    public static void main(String[] args) {

        // 일반
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                continue;
            }

            System.out.println(i);
        }

        // filter 적용
        IntStream.rangeClosed(1, 10)
                .filter(i -> i % 2 != 0)
                .forEach(System.out::println);

    }
}
