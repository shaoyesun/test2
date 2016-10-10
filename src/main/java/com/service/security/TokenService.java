package com.service.security;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import com.entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.service.security.util.JsonUtils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.inject.Inject;

@Service
public class TokenService {

    private static final Logger LOG = LoggerFactory.getLogger(TokenService.class);

    private static final Long TOKEN_VALIDITY = 24 * 3600 * 1000L; // 24 hours

    // TODO: scheduled method to periodically clean up expired tokens.
    private final Set<String> tokenIdStore = Sets.newConcurrentHashSet();

    @Inject
    private SignerProvider signerProvider;

    /**
     * 生成token
     *
     * @param user
     * @return
     */
    public String createUserAuthToken(User user) {
        Preconditions.checkNotNull(user);

        String tokenId = UUID.randomUUID().toString();
        Collection<? extends GrantedAuthority> userAuthorities = false ?
                Arrays.asList(new SimpleGrantedAuthority(AuthorityConstants.ADMIN)) :
                Arrays.asList(new SimpleGrantedAuthority(AuthorityConstants.USER));

        Map<String, Object> tokenContent = createTokenContent(tokenId, user.getId().toString(), user.getUserName(), userAuthorities);

        String content;
        try {
            content = JsonUtils.writeValueAsString(tokenContent);
        } catch (JsonUtils.JsonUtilException e) {
            LOG.error("Cannot convert auth token to JSON", e);
            throw new IllegalStateException("Cannot convert auth token to JSON", e);
        }

        String jwtToken = JwtHelper.encode(content, signerProvider.getSigner()).getEncoded();
        return jwtToken;
    }


    private Map<String, Object> createTokenContent(String tokenId, String userUuid, String username, Collection<? extends GrantedAuthority> authorities) {
        Map<String, Object> result = Maps.newLinkedHashMap();
        result.put(ClaimConstants.TOKEN_ID, tokenId);
        result.put(ClaimConstants.USER_UUID, userUuid);
        result.put(ClaimConstants.AUTHORITIES, authorityListToSet(authorities));
        result.put(ClaimConstants.EXP, System.currentTimeMillis() + TOKEN_VALIDITY);
        return result;
    }

    private Set<String> authorityListToSet(Collection<? extends GrantedAuthority> authorities) {
        Set<String> set = new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            set.add(authority.getAuthority());
        }
        return set;
    }

    /**
     * token解析
     *
     * @param token
     * @return
     */
    public UserAuthenticationToken retrieveUserAuthToken(String token) {
        Preconditions.checkArgument(StringUtils.isNotBlank(token));

        Map<String, Object> claims = getClaimsForToken(token);

        // Check if token is still valid.
        String tokenId = (String) claims.get(ClaimConstants.TOKEN_ID);
        if (StringUtils.isEmpty(tokenId)) {
            LOG.debug("Invalid token (no tid): " + token);
            throw new InvalidTokenException("Invalid token (no tid): " + token);
        }

        // Check expiration
        Long exp = (Long) claims.get(ClaimConstants.EXP);
        if (exp == null || exp.longValue() < System.currentTimeMillis()) {
            LOG.debug("Token expired: " + token);
            throw new InvalidTokenException("Token expired: " + token);
        }

        String userUuid = (String) claims.get(ClaimConstants.USER_UUID);
        if (StringUtils.isEmpty(userUuid)) {
            LOG.debug("Invalid token (no userUuid): " + token);
            throw new InvalidTokenException("Invalid token (no userUuid): " + token);
        }

        Object authoritesFromClaims = claims.get(ClaimConstants.AUTHORITIES);
        if (!(authoritesFromClaims instanceof Collection)) {
            LOG.debug("Invalid token (no authorities): " + token);
            throw new InvalidTokenException("Invalid token (no authorities): " + token);
        }

        Collection<? extends GrantedAuthority> authorities = createAuthorityList(
                StringUtils.split(StringUtils.join((Collection<?>) authoritesFromClaims, ','), ','));

        UserAuthenticationToken authToken = new UserAuthenticationToken(userUuid, token, claims,
                (Long) claims.get(ClaimConstants.EXP), authorities);
        return authToken;
    }

    private Map<String, Object> getClaimsForToken(String token) {
        Jwt tokenJwt = null;
        try {
            tokenJwt = JwtHelper.decodeAndVerify(token, signerProvider.getVerifier());
        } catch (Throwable t) {
            LOG.debug("Invalid token (could not decode)", t);
            throw new InvalidTokenException("Invalid token (could not decode): " + token);
        }

        Map<String, Object> claims = null;
        try {
            claims = JsonUtils.readValue(tokenJwt.getClaims(), new TypeReference<Map<String, Object>>() {
            });
        } catch (JsonUtils.JsonUtilException e) {
            throw new IllegalStateException("Cannot read token claims", e);
        }

        return claims;
    }

    private List<GrantedAuthority> createAuthorityList(String... roles) {
        List<GrantedAuthority> authorities = Lists.newArrayList();

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }

}
