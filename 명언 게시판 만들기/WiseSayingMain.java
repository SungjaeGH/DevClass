import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class WiseSayingMain {
    public static void main(String[] args) throws IOException {

        System.out.println("== 명언 앱 ==");

        ArrayList<WishSaying> wishSayings = new ArrayList<>();
        FileUtil fileUtil = new FileUtil();

        // 마지막 명언 Idx 탐색
        fileUtil.setFileDir("lastId.txt");
        int wishSayingIdx = fileUtil.findLastWishSayingIdx();

        // 명언 Idx 파일을 순회하여, 존재하는 명언 목록들을 list에 추가
        for (int idx = 1; idx <= wishSayingIdx; idx++) {

            fileUtil.setFileDir(idx + ".json");
            WishSaying wishSaying = fileUtil.findWishSaying();
            if (wishSaying != null) {
                wishSayings.add(wishSaying);
            }
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        boolean isEnd = false;
        while (!isEnd) {

            System.out.print("명령) ");
            String command = bufferedReader.readLine();

            if (command.equals("종료")) {

                isEnd = true;

            } else if (command.equals("등록")) {

                wishSayingIdx++;

                System.out.print("명언 : ");
                String content = bufferedReader.readLine();

                System.out.print("작가 : ");
                String author = bufferedReader.readLine();

                WishSaying wishSaying = new WishSaying(wishSayingIdx, content, author);
                wishSayings.add(wishSaying);

                // 명언 idx에 해당하는 파일에 명언 정보 저장
                fileUtil.setFileDir(wishSayingIdx + ".json");
                fileUtil.writeWishSaying(wishSaying);

                // 마지막 명언 idx 저장 파일에 명언 idx 저장
                fileUtil.setFileDir("lastId.txt");
                fileUtil.writeLastWishSayingIdx(wishSayingIdx);

                System.out.println(wishSayings.size() + "번 명언이 등록되었습니다.");

            } else if (command.equals("목록")) {

                System.out.println("번호 / 작가 / 명언");
                System.out.println("-------------------------");

                wishSayings.forEach(wishSaying -> System.out.println(
                        wishSaying.getIdx() + " / "
                        + wishSaying.getAuthor() + " / "
                        + wishSaying.getContent()));

            } else if (command.contains("삭제")) {

                int loc = command.indexOf('=');
                String idxStr = command.substring(loc + 1);
                int parseIdx = Integer.parseInt(idxStr);

                int findIdx = -1;
                for (int idx = 0; idx < wishSayings.size(); idx++) {
                    WishSaying wishSaying = wishSayings.get(idx);
                    if (wishSaying.getIdx() == parseIdx) {
                        findIdx = idx;
                    }
                }

                if (findIdx < 0) {
                    System.out.println(parseIdx + "번 명언은 존재하지 않습니다.");

                } else {
                    wishSayings.remove(findIdx);

                    // 명언 idx에 해당하는 파일 삭제
                    fileUtil.setFileDir((findIdx + 1) + ".json");
                    fileUtil.deleteWishSaying();

                    System.out.println(parseIdx + "번 명령이 삭제되었습니다.");
                }

            } else if (command.contains("수정")) {

                int loc = command.indexOf('=');
                String idxStr = command.substring(loc + 1);
                int parseIdx = Integer.parseInt(idxStr);

                int findIdx = -1;
                for (int idx = 0; idx < wishSayings.size(); idx++) {
                    WishSaying wishSaying = wishSayings.get(idx);
                    if (wishSaying.getIdx() == parseIdx) {
                        findIdx = idx;
                    }
                }

                if (findIdx < 0) {
                    System.out.println(parseIdx + "번 명언은 존재하지 않습니다.");
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

                // 명언 idx에 해당하는 파일에 명언 정보 저장
                fileUtil.setFileDir(parseIdx + ".json");
                fileUtil.writeWishSaying(update);
            }
        }

        bufferedReader.close();
    }
}
