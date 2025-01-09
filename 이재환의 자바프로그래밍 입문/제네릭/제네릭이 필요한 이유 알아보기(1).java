public class Ex01_MyGame1 {
    public static void main(String[] args) {
        // 게임 종족 생성
        HumanCamp1 human = new HumanCamp1();
        MachineCamp1 machine = new MachineCamp1();

        // 게임 종족에 유닛을 생성해 담기
        human.set(new Npc1());
        machine.set(new Tank1());

        Npc1 hUnit = human.get();
        Tank1 mUnit = machine.get();

        System.out.println(hUnit);
        System.out.println(mUnit);
    }
}

class Npc1 {
    public String toString() {
        return "This is a Npc1.";
    }
}

class HumanCamp1 {

    private Npc1 npc1;

    public void set(Npc1 npc1) {
        this.npc1 = npc1;
    }

    public Npc1 get() {
        return this.npc1;
    }
}

class Tank1 {
    public String toString() {
        return "This is a Tank1.";
    }
}

class MachineCamp1 {

    private Tank1 tank1;

    public void set(Tank1 tank1) {
        this.tank1 = tank1;
    }

    public Tank1 get() {
        return this.tank1;
    }
}