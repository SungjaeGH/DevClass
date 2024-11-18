/* Song.java */
public class Song {
    // 이곳에 코드를 작성하세요.
    String songTitle;
    String singer;
    String albumName;
    int trackNumber;
}

/* SongExam.java */
// Song.java 코드를 실행하기 위한 코드입니다. 이 코드는 수정하지 마세요.
public class SongExam {
    public static void main(String[] args) {
        Song song = new Song();
        song.songTitle = new String("곡명");
        song.singer = new String("가수");
        song.albumName = new String("앨범");
        song.trackNumber = 5;
    }
}