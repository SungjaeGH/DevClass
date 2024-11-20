public class Main {
    public static void main(String[] args) {
        로봇오리 a로봇오리 = new 로봇오리();
        a로봇오리.날다();
        // 출력 : 저는 못 날아요 ㅠㅠ
        a로봇오리.수영하다();
        // 출력 : 물갈퀴로 수영합니다.
    }
}

class 오리 {
    void 날다() {
        System.out.println("오리가 날개로 날아갑니다.");
    }

    void 수영하다() {
        System.out.println("물갈퀴로 수영합니다.");
    }
}

class 고무오리 extends 오리 {
    void 날다() {
        System.out.println("저는 못 날아요 ㅠㅠ");
    }

    void 수영하다() {
        System.out.println("물에 둥둥 뜹니다.");
    }
}

class 로봇오리 extends 오리 {
    void 날다() { // 상속만으로는 완벽한 중복제거가 불가능한 상황이 옵니다. 즉 실패!
        System.out.println("저는 못 날아요 ㅠㅠ");
    }
}