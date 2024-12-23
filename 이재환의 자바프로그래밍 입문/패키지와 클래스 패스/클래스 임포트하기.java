public class Ex04_ImportUse {
    public static void main(String[] args) {
        Banana banana = new Banana();
        banana.showName();
    }
}

class Banana {
    public void showName() {
        System.out.println("My name is banana.");
    }
}