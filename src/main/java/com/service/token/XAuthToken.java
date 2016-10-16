package com.service.token;

/**
 * The token.
 */
public class XAuthToken {

    private final String token;
    private final long expires;

    public XAuthToken(String token, long expires) {
        this.token = token;
        this.expires = expires;
    }

    public String getToken() {
        return token;
    }

    public long getExpires() {
        return expires;
    }
}
