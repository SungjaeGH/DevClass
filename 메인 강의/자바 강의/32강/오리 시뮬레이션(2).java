/* 요구사항
- D1 요구 : 오리를 생성하고 날게하시오.
- D2 요구 : 게임의 디테일을 살리기 위해서 `청둥오리`와 `흰오리`를 생성하고 날게하시오.
*/

public class Main {
    public static void main(String[] args) {
        청둥오리 a청둥오리 = new 청둥오리();
        a청둥오리.날다();
        // 출력 : 오리가 날개로 날아갑니다.

        흰오리 a흰오리 = new 흰오리();
        a흰오리.날다();
        // 출력 : 오리가 날개로 날아갑니다.
    }
}

class 오리 {
    void 날다() {
        System.out.println("오리가 날개로 날아갑니다.");
    }
}

class 청둥오리 {
    void 날다() {
        System.out.println("오리가 날개로 날아갑니다.");
    }
}

class 흰오리 {
    void 날다() {
        System.out.println("오리가 날개로 날아갑니다.");
    }
}