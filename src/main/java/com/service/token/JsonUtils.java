package com.service.token;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.util.StringUtils;

import java.io.IOException;

public final class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String writeValueAsString(Object object) throws JsonUtilException {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new JsonUtilException(e);
        }
    }

    public static <T> T readValue(String s, TypeReference typeReference) {
        try {
            if (StringUtils.hasText(s)) {
                return objectMapper.readValue(s, typeReference);
            } else {
                return null;
            }
        } catch (IOException e) {
            throw new JsonUtilException(e);
        }
    }

    public static class JsonUtilException extends RuntimeException {
        public JsonUtilException(Throwable cause) {
            super(cause);
        }
    }
}
