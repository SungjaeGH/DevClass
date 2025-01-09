enum Human2 {MAN, WOMAN}

enum Machine2 {TANK, AIRPLANE}

public class Ex02_Enum {
    public static void main(String[] args) {
        createUnit(Machine2.TANK);

        // createUnit(Human2.DOG);

        // 참고 : C처럼 숫자로 비교하면 에러가 난다.
//    	if (Human2.MAN == 0) {
//
//    	}
    }

    public static void createUnit(Machine2 kind) {
        switch (kind) {
            case TANK:
                System.out.println("탱크를 만듭니다.");
                break;
            case AIRPLANE:
                System.out.println("비행기를 만듭니다.");
                break;
        }
    }
}