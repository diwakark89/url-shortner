package com.shorturl.service;

import java.util.Optional;

public interface URLShortService {
    String shortenUrl(String originalUrl);
    Optional<String> getOriginalUrl(String shortUrl);
}
