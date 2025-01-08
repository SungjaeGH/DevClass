package com.ll.wisesaying.global.utils;

import java.util.StringTokenizer;

public class JsonUtil {

    public static String createStringJson(String key, String value) {

        return String.format("\"%s\" : \"%s\"", key, value);
    }

    public static String createIntJson(String key, int value) {

        return String.format("\"%s\" : %d", key, value);
    }

    public static String parseJsonKey(String data) {

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

    public static int parseIntJsonValue(String data) {

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

    public static String parseStringJsonValue(String data) {

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
