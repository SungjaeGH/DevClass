public class Ex07_mainParameter {
    public static void main(String[] args) {
        // main 메서드의 매개변수 확인
        if (args != null) {
            for (int i = 0; i < args.length; i++)
                System.out.println(args[i]);
        }
    }
}
