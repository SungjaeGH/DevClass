public class Ex04_toString3 {
    public static void main(String[] args) {
        Book4 myBook = new Book4();
        myBook.author = "홍길동";
        System.out.println(myBook.author);
        System.out.println(myBook);
    }
}

class Book4 {
    String author;

    public String toString() {
        return author;
    }
}