package com.service.security;

import com.google.common.base.Preconditions;

import com.utils.IoUtil;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.jwt.crypto.sign.InvalidSignatureException;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.jwt.crypto.sign.SignatureVerifier;
import org.springframework.security.jwt.crypto.sign.Signer;
import org.springframework.util.Assert;

import java.net.URL;

/**
 * A class that knows how to provide the signing and verification keys.
 */
public class SignerProvider implements InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(SignerProvider.class);

    private String verifierKey;
    private String signingKey;
    private Signer signer;

    @Override
    public void afterPropertiesSet() throws Exception {
        verifierKey = getFileString("verifier.key");
        signingKey = getFileString("signing.key");
        signer = new RsaSigner(signingKey);
        Assert.hasText(verifierKey, "Must at least specify verifier key");

        if (signer instanceof RsaSigner) {
            RsaVerifier verifier;
            try {
                verifier = new RsaVerifier(verifierKey);
            } catch (Exception e) {
                throw new RuntimeException("Unable to create an RSA verifier from verifierKey", e);
            }

            byte[] test = "test".getBytes();
            try {
                verifier.verify(test, signer.sign(test));
                LOG.debug("Signing and verification RSA keys match");
            } catch (InvalidSignatureException e) {
                throw new RuntimeException("Signing and verification RSA keys do not match", e);
            }
        }
    }

    public Signer getSigner() {
        return signer;
    }

    public SignatureVerifier getVerifier() {
        return new RsaVerifier(verifierKey);
    }

    public String getVerifierKey() {
        return verifierKey;
    }

    public void setVerifierKey(String verifierKey) {
        boolean valid = false;
        try {
            // Check if it's a private key.
            new RsaSigner(verifierKey);
        } catch (Exception expected) {
            // Valid verifierKey would fail RsaSigner.
            valid = true;
        }

        if (!valid) {
            throw new IllegalArgumentException("Private key cannot be set a verifierKey");
        }

        this.verifierKey = verifierKey;
    }

    public String getSigningKey() {
        return signingKey;
    }

    public void setSigningKey(String signingKey) {
        Preconditions.checkArgument(StringUtils.isNotBlank(signingKey));
        this.signingKey = signingKey.trim();
        this.signer = new RsaSigner(this.signingKey);
        LOG.debug("Configured with RSA signing key");
    }


    public String getFileString(String fileName){
        URL url = SignerProvider.class.getResource("/");
        String path = url.getFile() + "keys/" + fileName;
        return IoUtil.readCharacterFile(path);
    }

}
