public class Ex02_switch {
    public static void main(String[] args) {
        int n = 4;

        switch (n % 3) {
            case 1 -> System.out.println("나머지가 1");
            case 2 -> System.out.println("나머지가 2");
            default -> System.out.println("나머지가 0");
        }
    }
}