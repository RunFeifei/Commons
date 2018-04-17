package com.fei.root.common;

import android.util.Base64;

public class Base64s {

    private static final int FLAG = Base64.NO_WRAP | Base64.URL_SAFE;

    public static byte[] encode(byte[] input) {
        return Base64.encode(input, FLAG);
    }

    public static String encodeToString(byte[] input) {
        return Base64.encodeToString(input, FLAG);
    }

    public static byte[] decode(byte[] input) {
        return Base64.decode(input, FLAG);
    }

    public static String base64decode(String string) {
        return new String(Base64.decode(string, Base64.DEFAULT));
    }
    public static String enCode(String string) {
        return Base64.encodeToString(string.getBytes(), Base64.DEFAULT);
    }
}
