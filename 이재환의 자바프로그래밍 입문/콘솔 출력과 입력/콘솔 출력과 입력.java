public class Ex01_printf {
    public static void main(String[] args) {
        String name = "Hong Gil Dong";    // 글자들을 저장할 때 사용하는 자료형
        int age = 20;
        double height = 175.5;

        System.out.printf(" % s 의 나이는 % d 이고, 키는 % f입니다.\n", name, age, height);
        System.out.printf("%s의 나이는 %d이고, 키는  % .2f 입니다.\n", name, age, height);
    }
}