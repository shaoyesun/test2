package com.service.security;

import com.google.common.base.Preconditions;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Map;

public class UserAuthenticationToken extends AbstractAuthenticationToken {

    private final String userUuid;
    private final String jwtToken;
    private final Map<String, Object> jwtClaims;
    private final long expires;

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public UserAuthenticationToken(String userUuid, String jwtToken, Map<String, Object> jwtClaims, long expires, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.userUuid = Preconditions.checkNotNull(userUuid);
        this.jwtToken = Preconditions.checkNotNull(jwtToken);
        this.jwtClaims = Preconditions.checkNotNull(jwtClaims);
        this.expires = expires;
        super.setAuthenticated(!authorities.isEmpty());
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userUuid;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public Map<String, Object> getJwtClaims() {
        return jwtClaims;
    }

    public long getExpires() {
        return expires;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Once created you cannot set this token to authenticated. Create a new instance using the constructor which takes a GrantedAuthority list will mark this as authenticated.");
        }

        super.setAuthenticated(false);
    }

    public static UserAuthenticationToken getCurrentToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Assert.isInstanceOf(UserAuthenticationToken.class, authentication, "Invalid authentication");
        return (UserAuthenticationToken) authentication;
    }

    public String getUserUuid() {
        return userUuid;
    }

}
