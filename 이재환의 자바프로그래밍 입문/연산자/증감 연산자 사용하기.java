public class Ex04_PrefixPostfix {
    public static void main(String[] args) {
        int num = 10;
        System.out.println(++num);  // num의 값 하나 증가 후 출력
        System.out.println(num);    // num의 값이 증가되어 있음

        System.out.println(num++);  // 출력 후에 값이 증가
        System.out.println(num);    // 출력 후에 값이 증가
    }
}