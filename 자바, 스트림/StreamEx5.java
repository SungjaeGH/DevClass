import java.util.Arrays;

public class StreamEx5 {
    public static void main(String[] args) {

        System.out.println("== No Stream ==");
        noStreamVersion();

        System.out.println("== Stream ==");
        streamVersion();
    }

    private static void noStreamVersion() {

        String[] messages = new String[]{"1번", "2번", "3번"};
        int[] numbers = new int[messages.length];

        for (int i = 0; i < messages.length; i++) {
            String message = messages[i];
            // message에서 가장 마지막 한 자를 제거
            message = message.substring(0, message.length() - 1);
            int number = Integer.parseInt(message);

            if (number % 2 == 0) {
                continue;
            }

            numbers[i] = number;
        }

        System.out.println("= 결과 =");
        System.out.println(Arrays.toString(numbers));
    }

    private static void streamVersion() {

        int[] array = Arrays.stream(new String[]{"1번", "2번", "3번"})
                .map(s -> s.substring(0, s.length() - 1))
                .mapToInt(Integer::parseInt)
                .filter(i -> i % 2 != 0)
                .toArray();

        System.out.println("= 결과 =");
        System.out.println(Arrays.toString(array));
    }
}
