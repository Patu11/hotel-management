package com.pk.hotelmanagement.users.login;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BaseDecoder {

    public static String[] decode(String header) {

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decoded = decoder.decode(header);
        String data = new String(decoded, StandardCharsets.UTF_8);
        return data.split(":");
    }
}
