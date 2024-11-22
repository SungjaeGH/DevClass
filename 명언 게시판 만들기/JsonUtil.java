import java.util.StringTokenizer;

public class JsonUtil {

    public String createWishSaying(int idx, String content, String author) {

        return String.format("""
                    {
                        "id" : %d,
                        "content" : "%s",
                        "author" : "%s"
                    }
                    """,
                idx,
                content,
                author);
    }

    public WishSaying parseWishSaying(String wishSayingStr) {

        int id = 0;
        String content = null;
        String author = null;

        StringTokenizer st = new StringTokenizer(wishSayingStr, "{,}");

        while (st.hasMoreTokens()) {

            String token = st.nextToken().trim();
            String value = parseWishSayingValue(token);

            if (token.contains("id")) {
                id = Integer.parseInt(value);

            } else if (token.contains("content")) {
                content = value;

            } else if (token.contains("author")) {
                author = value;
            }
        }

        return new WishSaying(id, content, author);
    }

    private String parseWishSayingValue(String value) {

        String wishSayingValue = null;
        int loc = value.indexOf(":");
        String substring = value.substring(loc + 1);

        StringTokenizer st = new StringTokenizer(substring, "\"");
        while (st.hasMoreTokens()) {

            String token = st.nextToken().trim();
            if (!token.isEmpty()) {
                wishSayingValue = token;
                break;
            }
        }

        return wishSayingValue;
    }
}
