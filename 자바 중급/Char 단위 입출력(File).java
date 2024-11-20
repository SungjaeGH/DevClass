import java.io.*;

public class CharIOExam {
    public static void main(String[] args) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("data.txt"));
            pw.println("안녕하세요. PrintWriter입니다.");
            pw.close();     // 무조건 close 해줘야한다!

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}