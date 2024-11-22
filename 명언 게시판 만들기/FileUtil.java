import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {

    private String fileDir;
    private final JsonUtil jsonUtil;

    FileUtil() {
        jsonUtil = new JsonUtil();
    }

    public void setFileDir(String fileName) {
        this.fileDir = "명언 게시판 만들기\\db\\wishSaying\\" + fileName;
    }

    public void writeWishSaying(WishSaying wishSaying) {

        try (FileOutputStream output = new FileOutputStream(this.fileDir)) {

            DataOutputStream dataOutput = new DataOutputStream(output);

            // json 형식으로 문자열 생성
            String data = jsonUtil.createWishSaying(wishSaying.getIdx(), wishSaying.getContent(), wishSaying.getAuthor());

            dataOutput.writeUTF(data);
            dataOutput.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeLastWishSayingIdx(int lastIdx) {

        try (FileOutputStream output = new FileOutputStream(this.fileDir)) {

            DataOutputStream dataOutput = new DataOutputStream(output);
            dataOutput.writeInt(lastIdx);
            dataOutput.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int findLastWishSayingIdx() {

        int lastIdx = 1;

        try (FileInputStream input = new FileInputStream(this.fileDir)) {

            DataInputStream dataInput = new DataInputStream(input);
            lastIdx = dataInput.readInt();
            dataInput.close();

        } catch (FileNotFoundException e) {
            // 파일이 존재하지 않을 경우, skip
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lastIdx;
    }

    public void deleteWishSaying() {

        Path filePath = Paths.get(this.fileDir);

        try {
            Files.delete(filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WishSaying findWishSaying() {

        WishSaying wishSaying = null;

        try (FileInputStream input = new FileInputStream(this.fileDir)) {

            DataInputStream dataInput = new DataInputStream(input);
            String wishSayingStr = dataInput.readUTF();

            wishSaying = jsonUtil.parseWishSaying(wishSayingStr);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return wishSaying;
    }
}
