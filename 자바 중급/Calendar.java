import java.util.*;

public class CalendarExam {
    public String hundredDaysAfter() {
        //오늘부터 100일 뒤의 날짜를 "2016년1월1일"의 형식으로 return하세요.

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 100);

        return cal.get(Calendar.YEAR) + "년"
                + (cal.get(Calendar.MONTH) + 1) + "월"
                + cal.get(Calendar.DATE) + "일";
    }

    public static void main(String[] args) {
    }
}