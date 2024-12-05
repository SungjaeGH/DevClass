public class Ex03_SwitchNoneBreak {
    public static void main(String[] args) {
        int n = 5;

        switch (n % 7) {
            case 1, 2, 3, 4, 5:
                System.out.println("주중");
                break;
            case 6:
            default:
                System.out.println("주말");
        }
    }
}