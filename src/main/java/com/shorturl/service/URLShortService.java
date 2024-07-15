package com.shorturl.service;

import com.shorturl.model.URLMapping;
import com.shorturl.repository.URLMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class URLShortService {

    private final URLMappingRepository urlMappingRepository;
    private final CounterService counterService;
    private static final int SHORT_URL_LENGTH = 8;

    @Autowired
    public URLShortService(URLMappingRepository urlMappingRepository, CounterService counterService) {
        this.urlMappingRepository = urlMappingRepository;
        this.counterService = counterService;
    }

    /**
     * Save the Shorten URL
     * */

    public URLMapping shortenUrl(String originalUrl) {

        String shortUrl = generateUniqueShortUrl();

        URLMapping urlMapping = new URLMapping();

        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setShortUrl(shortUrl);
        return urlMappingRepository.save(urlMapping);
    }

    /**
     * Method to generate the Unique URL if it already exists generate new one
     * */
    private String generateUniqueShortUrl() {
        String shortUrl;
        do {
            String shortUrlCounter = String.valueOf(counterService.getNextSequence());
            shortUrl = getEncodedString(shortUrlCounter);
        } while (urlMappingRepository.findByShortUrl(shortUrl) != null);
        return shortUrl;
    }

    private String getEncodedString(String shortUrlCounter) {
        return Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(shortUrlCounter.getBytes(StandardCharsets.UTF_8))
                .substring(0, SHORT_URL_LENGTH);
    }

    public URLMapping getOriginalUrl(String shortUrl) {
        return urlMappingRepository.findByShortUrl(shortUrl);
    }
}
