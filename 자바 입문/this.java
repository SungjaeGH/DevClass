/* Person.java */
class Person {
    String name;
    int age;

    public Person(String name, int age) {
        // 매개변수로 받은 name과 age를 각각 name, age 필드에 저장하세요.
        this.name = name;
        this.age = age;

    }
}

/* PersonExam.java */
// 실행을 위한 코드입니다.
public class PersonExam {
    public static void main(String[] args) {
        // Person클래스에서 String과 int를 매개변수로 받는 생성자를 호출합니다.
        Person person = new Person("사람", 25);
    }
}