import java.util.ArrayList;
import java.util.StringTokenizer;

public class JsonUtil {

    public String createWishSaying(WishSaying wishSaying, JsonConcatType type) {

        return (type.equals(JsonConcatType.SINGLE)) ?
                String.format("""
                        {
                            "id" : %d,
                            "content" : "%s",
                            "author" : "%s"
                        }""",
                        wishSaying.getIdx(),
                        wishSaying.getContent(),
                        wishSaying.getAuthor())
                :
                String.format("""
                        \t{
                        \t    "id" : %d,
                        \t    "content" : "%s",
                        \t    "author" : "%s"
                        \t}""",
                        wishSaying.getIdx(),
                        wishSaying.getContent(),
                        wishSaying.getAuthor());
    }

    public String concatWishSayings(ArrayList<WishSaying> wishSayings) {

        StringBuilder sb = new StringBuilder();

        for (int idx = 0; idx < wishSayings.size(); idx++) {

            WishSaying wishSaying = wishSayings.get(idx);
            String data = createWishSaying(wishSaying, JsonConcatType.MULTI);
            sb.append(data);

            if (idx != wishSayings.size() - 1) {
                sb.append(",\n");
            }
        }

        sb.insert(0, "[\n");
        sb.insert(sb.length(), "\n]");

        return sb.toString();
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
