/* Person.java */
class Person{
    int age;

    public Person(int age) {
        this.age = age;
    }
}

/* PersonExam.java */
//실행을 위한 코드입니다.
public class PersonExam {
    public static void main(String[] args) {
        //Person클래스에서 int형 변수를 매개변수로 받는 생성자를 호출합니다.
        Person person = new Person(25);
    }
}