package com.ll.wisesaying.utils;

import java.util.StringTokenizer;

public class JsonUtil {

    public String createStringJson(String key, String value) {

        return String.format("\"%s\" : \"%s\"\n", key, value);
    }

    public String createIntJson(String key, int value) {

        return String.format("\"%s\" : %d\n", key, value);
    }

    public String parseJsonKey(String data) {

        String parsedKey = null;
        int loc = data.indexOf(":");
        String substring = data.substring(0, loc);

        StringTokenizer st = new StringTokenizer(substring, "\"");
        while (st.hasMoreTokens()) {

            String token = st.nextToken().trim();
            if (!token.isEmpty()) {
                parsedKey = token;
                break;
            }
        }

        return parsedKey;
    }

    public int parseIntJsonValue(String data) {

        int parsedValue = 0;
        int loc = data.indexOf(":");
        String substring = data.substring(loc + 1);

        StringTokenizer st = new StringTokenizer(substring, "\"");
        while (st.hasMoreTokens()) {

            String token = st.nextToken().trim();
            if (!token.isEmpty()) {
                parsedValue = Integer.parseInt(token);
                break;
            }
        }

        return parsedValue;
    }

    public String parseStringJsonValue(String data) {

        String parsedValue = null;
        int loc = data.indexOf(":");
        String substring = data.substring(loc + 1);

        StringTokenizer st = new StringTokenizer(substring, "\"");
        while (st.hasMoreTokens()) {

            String token = st.nextToken().trim();
            if (!token.isEmpty()) {
                parsedValue = token;
                break;
            }
        }

        return parsedValue;
    }
}
