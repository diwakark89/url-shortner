package com.shorturl.service;

import com.shorturl.model.URLMapping;
import com.shorturl.repository.URLMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class URLShortService {
    @Autowired
    private URLMappingRepository urlMappingRepository;

    public URLMapping shortenUrl(String originalUrl) {
        String encodedUrl = Base64.getUrlEncoder().encodeToString(originalUrl.getBytes(StandardCharsets.UTF_8)).substring(0, 8);
        URLMapping urlMapping = new URLMapping();
        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setShortUrl(encodedUrl);
        return urlMappingRepository.save(urlMapping);
    }

    public URLMapping getOriginalUrl(String shortUrl) {
        return urlMappingRepository.findByShortUrl(shortUrl);
    }
}
