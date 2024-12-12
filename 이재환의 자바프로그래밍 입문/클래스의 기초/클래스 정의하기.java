public class NpcUse {
    public static void main(String[] args) {
        Npc saram1 = new Npc();

        saram1.name = "경비";
        saram1.hp = 100;
        System.out.println(saram1.name + ":" + saram1.hp);

        saram1.say();
    }
}

class Npc {

    public String name;
    public int hp;

    public void say() {
        System.out.println("안녕하세요.");
    }
}