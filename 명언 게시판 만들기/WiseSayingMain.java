import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class WiseSayingMain {

    public static int wishSayingIdx = 1;
    public static void main(String[] args) throws IOException {

        System.out.println("== 명언 앱 ==");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<WishSaying> wishSayings = new ArrayList<>();

        boolean isEnd = false;
        while (!isEnd) {

            System.out.print("명령) ");
            String command = bufferedReader.readLine();

            if (command.equals("종료")) {

                isEnd = true;

            } else if (command.equals("등록")) {

                System.out.print("명언 : ");
                String content = bufferedReader.readLine();

                System.out.print("작가 : ");
                String author = bufferedReader.readLine();

                WishSaying wishSaying = new WishSaying(wishSayingIdx, content, author);
                wishSayings.add(wishSaying);

                System.out.println(wishSayings.size() + "번 명언이 등록되었습니다.");

                wishSayingIdx++;

            } else if (command.equals("목록")) {

                System.out.println("번호 / 작가 / 명언");
                System.out.println("-------------------------");

                wishSayings.forEach(wishSaying -> {
                    System.out.println(wishSaying.getIdx() + " / "
                            + wishSaying.getAuthor() + " / "
                            + wishSaying.getContent());
                });

            } else if (command.contains("삭제")) {

                int loc = command.indexOf('=');
                String idxStr = command.substring(loc + 1);
                int wishSayingIdx = Integer.parseInt(idxStr);

                int findIdx = -1;
                for (int idx = 0; idx < wishSayings.size(); idx++) {
                    WishSaying wishSaying = wishSayings.get(idx);
                    if (wishSaying.getIdx() == wishSayingIdx) {
                        findIdx = idx;
                    }
                }

                if (findIdx < 0) {
                    System.out.println(wishSayingIdx + "번 명언은 존재하지 않습니다.");

                } else {
                    wishSayings.remove(findIdx);
                    System.out.println(wishSayingIdx + "번 명령이 삭제되었습니다.");
                }

            } else if (command.contains("수정")) {

                int loc = command.indexOf('=');
                String idxStr = command.substring(loc + 1);
                int wishSayingIdx = Integer.parseInt(idxStr);

                int findIdx = -1;
                for (int idx = 0; idx < wishSayings.size(); idx++) {
                    WishSaying wishSaying = wishSayings.get(idx);
                    if (wishSaying.getIdx() == wishSayingIdx) {
                        findIdx = idx;
                    }
                }

                if (findIdx < 0) {
                    System.out.println(wishSayingIdx + "번 명언은 존재하지 않습니다.");
                    continue;
                }

                WishSaying update = wishSayings.get(findIdx);

                System.out.println("명언(기존) : " + update.getContent());
                System.out.print("명령 : ");
                String updateContent = bufferedReader.readLine();

                System.out.println("작가(기존) : " + update.getAuthor());
                System.out.print("명령 : ");
                String updateAuthor = bufferedReader.readLine();

                update.setContent(updateContent);
                update.setAuthor(updateAuthor);
            }
        }

        bufferedReader.close();
    }
}
