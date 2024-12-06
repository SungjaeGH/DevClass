import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StreamEx7 {
    public static void main(String[] args) {

        List<Person> people = new ArrayList<>();
        people.add(new Person(1, "Alice", 20, 'F'));
        people.add(new Person(2, "Bob", 25, 'M'));
        people.add(new Person(3, "David", 35, 'M'));

        // 문제 1 : 남성들의 나이의 합
        System.out.println("== No Sum Stream ==");
        noStreamSumVersion(people);

        System.out.println("== Sum Stream ==");
        streamSumVersion(people);

        // 문제 2 : 남성들의 나이의 평균
        System.out.println("== No Avg Stream ==");
        noStreamAvgVersion(people);

        System.out.println("== Avg Stream ==");
        streamAvgVersion(people);

        // 문제 3 : 남성들의 이름
        System.out.println("== No Stream ==");
        noStreamNameVersion(people);

        System.out.println("== Stream ==");
        streamNameVersion(people);

        // 문제 4 : id가 2번인 사람의 이름을 출력
        System.out.println("== No Stream ==");
        noStreamIdVersion(people);

        System.out.println("== Stream ==");
        streamIdVersion(people);
    }

    private static void noStreamSumVersion(List<Person> people) {
        int sum = 0;

        for (Person person : people) {
            if (person.getGender() == 'M') {
                sum += person.getAge();
            }
        }

        System.out.println("sum of age : " + sum);
    }

    private static void streamSumVersion(List<Person> people) {
        int sum = people.stream()
                .filter(p -> p.getGender() == 'M')
                .mapToInt(Person::getAge)
                .sum();

        System.out.println("sum of age = " + sum);
    }

    private static void noStreamAvgVersion(List<Person> people) {
        int sum = 0, itemsOfMan = 0;

        for (Person person : people) {
            if (person.getGender() == 'M') {
                sum += person.getAge();
                itemsOfMan++;
            }
        }

        double avg = (double) sum / itemsOfMan;
        System.out.println("avg of age = " + avg);
    }

    private static void streamAvgVersion(List<Person> people) {
        double avg = people.stream()
                .filter(p -> p.getGender() == 'M')
                .mapToInt(Person::getAge)
                .average()
                .orElse(0);

        System.out.println("avg of age = " + avg);
    }

    private static void noStreamNameVersion(List<Person> people) {

        List<String> names = new ArrayList<>();

        for (Person person : people) {
            if (person.getGender() == 'M') {
                names.add(person.getName());
            }
        }

        String manNames = String.join(", ", names);
        System.out.println("manNames = " + manNames);
    }

    private static void streamNameVersion(List<Person> people) {

        List<String> names = people.stream()
                .filter(p -> p.getGender() == 'M')
                .map(Person::getName)
                .toList();

        String manNames = String.join(", ", names);
        System.out.println("manNames = " + manNames);

    }

    private static void noStreamIdVersion(List<Person> people) {

        Person found = null;

        for (Person person : people) {
            if (person.getId() == 2) {
                found = person;
                break;
            }
        }

        if (found == null) {
            System.out.println("not found");
            return;
        }

        System.out.println("found : " + found.getName());
    }

    private static void streamIdVersion(List<Person> people) {

        Optional<Person> opPerson = people.stream()
                .filter(p -> p.getId() == 2)
                .findFirst();

        Person found = opPerson.orElse(null);

        if (found == null) {
            System.out.println("not found");
            return;
        }

        System.out.println("found : " + found.getName());
    }
}

class Person {

    private int id;
    private String name;
    private int age;
    private char gender;

    public Person(int id, String name, int age, char gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public char getGender() {
        return gender;
    }
}