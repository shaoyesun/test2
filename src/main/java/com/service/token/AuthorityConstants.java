package com.service.token;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthorityConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";

    // This class should not be instantiated.
    private AuthorityConstants() {
    }
}
