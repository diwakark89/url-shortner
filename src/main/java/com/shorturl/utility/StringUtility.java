package com.shorturl.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class StringUtility {
    @Value("${baseurl}")
    private String baseURL;

    @Value("${short.url.length}")
    private int shortURLLength;

    public String getShortURLString(String url) {
        return url.substring(baseURL.length());
    }

    public String getFullShortURL(String shortenUrl) {
        return baseURL + shortenUrl;
    }

    /**
     * To encode the counter into Base64 String
     * */
    public String getShortString(String shortUrlCounter) {
        return getBase64EncodedString(shortUrlCounter).substring(0, shortURLLength);
    }

    private String getBase64EncodedString(String shortUrlCounter){
        return Base64.getUrlEncoder().withoutPadding().encodeToString(shortUrlCounter.getBytes(StandardCharsets.UTF_8));
    }
}
