package com.pk.hotelmanagement.users.vo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostCode {
    private final String postCode;

    public PostCode(String postCode) {
        if (!isPostCode(postCode)) {
            throw new IllegalArgumentException("Given string is not a post code");
        }
        this.postCode = postCode;
    }

    private boolean isPostCode(String postCode) {
        Pattern pattern = Pattern.compile("^[0-9]{2}-[0-9]{3}$");
        Matcher matcher = pattern.matcher(postCode);
        return matcher.matches();
    }

    public String getPostCode() {
        return postCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostCode postCode1 = (PostCode) o;

        return postCode.equals(postCode1.postCode);
    }

    @Override
    public int hashCode() {
        return postCode.hashCode();
    }

    @Override
    public String toString() {
        return postCode;
    }
}
